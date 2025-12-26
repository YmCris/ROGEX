package ymcris.rogex.d.daos.instalations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import ymcris.rogex.e.models.instalation.VideogameInstalation;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The VideogameInstalationDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class VideogameInstalationDAO extends GenericDAO<VideogameInstalation> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_INSTALATION
            = "INSERT INTO videogame_installation (videogame_installation_date, "
            + "videogame_desinstallation_date, user_email, videogame_title, "
            + "enterprise_name) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_INSTALATION
            = "SELECT 1 FROM videogame_installation WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ? AND "
            + "videogame_installation_date = ?";

    private static final String SQL_GET_INSTALATIONS_OF_GAME
            = "SELECT * FROM videogame_installation WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ? AND "
            + "videogame_installation_date = ?";

    private static final String SQL_UPDATE_INSTALATION
            = "UPDATE videogame_installation SET videogame_desinstallation_date = ?"
            + " WHERE user_email = ? AND videogame_title = ? AND "
            + "enterprise_name = ? AND videogame_installation_date =? ";

    private static final String SQL_GET_ALL_INSTALATIONS_OF_VIDEOGAME_USER
            = "SELECT * FROM videogame_installation WHERE user_email = ? AND "
            + "videogame_title = ? AND enterprise_name = ?";

    private static final String SQL_DELETE_INSTALATION
            = null;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameInstalationDAO() {
        super(
                SQL_INSERT_INSTALATION,
                SQL_EXISTS_INSTALATION,
                SQL_GET_INSTALATIONS_OF_GAME,
                SQL_UPDATE_INSTALATION,
                SQL_GET_ALL_INSTALATIONS_OF_VIDEOGAME_USER,
                SQL_DELETE_INSTALATION
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    public void createEntity(VideogameInstalation videogameInstalation) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_INSTALATION)) {

            statement.setTimestamp(1, Timestamp.valueOf(videogameInstalation.getVideogameInstallationDate()));
            if (videogameInstalation.getVideogameDesinstallationDate() != null) {
                statement.setTimestamp(2, Timestamp.valueOf(videogameInstalation.getVideogameDesinstallationDate()));
            } else {
                statement.setNull(2, Types.TIMESTAMP);
            }
            statement.setString(3, videogameInstalation.getUserEmail());
            statement.setString(4, videogameInstalation.getVideogameTitle());
            statement.setString(5, videogameInstalation.getEnterpriseName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Creating videogame instalation with object", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, VideogameInstalation videogameInstalation) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_INSTALATION)) {

            statement.setTimestamp(1, Timestamp.valueOf(videogameInstalation.getVideogameDesinstallationDate()));
            statement.setString(2, primaryKeys[0]);
            statement.setString(3, primaryKeys[1]);
            statement.setString(4, primaryKeys[2]);
            statement.setString(5, primaryKeys[3]);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Creating videogame instalation with object", e);
        }
    }

    @Override
    protected VideogameInstalation getEntity(ResultSet resultSet) {
        try {
            LocalDateTime desinstalationDate = null;
            if (resultSet.getTimestamp("videogame_desinstallation_date")!= null) {
                desinstalationDate = resultSet.getTimestamp("videogame_desinstallation_date").toLocalDateTime();
            }

            return new VideogameInstalation(
                    resultSet.getTimestamp("videogame_installation_date").toLocalDateTime(),
                    desinstalationDate,
                    resultSet.getString("user_email"),
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name")
            );

        } catch (SQLException e) {
            throw new DAOException("Getting videogame with result set", e);
        }
    }

}
