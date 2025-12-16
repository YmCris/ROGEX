package ymcris.rogex.g.commons.services;

import java.util.Optional;
import ymcris.rogex.g.commons.dao.GenericSingletonDAO;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The GenericSingletonService class is the class responsible for be template of
 * the service of and singleton enitity
 *
 * @author YmCris
 * @param <T> Singleton Type
 * @since Dec 16, 2025
 */
public abstract class GenericSingletonService<T> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    protected GenericSingletonDAO<T> genericDAO;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GenericSingletonService(GenericSingletonDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Method responsible for get the single entity of the entity
     *
     * @return Entity if this exists and isn't null
     * @throws ObjectNotFoundException if the entity doesn't exists
     */
    public final T getSingletonEntity() throws ObjectNotFoundException {

        Optional<T> entityOptional = genericDAO.getSingleton();

        if (entityOptional.isEmpty()) {
            throw new ObjectNotFoundException("This entity does'nt exists yet");
        }

        return entityOptional.get();
    }

    /**
     * Method responsible for update the singleton entity with the updateRequest
     *
     * @param updateRequest items to update
     * @return Entity updated
     * @throws InvalidUserParametersException if some parameter is invalid
     * @throws ObjectNotFoundException if the entity does'nt founded
     */
    public final T updateSingleton(GenericUpdateObjectRequest updateRequest)
            throws InvalidUserParametersException, ObjectNotFoundException {

        T entity = getSingletonEntity();

        updateEntity(entity, updateRequest);

        genericDAO.updateEntity(null, entity);

        return entity;
    }

    // ABSTRACT METHODS --------------------------------------------------------
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
    protected abstract void updateEntity(T entity,
            GenericUpdateObjectRequest updateObjectRequest) throws InvalidUserParametersException;
}
