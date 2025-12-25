package ymcris.rogex.d.daos.users.videogames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.users.videogames.UserVideogame;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The UserVideogameDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class UserVideogameDAO extends GenericDAO<UserVideogame> {

    // CONSTANTS ---------------------------------------------------------------
    public static final String SQL_INSERT_USER_VIDEOGAME
            = "INSERT INTO videogame_user (instaled, user_email, videogame_title, "
            + "enterprise_name) VALUES (?, ?, ?, ?)";

    public static final String SQL_EXISTS_USER_VIDEOGAME
            = "SELECT 1 FROM videogame_user WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ?";

    public static final String SQL_GET_USER_VIDEOGAME
            = "SELECT * FROM videogame_user WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ?";

    public static final String SQL_UPDATE_USER_VIDEOGAME
            = "UPDATE videogame_user SET instaled = ? WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ?";

    public static final String SQL_GET_ALL_USER_VIDEOGAMES
            = "SELECT * FROM videogame_user WHERE user_email = ?";

    public static final String SQL_DELETE_VIDEOGAME_USER
            = null;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserVideogameDAO() {
        super(
                SQL_INSERT_USER_VIDEOGAME,
                SQL_EXISTS_USER_VIDEOGAME,
                SQL_GET_USER_VIDEOGAME,
                SQL_UPDATE_USER_VIDEOGAME,
                SQL_GET_ALL_USER_VIDEOGAMES,
                SQL_DELETE_VIDEOGAME_USER
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(UserVideogame userVideogame) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_USER_VIDEOGAME)) {

            statement.setBoolean(1, userVideogame.isInstaled());
            statement.setString(2, userVideogame.getUserEmail());
            statement.setString(3, userVideogame.getVideogameTitle());
            statement.setString(4, userVideogame.getEnterpriseName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Creating the uservideogame with the object", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, UserVideogame userVideogame) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_USER_VIDEOGAME)) {

            statement.setBoolean(1, userVideogame.isInstaled());
            statement.setString(2, primaryKeys[0]);
            statement.setString(3, primaryKeys[1]);
            statement.setString(4, primaryKeys[2]);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Updating the uservideogame with the object", e);
        }
    }

    @Override
    protected UserVideogame getEntity(ResultSet resultSet) {
        try {

            return new UserVideogame(
                    resultSet.getString("user_email"),
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name"),
                    resultSet.getBoolean("instaled")
            );

        } catch (SQLException e) {
            throw new DAOException("Getting the uservideogame with the RS", e);
        }
    }

}
