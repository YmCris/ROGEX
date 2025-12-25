package ymcris.rogex.d.daos.videogame;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.e.models.videogame.AgeRating;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The VideogameDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class VideogameDAO extends GenericDAO<Videogame> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_VIDEOGAME
            = "INSERT INTO videogame (title, description, price, minimum_requirements,"
            + " age_rating, release_date, downloads, enterprise_name, "
            + "suspension_of_sale, hidden_comments, hidden) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_EXISTS_VIDEOGAME
            = "SELECT 1 FROM videogame WHERE title = ? AND enterprise_name = ?";

    private static final String SQL_GET_VIDEOGAME
            = "SELECT * FROM videogame WHERE title = ? AND enterprise_name = ?";

    private static final String SQL_UPDATE_VIDEOGAME
            = "UPDATE videogame SET description = ?, price = ?, "
            + "minimum_requirements = ?, age_rating = ?, "
            + "downloads = ?, suspension_of_sale = ?, hidden_comments = ?, "
            + "hidden = ? WHERE title = ? AND enterprise_name = ?";

    private static final String SQL_GET_ALL_ENTERPRISE_VIDEOGAMES
            = "SELECT * FROM videogame WHERE enterprise_name = ?";

    private static final String SQL_GET_ALL_VIDEOGAMES
            = "SELECT * FROM videogame";

    private static final String SQL_DELETE_VIDEOGAME
            = "DELETE FROM videogame WHERE title = ? AND enterprise_name = ?";

    // CATEGORIES --------------------------------------------------------------
    private static final String SQL_INSERT_VIDEOGAME_CATEGORY
            = "INSERT INTO videogame_category (category_name, videogame_title, "
            + "enterprise_name) VALUES (?, ?, ?)";

    private static final String SQL_GET_VIDEOGAME_CATEGORY
            = "SELECT category_name FROM videogame_category WHERE videogame_title = ? "
            + "AND enterprise_name = ?";

    private static final String SQL_GET_VIDEOGAME_CATEGORIES
            = "SELECT COUNT(*) FROM videogame_category WHERE videogame_title = ? "
            + "AND enterprise_name = ?";

    private static final String SQL_UPDATE_VIDEOGAME_CATEGORY
            = "UPDATE videogame_category SET category_name = ? WHERE "
            + "videogame_title = ? AND enterprise_name = ? AND category_name = ?";

    private static final String SQL_DELETE_VIDEOGAME_CATEGORY
            = "DELETE FROM videogame_category WHERE category_name = ?"
            + " AND videogame_title = ? AND enterprise_name = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameDAO() {
        super(
                SQL_INSERT_VIDEOGAME,
                SQL_EXISTS_VIDEOGAME,
                SQL_GET_VIDEOGAME,
                SQL_UPDATE_VIDEOGAME,
                SQL_GET_ALL_ENTERPRISE_VIDEOGAMES,
                SQL_DELETE_VIDEOGAME
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public void insertCategories(Videogame videogame) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_VIDEOGAME_CATEGORY)) {

            for (Category category : videogame.getCategories()) {
                statement.setString(1, category.getName());
                statement.setString(2, videogame.getTitle());
                statement.setString(3, videogame.getEnterpriseName());
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing videogameCategory "
                    + "because " + e.getMessage());
        }
    }

    public void addCategoryToVideogame(String title, String enterpriseName,
            String categoryName) {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_VIDEOGAME_CATEGORY)) {

            statement.setString(1, categoryName);
            statement.setString(2, title);
            statement.setString(3, enterpriseName);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing videogameCategory "
                    + "because " + e.getMessage());
        }
    }

    public void updateVideogameCategory(String title, String enterpriseName,
            String existingCategoryName, String newCategoryName) {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_VIDEOGAME_CATEGORY)) {

            statement.setString(1, newCategoryName);
            statement.setString(2, title);
            statement.setString(3, enterpriseName);
            statement.setString(4, existingCategoryName);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing videogameCategoryUpdate "
                    + "because " + e.getMessage());
        }
    }

    public void deleteCategoryFromVideogame(String title, String enterpriseName,
            String categoryName) throws ObjectNotFoundException {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_DELETE_VIDEOGAME_CATEGORY)) {

            statement.setString(1, categoryName);
            statement.setString(2, title);
            statement.setString(3, enterpriseName);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing videogameCategoryDelete "
                    + "because " + e.getMessage());
            throw new ObjectNotFoundException("Category does'nt exists in the game");
        }
    }

    public void loadCategoriesFromDB(Videogame videogame) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_VIDEOGAME_CATEGORY)) {

            statement.setString(1, videogame.getTitle());
            statement.setString(2, videogame.getEnterpriseName());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                videogame.getCategories().add(new Category(resultSet.getString("category_name")));
            }

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing addingCategories to videogame "
                    + "because " + e.getMessage());
            throw new RuntimeException("Burro");
        }
    }

    public boolean hasCategories(String title, String enterpriseName) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_VIDEOGAME_CATEGORIES)) {

            statement.setString(1, title);
            statement.setString(2, enterpriseName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 1;
            }

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing addingCategories to videogame "
                    + "because " + e.getMessage());
        }

        return false;

    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public void createEntity(Videogame videogame) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_VIDEOGAME)) {

            statement.setString(1, videogame.getTitle());
            statement.setString(2, videogame.getDescription());
            statement.setDouble(3, videogame.getPrice());
            statement.setString(4, videogame.getMinimumRequirements());
            statement.setString(5, videogame.getAgeRating().name());
            statement.setDate(6, Date.valueOf(videogame.getReleaseDate()));
            statement.setInt(7, videogame.getDownloads());
            statement.setString(8, videogame.getEnterpriseName());
            statement.setBoolean(9, videogame.isSuspensionOfSale());
            statement.setBoolean(10, videogame.isHiddenComments());
            statement.setBoolean(11, videogame.isHidden());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing VideogameCreatingDAO "
                    + "because " + e.getMessage());
        }

    }

    @Override
    public void updateEntity(String[] primaryKeys, Videogame videogame) {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_VIDEOGAME)) {

            statement.setString(1, videogame.getDescription());
            statement.setDouble(2, videogame.getPrice());
            statement.setString(3, videogame.getMinimumRequirements());
            statement.setString(4, videogame.getAgeRating().name());
            statement.setInt(5, videogame.getDownloads());
            statement.setBoolean(6, videogame.isSuspensionOfSale());
            statement.setBoolean(7, videogame.isHiddenComments());
            statement.setBoolean(8, videogame.isHidden());
            statement.setString(9, primaryKeys[0]);
            statement.setString(10, primaryKeys[1]);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing VideogameUpdateDAO "
                    + "because " + e.getMessage());
        }

    }

    @Override
    protected Videogame getEntity(ResultSet resultSet) {
        try {
            Videogame videogame = new Videogame(
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getString("minimum_requirements"),
                    AgeRating.valueOf(resultSet.getString("age_rating")),
                    resultSet.getDate("release_date").toLocalDate(),
                    resultSet.getInt("downloads"),
                    resultSet.getString("enterprise_name"),
                    resultSet.getBoolean("suspension_of_sale"),
                    resultSet.getBoolean("hidden_comments"),
                    resultSet.getBoolean("hidden"));
            loadCategoriesFromDB(videogame);

            return videogame;
        } catch (SQLException ex) {
            System.out.println("An exception of type " + ex.getClass().getName()
                    + " occurred while performing VideogameCreatingDAO RS"
                    + "because " + ex.getMessage());
            throw new RuntimeException("ERRORR creating videogame");
        }
    }

}
