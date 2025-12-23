package ymcris.rogex.g.commons.services;

import java.util.List;
import java.util.Optional;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The GenericService class is the class responsible for be the template of all
 * crud of the program
 *
 * @author YmCris
 * @param <T> Parameter of the crud type
 * @since Dec 11, 2025
 */
public abstract class GenericService<T> {

    // REFERENCE VARIABLES -----------------------------------------------------
    protected GenericDAO<T> genericDAO;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GenericService(GenericDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    // SPECIFICS METHODS -------------------------------------------------------
    // CREATE ------------------------------------------------------------------
    /**
     * Funcion responsible for create and object
     *
     * @param newObjectRequest new Object request
     * @return CRUD type
     * @throws InvalidUserParametersException if the parameters are invalids
     * @throws ObjectAlreadyExistsException if the entity already exists
     */
    public T insertObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException, ObjectAlreadyExistsException {

        T entity = extractObject(newObjectRequest);

        if (genericDAO.entityExists(newObjectRequest.getPrimaryKeysSQLs())) {

            throw new ObjectAlreadyExistsException("This already exists");

        }

        genericDAO.createEntity(entity);

        return entity;
    }

    /**
     * Funcion responsible for create and object
     *
     * @param newObjectRequest new Object request
     * @return CRUD type
     * @throws InvalidUserParametersException if the entity is incorrect
     */
    protected T extractObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        try {

            if (newObjectRequest == null) {
                throw new InvalidUserParametersException("Request cannot be null");
            }

            return createObject(newObjectRequest);

        } catch (IllegalArgumentException e) {
            throw new InvalidUserParametersException("Invalid data sent");
        }
    }

    // UPDATE ------------------------------------------------------------------
    /**
     * Function responsible for update an object with their primary keys
     *
     * @param primaryKeys unique id of the entity
     * @param updateObjectRequest updateObjectRequest
     * @return CRUD type
     * @throws InvalidUserParametersException if the parameters are invalid
     * @throws ObjectNotFoundException if the object does'nt exists
     */
    public T updateEntity(String[] primaryKeys,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException, ObjectNotFoundException {

        T entity = getEntity(primaryKeys);

        updateObject(entity, updateObjectRequest);

        genericDAO.updateEntity(primaryKeys, entity);

        return entity;
    }

    // DELETE ------------------------------------------------------------------
    /**
     * Function responsible for delete an entity
     *
     * @param primaryKeys unique id of the entity
     * @throws ObjectNotFoundException if the eentity doesnot exists
     */
    public void deleteEntity(String[] primaryKeys)
            throws ObjectNotFoundException {

        getEntity(primaryKeys);
        genericDAO.deleteEntity(primaryKeys);

    }

    // GET ---------------------------------------------------------------------
    /**
     * Function responsible for get an entity
     *
     * @param primaryKeys unique id of the entity
     * @return CRUD type
     * @throws ObjectNotFoundException if the entity was not found
     */
    public T getEntity(String[] primaryKeys) throws ObjectNotFoundException {
        Optional<T> entityOptional = genericDAO.getEntityByPrimaryKeys(primaryKeys);

        if (entityOptional.isEmpty()) {
            throw new ObjectNotFoundException("This entity does'nt exists");
        }

        return entityOptional.get();
    }

    /**
     * Function responsible for deliver all entities
     *
     * @return List of entities
     */
    public final List<T> getAllEntities() {
        return genericDAO.getAllEntities();
    }

    // ABSTRACT METHODS --------------------------------------------------------
    /**
     * Have to create the user, and add the entity.isValid() whit
     *
     * @param newObjectRequest to extract all parameters to create the entity
     * @return new entity
     * @throws
     * ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException
     */
    protected abstract T createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException;

    /**
     * Function responsible for update an entity with the entity object
     *
     * entity.setLocation(updateObjectRequest.getLocation());
     * entity.setPhoto(updateObjectRequest.getPhoto());
     * entity.setPassword(updateObjectRequest.getPassword()); if
     * (!entity.isValid()) { throw new InvalidUserParametersException("Invalid
     * data to update"); }
     *
     * @param entity Created entity
     * @param updateObjectRequest updateObjectRequest
     * @throws
     * ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException
     */
    protected abstract void updateObject(T entity,
            GenericUpdateObjectRequest updateObjectRequest) throws InvalidUserParametersException;

}
