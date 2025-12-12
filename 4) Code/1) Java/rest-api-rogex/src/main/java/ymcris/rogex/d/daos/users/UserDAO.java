package ymcris.rogex.d.daos.users;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.g.commons.dao.GenericDAO;

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
            + "country = ?, public_library WHERE email = ?";

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
                    (File) resultSet.getBlob("user"),
                    resultSet.getString("nickname"),
                    resultSet.getString("password"),
                    resultSet.getDate("birth_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("country"),
                    resultSet.getBoolean("public_library")
            );
        } catch (SQLException ex) {
            System.out.println("ERRORRRR");
        }
        return null;
    }

}
