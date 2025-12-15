package ymcris.rogex.d.daos.users;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.f.database.DBConnectionSingleton;

/**
 * The UsersDAO class is the class responsible for be the Data Access Object of
 * users, crud auxiliar methods
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UserDAO extends GenericDAO<User> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_USER
            = "INSERT INTO user (photo, nickname, password, birth_date, email,"
            + "phone_number, country, public_library)"
            + " VALUES (?, ?, ?, ?, ?,?,?,?)";

    private static final String SQL_EXISTS_USER
            = "SELECT 1 FROM user WHERE email = ?";

    private static final String SQL_GET_BY_EMAIL
            = "SELECT * FROM user WHERE email = ?";

    private static final String SQL_UPDATE_USER
            = "UPDATE user SET photo = ?, birth_date = ?, phone_number= ?,"
            + "country = ?, public_library = ? WHERE email = ?";

    private static final String SQL_GET_ALL_USERS
            = "SELECT * FROM user";

    private static final String SQL_DELETE_USER
            = "DELETE FROM user WHERE email = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserDAO() {
        super(SQL_INSERT_USER, SQL_EXISTS_USER,
                SQL_GET_BY_EMAIL, SQL_UPDATE_USER,
                SQL_GET_ALL_USERS, SQL_DELETE_USER);
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected User createEntity(ResultSet resultSet) {
        try {
            return new User(
                    resultSet.getBytes("photo"),
                    resultSet.getString("nickname"),
                    resultSet.getString("password"),
                    resultSet.getDate("birth_date").toLocalDate(),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("country"),
                    resultSet.getBoolean("public_library")
            );
        } catch (SQLException ex) {
            System.out.println("ERRORRRR");
            throw new RuntimeException("Error creating user entity", ex);
        }
    }

    @Override
    public void createEntity(User user) {
        try (
                Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ENTITY)) {

            statement.setBytes(1, user.getPhoto());
            statement.setString(2, user.getNickname());
            statement.setString(3, user.getPassword());
            statement.setDate(4, Date.valueOf(user.getBirthDate()));
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getCountry());
            statement.setBoolean(8, user.isPublicLibrary());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in entity creation: " + e.getMessage());
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, User user) {

        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ENTITY)) {

            statement.setBytes(1, user.getPhoto());
            statement.setDate(2, Date.valueOf(user.getBirthDate()));
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getCountry());
            statement.setBoolean(5, user.isPublicLibrary());
            statement.setString(6, primaryKeys[0]);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error in the entity update: " + e.getMessage());
        }
    }

}
