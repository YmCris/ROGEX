package ymcris.rogex.d.daos.groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.groups.Group;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;

/**
 * The GroupDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class GroupDAO extends GenericDAO<Group> {

    // GROUP CONSTANTS ---------------------------------------------------------
    private static final String SQL_INSERT_GROUP
            = "INSERT INTO family_group (name, quantity, members_limit) "
            + "VALUES (?, ?, ?)";

    private static final String SQL_EXISTS_GROUP
            = "SELECT 1 FROM family_group WHERE name = ?";

    private static final String SQL_GET_GROUP
            = "SELECT * FROM family_group WHERE name = ?";

    private static final String SQL_UPDATE_GROUP
            = "UPDATE family_group SET quantity = ? WHERE name = ?";

    private static final String SQL_GET_ALL_GROUPS
            = "SELECT * FROM family_group";

    private static final String SQL_DELETE_GROUP
            = "DELETE FROM family_group WHERE name = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GroupDAO() {
        super(
                SQL_INSERT_GROUP,
                SQL_EXISTS_GROUP,
                SQL_GET_GROUP,
                SQL_UPDATE_GROUP,
                SQL_GET_ALL_GROUPS,
                SQL_DELETE_GROUP
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Group group) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_GROUP)) {

            statement.setString(1, group.getName());
            statement.setInt(2, group.getQuantity());
            statement.setInt(3, group.getMembersLimit());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating group group"
                    + "because " + e.getMessage());
        }

    }

    @Override
    public void updateEntity(String[] primaryKeys, Group group) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_GROUP)) {

            statement.setInt(1, group.getQuantity());
            statement.setString(2, primaryKeys[0]);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing updating group group, pK"
                    + "because " + e.getMessage());
        }
    }

    @Override
    protected Group getEntity(ResultSet resultSet) {
        try {
            Group group = new Group(
                    resultSet.getString("name"),
                    resultSet.getInt("members_limit"),
                    resultSet.getInt("quantity")
            );

            return group;
        } catch (SQLException ex) {
            System.out.println("An exception of type " + ex.getClass().getName()
                    + " occurred while performing creating group RS"
                    + "because " + ex.getMessage());
            throw new RuntimeException("Creating group error");
        }
    }

}
