package ymcris.rogex.g.commons.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;
import ymcris.rogex.g.commons.dao.GenericDAO;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.interfaces.Visualizable;

/**
 * The GenericImageService class is the class responsible for
 *
 * @author YmCris
 * @param <T>
 * @since Dec 26, 2025
 */
public abstract class GenericImageService<T extends Visualizable> {

    // REFERENCE VARIABLES -----------------------------------------------------
    protected GenericDAO<T> genericDAO;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public GenericImageService(GenericDAO<T> genericDAO) {
        this.genericDAO = genericDAO;
    }

    // SPECIFICS METHODS -------------------------------------------------------
    // CREATE ------------------------------------------------------------------
    /**
     * Funcion responsible for create and object
     *
     * @param newObjectRequest new Object request
     * @param file
     * @return CRUD type
     * @throws InvalidUserParametersException if the parameters are invalids
     * @throws ObjectAlreadyExistsException if the entity already exists
     * @throws java.io.IOException
     */
    public T insertObject(GenericNewObjectRequest newObjectRequest, byte[] file)
            throws InvalidUserParametersException, ObjectAlreadyExistsException, IOException {

        T entity = extractObject(newObjectRequest);

        setImage(entity, file);

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
     * @throws java.io.IOException
     */
    protected T extractObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException, IOException {

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
     * @param file
     * @param updateObjectRequest updateObjectRequest
     * @return CRUD type
     * @throws InvalidUserParametersException if the parameters are invalid
     * @throws ObjectNotFoundException if the object does'nt exists
     * @throws java.io.IOException
     */
    public T updateEntity(String[] primaryKeys, byte[] file,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException, ObjectNotFoundException, IOException {

        T entity = getEntity(primaryKeys);

        if (file != null && file.length > 0) {
            setImage(entity, file);
        }

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
     * @param parameters to get some entities
     * @return List of entities
     */
    public final List<T> getAllEntities(String[] parameters) {
        return genericDAO.getAllEntities(parameters);
    }

    // IMAGE METHODS -----------------------------------------------------------
    protected final void setImage(T entity, byte[] image)
            throws InvalidUserParametersException, IOException {

        if (image == null || image.length == 0) {
            throw new InvalidUserParametersException("The image is invalid");
        }

        boolean isImage;

        try {

            isImage = ImageIO.read(new ByteArrayInputStream(image)) != null;

        } catch (IOException e) {

            throw new InvalidUserParametersException("Invalid image file");

        }

        if (!isImage) {

            throw new InvalidUserParametersException("File is not an image");

        }

        entity.setPhoto(image);
        entity.setImage(true);
    }

    public byte[] getImage(String[] primaryKeys)
            throws ObjectNotFoundException {

        T entity = getEntity(primaryKeys);

        byte[] image = entity.getPhoto();

        if (image == null || image.length == 0) {
            throw new ObjectNotFoundException("Entity has no image");
        }

        return image;
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
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException;

}
