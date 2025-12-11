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
    private GenericDAO<T> genericDAO;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GenericService(GenericDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    // CREATE ------------------------------------------------------------------
    /**
     * Funcion responsible for create and object
     *
     * @param newObjectRequest new Object request
     * @return CRUD type
     * @throws InvalidUserParametersException if the parameters are invalids
     * @throws ObjectAlreadyExistsException if the entity already exists
     */
    public T createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException, ObjectAlreadyExistsException {

        T entity = extractEntity(newObjectRequest);

        if (genericDAO.existsEntity(newObjectRequest.getPrimaryKeys())) {

            throw new ObjectAlreadyExistsException("Can't use this pks");

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
    private T extractEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        try {

            T entity = createEntity(newObjectRequest);

            return entity;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidUserParametersException("Invalid data sent");
        }
    }

    /**
     * Have to create the user, and add the entity.isValid() whit
     *
     * @param newObjectRequest to extract all parameters to create the entity
     * @return new entity
     * @throws
     * ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException
     */
    protected abstract T createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException;

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
    public T updateObject(String[] primaryKeys,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException, ObjectNotFoundException {

        T entity = getEntity(primaryKeys);

        updateEntity(entity, updateObjectRequest);

        genericDAO.updateEntity(primaryKeys, entity);

        return entity;
    }

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
     */
    protected abstract void updateEntity(T entity,
            GenericUpdateObjectRequest updateObjectRequest);

    // DELETE ------------------------------------------------------------------
    /**
     * Function responsible for delete an entity
     *
     * @param primaryKeys unique id of the entity
     * @return true if the entity was delete
     * @throws ObjectNotFoundException if the eentity doesnot exists
     */
    public boolean deleteEntity(String[] primaryKeys) throws ObjectNotFoundException {
        T entity = getEntity(primaryKeys);

        if (entity != null) {
            genericDAO.deleteEntity(primaryKeys);

            return true;
        }

        return false;
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
        Optional<T> entityOptional = genericDAO.getByEntityByPrimariKeys(primaryKeys);

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
    public List<T> getAllEntities() {
        return genericDAO.getAllEntities();
    }
}
