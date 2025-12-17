package ymcris.rogex.d.daos.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.g.commons.dao.GenericDAO;

/**
 * The CategoryDAO class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class CategoryDAO extends GenericDAO<Category> {

    // CONSTANTS ---------------------------------------------------------------
    private static final String SQL_INSERT_CATEGORY
            = "INSERT INTO category (name) VALUES (?)";

    private static final String SQL_EXISTS_CATEGORY
            = "SELECT 1 FROM category WHERE name = ?";

    private static final String SQL_GET_BY_ID
            = "SELECT * FROM category WHERE name = ?";

    private static final String SQL_UPDATE_CATEGORY
            = "UPDATE category SET name = ? WHERE name = ?";

    private static final String SQL_GET_ALL_CATEGORIES
            = "SELECT * FROM category";

    private static final String SQL_DELETE_CATEGORY
            = "DELETE FROM category WHERE name = ?";

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public CategoryDAO() {
        super(
                SQL_INSERT_CATEGORY,
                SQL_EXISTS_CATEGORY,
                SQL_GET_BY_ID,
                SQL_UPDATE_CATEGORY,
                SQL_GET_ALL_CATEGORIES,
                SQL_DELETE_CATEGORY
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------    
    @Override
    public void createEntity(Category category) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_INSERT_CATEGORY)) {

            statement.setString(1, category.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing creatingEntity(Category) (CategoryDAO)"
                    + "because " + e.getMessage());
        }
    }

    @Override
    public void updateEntity(String[] primaryKeys, Category category) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_UPDATE_CATEGORY)) {

            statement.setString(1, category.getName());
            statement.setString(2, primaryKeys[0]);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while performing update entity (CategoryDAO) "
                    + "because " + e.getMessage());
        }
    }

    @Override
    protected Category createEntity(ResultSet resultSet) {
        try {
            return new Category(
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error creating category entity");
        }
    }

}
