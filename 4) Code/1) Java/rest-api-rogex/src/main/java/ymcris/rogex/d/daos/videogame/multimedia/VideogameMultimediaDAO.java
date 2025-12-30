package ymcris.rogex.d.daos.videogame.multimedia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ymcris.rogex.e.models.videogame.multimedia.VideogameMultimedia;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The VideogameMultimediaDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
public class VideogameMultimediaDAO extends GenericDAO<VideogameMultimedia> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_VIDEOGAME_MULTIMEDIA
            = "INSERT INTO videogame_multimedia (multimedia, is_image, "
            + "videogame_title, enterprise_name) VALUES (?, ?, ?, ?)";

    private static final String SQL_EXISTS_VIDEOGAME_MULTIMEDIA
            = "SELECT 1 FROM videogame_multimedia WHERE id = ?";

    private static final String SQL_GET_VIDEOGAME_MULTIMEDIA
            = "SELECT * FROM videogame_multimedia WHERE id = ?";

    private static final String SQL_UPDATE_VIDEOGAME_MULTIMEDIA
            = "UPDATE videogame_multimedia SET multimedia = ?, is_image = ? "
            + "WHERE id = ?";

    private static final String SQL_GET_ALL_VIDEOGAME_MULTIMEDIA
            = "SELECT * FROM videogame_multimedia WHERE videogame_title = ? "
            + "AND enterprise_name = ?";

    private static final String SQL_DELETE_VIDEOGAME_MULTIMEDIA
            = "DELETE FROM videogame_multimedia WHERE id = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameMultimediaDAO() {
        super(
                SQL_INSERT_VIDEOGAME_MULTIMEDIA,
                SQL_EXISTS_VIDEOGAME_MULTIMEDIA,
                SQL_GET_VIDEOGAME_MULTIMEDIA,
                SQL_UPDATE_VIDEOGAME_MULTIMEDIA,
                SQL_GET_ALL_VIDEOGAME_MULTIMEDIA,
                SQL_DELETE_VIDEOGAME_MULTIMEDIA
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(VideogameMultimedia multimedia) {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(
                        SQL_INSERT_VIDEOGAME_MULTIMEDIA, Statement.RETURN_GENERATED_KEYS)) {

            statement.setBytes(1, multimedia.getPhoto());
            statement.setBoolean(2, multimedia.isImage());
            statement.setString(3, multimedia.getVideogameTitle());
            statement.setString(4, multimedia.getEnterpriseName());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                multimedia.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DAOException("Creating the multimedia in DB", e);
        }

    }

    @Override
    public void updateEntity(String[] primaryKeys, VideogameMultimedia multimedia) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(
                        SQL_UPDATE_VIDEOGAME_MULTIMEDIA)) {

            statement.setBytes(1, multimedia.getPhoto());
            statement.setBoolean(2, multimedia.isImage());
            statement.setInt(3, Integer.parseInt(primaryKeys[0]));

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Updating multimedia in DB", e);
        }
    }

    @Override
    protected VideogameMultimedia getEntity(ResultSet resultSet) {

        try {

            return new VideogameMultimedia(
                    resultSet.getBytes("multimedia"),
                    resultSet.getBoolean("is_image"),
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name")
            );

        } catch (SQLException e) {
            throw new DAOException("Getting multimedia from DB", e);
        }
    }

}
