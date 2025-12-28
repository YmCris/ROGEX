package ymcris.rogex.d.daos.enterprises.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The EnterpriseUserDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseUserDAO extends GenericDAO<EnterpriseUser> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_ENTERPRISE_USER
            = "INSERT INTO enterprise_user (email, name, password, birth_date,"
            + " enterprise_name) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_ENTERPRISE_USER
            = "SELECT 1 FROM enterprise_user WHERE email = ?";

    private static final String SQL_GET_ENTERPRISE_USER
            = "SELECT * FROM enterprise_user WHERE email = ?";

    private static final String SQL_UPDATE_ENTERPRISE_USER
            = "UPDATE enterprise_user SET name = ?, password = ?, birth_date = ? "
            + "WHERE email = ?";

    private static final String SQL_LOG_IN
            = "SELECT * FROM enterprise_user WHERE email = ? and password = ?";

    private static final String SQL_GET_ALL_ENTERPRISE_USERS_OF_ENTERPRISE
            = "SELECT * FROM enterprise_user WHERE enterprise_name = ?";

    private static final String SQL_DELETE_ENTERPRISE_USER
            = "DELETE FROM enterprise_user WHERE email = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseUserDAO() {
        super(
                SQL_INSERT_ENTERPRISE_USER,
                SQL_EXISTS_ENTERPRISE_USER,
                SQL_GET_ENTERPRISE_USER,
                SQL_UPDATE_ENTERPRISE_USER,
                SQL_GET_ALL_ENTERPRISE_USERS_OF_ENTERPRISE,
                SQL_DELETE_ENTERPRISE_USER
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    public void createEntity(EnterpriseUser enterpriseUser) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_ENTERPRISE_USER)) {

            statement.setString(1, enterpriseUser.getEmail());
            statement.setString(2, enterpriseUser.getName());
            statement.setString(3, enterpriseUser.getPassword());
            statement.setDate(4, Date.valueOf(enterpriseUser.getBirthDate()));
            statement.setString(5, enterpriseUser.getEnterpriseName());

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

            statement.setString(1, enterpriseUser.getName());
            statement.setString(2, enterpriseUser.getPassword());
            statement.setDate(3, Date.valueOf(enterpriseUser.getBirthDate()));
            statement.setString(4, primaryKeys[0]);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseUserDAO)"
                    + "because " + e.getMessage());
        }
    }

    @Override
    protected EnterpriseUser getEntity(ResultSet resultSet) {
        try {

            return new EnterpriseUser(
                    resultSet.getString("email"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getDate("birth_date").toLocalDate(),
                    resultSet.getString("enterprise_name")
            );

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creating entity (EnterpriseUserDAO)"
                    + "because " + e.getMessage());
            throw new RuntimeException("Error creating enterprise user");
        }
    }

    public Optional<EnterpriseUser> logIn(String email, String password) {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_LOG_IN)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                EnterpriseUser enterpriseUser = getEntity(resultSet);

                return Optional.of(enterpriseUser);
            }

            return Optional.empty();
            
        } catch (SQLException e) {
            throw new DAOException("Login in enterprise user DAO", e);
        }

    }

}
