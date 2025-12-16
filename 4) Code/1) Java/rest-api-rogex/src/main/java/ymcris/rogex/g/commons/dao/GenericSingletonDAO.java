package ymcris.rogex.g.commons.dao;

import java.sql.ResultSet;
import java.util.Optional;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import ymcris.rogex.f.database.DBConnectionSingleton;

/**
 * The GenericSingletonDAO class is the class responsible for be the template of
 * Singleton DAOs
 *
 * @author YmCris
 * @param <T>
 * @since Dec 16, 2025
 */
public abstract class GenericSingletonDAO<T> {

    // CONSTANTS ---------------------------------------------------------------
    protected final String SQL_GET_SINGLETON;
    protected final String SQL_UPDATE_SINGLETON;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     *
     * @param SQL_GET_SINGLETON
     * @param SQL_UPDATE_SINGLETON
     */
    public GenericSingletonDAO(String SQL_GET_SINGLETON, String SQL_UPDATE_SINGLETON) {
        this.SQL_GET_SINGLETON = SQL_GET_SINGLETON;
        this.SQL_UPDATE_SINGLETON = SQL_UPDATE_SINGLETON;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Return the single tuple of the DB
     *
     * @return The optional with contains the Entity
     */
    public Optional<T> getSingleton() {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_SINGLETON)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(createEntity(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Error getting singleton entity: " + e.getMessage());
        }

        return Optional.empty();
    }

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Function responsible for create an entity
     *
     * resultSet.getString("username"), resultSet.getString("email"),
     * resultSet.getString("location"), resultSet.getString("photo"),
     * resultSet.getString("password")
     *
     * @param resultSet retult set
     * @return entity type
     */
    protected abstract T createEntity(ResultSet resultSet);

    /**
     * Method responsible for update an entity with their primary keys
     *
     * @param primaryKeys unique id of the entity
     * @param entity entity to update
     */
    public abstract void updateEntity(String[] primaryKeys, T entity);
}
