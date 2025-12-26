package ymcris.rogex.d.daos.banner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.banner.MainBanner;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.DAOException;

/**
 * The MainBannerDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
public class MainBannerDAO extends GenericDAO<MainBanner> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_GAME_TO_BANNER
            = "INSERT INTO main_banner (multimedia, is_image, link) "
            + "VALUES (?, ?, ?)";

    private static final String SQL_EXIST_GAME_IN_BANNER
            = "SELECT 1 FROM main_banner WHERE link = ?";

    private static final String SQL_GET_GAME_IN_BANNER_WHIT_LINK
            = "SELECT * FROM main_banner WHERE link = ?";

    private static final String SQL_UPDATE_GAME_IN_BANNER_WITH_LINK
            = "UPDATE main_banner SET multimedia = ?, is_image = ?, link = ? "
            + "WHERE link = ?";

    private static final String SQL_GET_ALL_GAMES_IN_BANNER
            = "SELECT * FROM main_banner";

    private static final String SQL_DELETE_GAME_TO_BANNER
            = "DELETE FROM main_banner WHERE link = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MainBannerDAO() {
        super(
                SQL_INSERT_GAME_TO_BANNER,
                SQL_EXIST_GAME_IN_BANNER,
                SQL_GET_GAME_IN_BANNER_WHIT_LINK,
                SQL_UPDATE_GAME_IN_BANNER_WITH_LINK,
                SQL_GET_ALL_GAMES_IN_BANNER,
                SQL_DELETE_GAME_TO_BANNER
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(MainBanner banner) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_GAME_TO_BANNER)) {

            statement.setBytes(1, banner.getMultimedia());
            statement.setBoolean(2, banner.isImage());
            statement.setString(3, banner.getLink());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Adding videogame to banner with the object", e);
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, MainBanner banner) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_GAME_IN_BANNER_WITH_LINK)) {

            statement.setBytes(1, banner.getMultimedia());
            statement.setBoolean(2, banner.isImage());
            statement.setString(3, banner.getLink());
            statement.setString(4, primaryKeys[0]);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Updating banner with the object and pks", e);
        }
    }

    @Override
    protected MainBanner getEntity(ResultSet resultSet) {
        try {

            return new MainBanner(
                    resultSet.getBytes("multimedia"),
                    resultSet.getBoolean("is_image"),
                    resultSet.getString("link")
            );

        } catch (SQLException e) {
            throw new DAOException("Getting banner with RS", e);
        }
    }

}
