package ymcris.rogex.d.daos.enterprises.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;

/**
 * The EnterpriseUserDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseUserDAO extends GenericDAO<EnterpriseUser> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_ENTERPRISE_USER
            = "INSERT INTO user_enterprise ()";
    private static final String SQL_EXISTS_ENTERPRISE_USER = "";
    private static final String SQL_GET_ENTERPRISE_USER = "";
    private static final String SQL_UPDATE_ENTERPRISE_USER = "";
    private static final String SQL_GET_ALL_ENTERPRISE_USERS = "";
    private static final String SQL_DELETE_ENTERPRISE_USER = "";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseUserDAO() {
        super(
                SQL_INSERT_ENTERPRISE_USER,
                SQL_EXISTS_ENTERPRISE_USER,
                SQL_GET_ENTERPRISE_USER,
                SQL_UPDATE_ENTERPRISE_USER,
                SQL_GET_ALL_ENTERPRISE_USERS,
                SQL_DELETE_ENTERPRISE_USER
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    public void createEntity(EnterpriseUser entity) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTERPRISE_USER)) {

            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getPassword());
            statement.setDate(4, Date.valueOf(entity.getBirthDate()));
            statement.setString(5, entity.getEnterpriseName());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseUserDAO)"
                    + "because " + e.getMessage());
        }

    }

    @Override
    public void updateEntity(String[] primaryKeys, EnterpriseUser enterpriseUser) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_ENTERPRISE_USER)) {

            statement.setString(1, "name");
            statement.setString(2, "password");
            statement.setString(3, "birth_date");
            statement.setString(4, primaryKeys[0]);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseUserDAO)"
                    + "because " + e.getMessage());
        }
    }

    @Override
    protected EnterpriseUser createEntity(ResultSet resultSet) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTERPRISE_USER)) {

            return new EnterpriseUser(
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getDate("birth_date").toLocalDate(),
                    resultSet.getString("enterprise_name")
            );

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseUserDAO)"
                    + "because " + e.getMessage());
            throw new RuntimeException("Error creating enterprise user");
        }
    }

}
