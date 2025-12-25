package ymcris.rogex.d.daos.videogames.ratings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.videogames.ratings.VideogameRating;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The VideogameRatingDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameRatingDAO extends GenericDAO<VideogameRating> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_VIDEOGAME_RATING
            = "INSERT INTO videogame_rating (videogame_title, enterprise_name, "
            + "user_email, rating) VALUES (?, ?, ?, ?)";

    private static final String SQL_EXISTS_VIDEOGAME_RATING_FROM_USER
            = "SELECT 1 FROM videogame_rating WHERE videogame_title = ? AND "
            + "enterprise_name = ? AND user_email = ?";

    private static final String SQL_GET_VIDEOGAME_RATING_FROM_USER
            = "SELECT * FROM videogame_rating WHERE videogame_title = ? AND "
            + "enterprise_name = ? AND user_email = ?";

    private static final String SQL_UPDATE_VIDEOGAME_RATING
            = "UPDATE videogame_rating SET rating = ? WHERE videogame_title = ? "
            + "AND enterprise_name = ? AND user_email = ?";

    private static final String SQL_GET_ALL_VIDEOGAMES_RATINGS
            = "SELECT * FROM videogame_rating WHERE videogame_title = ? "
            + "AND enterprise_name = ? ";

    private static final String SQL_DELETE_VIDEOGAME_RATING
            = "DELETE FROM videogame_rating WHERE videogame_title = ? "
            + "AND enterprise_name = ? AND user_email = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameRatingDAO() {
        super(
                SQL_INSERT_VIDEOGAME_RATING,
                SQL_EXISTS_VIDEOGAME_RATING_FROM_USER,
                SQL_GET_VIDEOGAME_RATING_FROM_USER,
                SQL_UPDATE_VIDEOGAME_RATING,
                SQL_GET_ALL_VIDEOGAMES_RATINGS,
                SQL_DELETE_VIDEOGAME_RATING
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(VideogameRating videogameRating) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_VIDEOGAME_RATING)) {

            statement.setString(1, videogameRating.getVideogameTitle());
            statement.setString(2, videogameRating.getEnterpriseName());
            statement.setString(3, videogameRating.getUserEmail());
            statement.setInt(4, videogameRating.getRating());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Creating videogame rating in DAO", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, VideogameRating videogameRating) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_VIDEOGAME_RATING)) {

            statement.setInt(1, videogameRating.getRating());
            statement.setString(2, primaryKeys[0]);
            statement.setString(3, primaryKeys[1]);
            statement.setString(4, primaryKeys[2]);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Updating videogame rating in DAO", e);
        }

    }

    @Override
    protected VideogameRating getEntity(ResultSet resultSet) {
        try {
            return new VideogameRating(
                    resultSet.getString("videogame_title"),
                    resultSet.getString("enterprise_name"),
                    resultSet.getString("user_email"),
                    resultSet.getInt("rating")
            );
        } catch (SQLException e) {
            throw new DAOException("Getting videogame rating with rs", e);
        }
    }

}
