package ymcris.rogex.d.daos.groups;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.groups.Group;
import ymcris.rogex.e.models.groups.MemberGroup;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The MemberGroupDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class MemberGroupDAO extends GenericDAO<MemberGroup> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_MEMBER_GROUP
            = "INSERT INTO group_member (group_member_email, family_group_name) "
            + "VALUES (?, ?)";

    private static final String SQL_EXISTS_MEMBER_GROUP
            = "SELECT 1 FROM group_member WHERE group_member_email = ? "
            + "AND family_group_name = ?";

    private static final String SQL_GET_MEMBER_GROUP
            = "SELECT * FROM group_member WHERE group_member_email = ? "
            + "AND family_group_name = ?";

    private static final String SQL_GET_MEMBERS_GROUP
            = "SELECT * FROM group_member WHERE family_group_name = ?";

    private static final String SQL_UPDATE_MEMBER_GROUP
            = null;

    private static final String SQL_GET_ALL_MEMBER_GROUPS
            = "SELECT * FROM group_member";

    private static final String SQL_DELETE_MEMBER_GROUP
            = "DELETE FROM group_member WHERE group_member_email = ?"
            + " AND family_group_name = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MemberGroupDAO() {
        super(
                SQL_INSERT_MEMBER_GROUP,
                SQL_EXISTS_MEMBER_GROUP,
                SQL_GET_MEMBER_GROUP,
                SQL_UPDATE_MEMBER_GROUP,
                SQL_GET_ALL_MEMBER_GROUPS,
                SQL_DELETE_MEMBER_GROUP
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    public void createEntity(MemberGroup memberGroup) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTITY)) {

            statement.setString(1, memberGroup.getGroupMemberEmail());
            statement.setString(2, memberGroup.getFamilyGroupName());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating member group "
                    + "because " + e.getMessage());
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, MemberGroup memberGroup) {
        throw new UnsupportedOperationException("You can't edit this");
    }

    @Override
    protected MemberGroup createEntity(ResultSet resultSet) {
        try {

            return new MemberGroup(
                    resultSet.getString("group_member_email"),
                    resultSet.getString("family_group_name")
            );

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creatingMemberGroup RS "
                    + "because " + e.getMessage());
            throw new RuntimeException("ERROR creating member group");
        }
    }

    //-----------------
    public void addUsers(Group group) throws ObjectNotFoundException {
        UserDAO userDAO = new UserDAO();

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_MEMBERS_GROUP)) {

            statement.setString(1, group.getName());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = userDAO.getEntityByPrimaryKeys(
                        new String[]{resultSet.getString("group_member_email")}).get();
                System.out.println("Agregando usuario: " + user.getEmail() + " al grupo " + group.getName());
                group.getGroupMembers().add(user);
            }

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName() + " occurred while performing ____ because " + e.getMessage());
            throw new ObjectNotFoundException("Some User doesn't exists");
        }
    }
}
