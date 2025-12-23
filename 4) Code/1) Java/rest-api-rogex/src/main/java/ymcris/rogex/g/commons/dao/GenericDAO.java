package ymcris.rogex.g.commons.dao;

import java.util.List;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import ymcris.rogex.f.database.DBConnectionSingleton;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The GenericDAO class is the class responsible for be the template of daos
 *
 * @author YmCris
 * @param <T> Parameter of DAO type
 * @since Dec 11, 2025
 */
public abstract class GenericDAO<T> {

    // CONSTANTS ---------------------------------------------------------------
    protected final String SQL_GET_ENTITY_BY_PK;
    protected final String SQL_INSERT_ENTITY;
    protected final String SQL_ENTITY_EXISTS;
    protected final String SQL_UPDATE_ENTITY;
    protected final String SQL_DELETE_ENTITY;
    protected final String SQL_GET_ALL_ENTITIES;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     *
     * @param SQL_INSERT_ENTITY
     * @param SQL_EXISTS_ENTITY
     * @param SQL_GET_BY_PK
     * @param SQL_UPDATE_ENTITY
     * @param SQL_GET_ALL_ENTITIES
     * @param SQL_DELETE_ENTITY
     */
    public GenericDAO(String SQL_INSERT_ENTITY, String SQL_EXISTS_ENTITY,
            String SQL_GET_BY_PK, String SQL_UPDATE_ENTITY,
            String SQL_GET_ALL_ENTITIES, String SQL_DELETE_ENTITY) {
        this.SQL_INSERT_ENTITY = SQL_INSERT_ENTITY;
        this.SQL_ENTITY_EXISTS = SQL_EXISTS_ENTITY;
        this.SQL_GET_ENTITY_BY_PK = SQL_GET_BY_PK;
        this.SQL_UPDATE_ENTITY = SQL_UPDATE_ENTITY;
        this.SQL_GET_ALL_ENTITIES = SQL_GET_ALL_ENTITIES;
        this.SQL_DELETE_ENTITY = SQL_DELETE_ENTITY;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Method responsible for delete an entity with their primary keys
     *
     * @param primaryKeys unique id of the entity
     */
    public final void deleteEntity(String[] primaryKeys) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_DELETE_ENTITY)) {

            for (int i = 0; i < primaryKeys.length; i++) {
                statement.setString(i + 1, primaryKeys[i]);
            }

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Deleting entity by pks", e);
        }
    }

    // FUNCTIONS ---------------------------------------------------------------
    /**
     * Function responsible for watch if an entity exists
     *
     * @param primaryKeys unique id of the entity
     * @return true if it exists
     */
    public final boolean entityExists(String[] primaryKeys) {
        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_ENTITY_EXISTS)) {

            for (int i = 0; i < primaryKeys.length; i++) {
                statement.setString(i + 1, primaryKeys[i]);
            }

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new DAOException("Checking if an entity exists by pks", e);
        }

    }

    /**
     * Function responsible for deliver the founded entity
     *
     * @param primaryKeys unique id of the entity
     * @return Entity
     * @throws ObjectNotFoundException if this does'nt exists
     */
    public final Optional<T> getEntityByPrimaryKeys(String[] primaryKeys)
            throws ObjectNotFoundException {

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_ENTITY_BY_PK)) {

            for (int i = 0; i < primaryKeys.length; i++) {
                statement.setString(i + 1, primaryKeys[i]);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                T entity = getEntity(resultSet);

                return Optional.of(entity);
            }

        } catch (SQLException e) {
            throw new DAOException("Getting entity by pks", e);
        }

        throw new ObjectNotFoundException("The entity has not exists");
    }

    /**
     * Function responsible for deliver all entities
     *
     * @return list of all entities
     */
    public final List<T> getAllEntities() {
        List<T> entities = new ArrayList<>();

        try (Connection connection
                = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement statement
                = connection.prepareStatement(SQL_GET_ALL_ENTITIES)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T entity = getEntity(resultSet);
                entities.add(entity);
            }

        } catch (SQLException e) {
            throw new DAOException("getting all entities by pks", e);
        }

        return entities;
    }

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Method responsible for create an entity
     *
     * @param entity to create
     */
    public abstract void createEntity(T entity);

    /**
     * Method responsible for update an entity with their primary keys
     *
     * @param primaryKeys unique id of the entity
     * @param entity entity to update
     */
    public abstract void updateEntity(String[] primaryKeys, T entity);

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
    protected abstract T getEntity(ResultSet resultSet);

}
