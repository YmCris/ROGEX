package ymcris.rogex.g.commons.resources;

import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.interfaces.Visualizable;

/**
 * The GenericImageResource class is the class responsible for
 *
 * @author YmCris
 * @param <T>
 * @since Dec 26, 2025
 */
public abstract class GenericImageResource<T extends Visualizable> {

    // LOOGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(GenericImageResource.class);

    // POST --------------------------------------------------------------------
    /**
     * Method responsible for create an object send the resonse via JSON
     *
     * @param genericNewObjectRequest request with the necesary data for create
     * this
     * @param image
     * @return Response to server
     */
    public final Response createObjectInternal(
            GenericNewObjectRequest genericNewObjectRequest, byte[] image) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericImageService<T> service = getService();

        try {
            service.insertObject(genericNewObjectRequest, image);

            return jSONResponse.sendJSONResponse("Created",
                    Response.Status.CREATED);

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (ObjectAlreadyExistsException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.CONFLICT);

        } catch (DAOException exc) {
            logger.error("Error executing {}", exc.getOperation(), exc);

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        } catch (IOException exception) {

            return jSONResponse.sendJSONResponse(exception.getMessage(),
                    Response.Status.NOT_ACCEPTABLE);
        }
    }

    // GET ---------------------------------------------------------------------
    /**
     * Method responsible for send all objects
     *
     * @param parameters
     * @return Response with the list of objects respornse
     */
    public final Response getAllObjectsInternal(String[] parameters) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            GenericImageService<T> objectsGetter = getService();

            List<GenericObjectResponse> objects = getObjects(objectsGetter, parameters);

            return Response.ok(objects).build();

        } catch (DAOException exc) {
            logger.error("Error executing {}", exc.getOperation(), exc);

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        }
    }

    /**
     * Method responsible for get an object with pks
     *
     * @param primaryKeys unique atributes of an entity
     * @return Resonse in json
     */
    public final Response getObjectInternal(String[] primaryKeys) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericImageService<T> service = getService();

        try {

            T entity = service.getEntity(primaryKeys);
            return Response.ok(toResponse(entity)).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND);

        } catch (DAOException exc) {

            logger.error("Error executing {}", exc.getOperation(), exc);

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        }
    }

    // DELETE ------------------------------------------------------------------
    /**
     * Method responsible for delete an object
     *
     * @param genericNewObjectRequest request of the data request
     * @return response in JSON
     */
    public final Response deleteObjectInternal(
            GenericNewObjectRequest genericNewObjectRequest) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericImageService<T> deleteObject = getService();

        try {

            deleteObject.deleteEntity(genericNewObjectRequest.getPrimaryKeysSQLs());

            return jSONResponse.sendJSONResponse("Deleted",
                    Response.Status.OK);

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (DAOException exc) {

            logger.error("Error executing {}", exc.getOperation(), exc);

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        }
    }

    // "PUT" -------------------------------------------------------------------
    /**
     * Method responsible for update an entity with pks and updateRequest
     *
     * @param primaryKeys unique identifiers
     * @param updateRequest updateRequest with data to update
     * @param file
     * @return Response in JSON or entity
     */
    public final Response updateObjectInternal(
            String[] primaryKeys,
            GenericUpdateObjectRequest updateRequest, byte[] file) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericImageService<T> service = getService();

        try {

            T updated = service.updateEntity(primaryKeys, file, updateRequest);
            return Response.ok(toResponse(updated)).build();

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.BAD_REQUEST
            );

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND
            );

        } catch (DAOException exc) {
            
            logger.error("Error executing {}", exc.getOperation(), exc);

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        } catch (IOException exception) {

            return jSONResponse.sendJSONResponse(exception.getMessage(),
                    Response.Status.NOT_ACCEPTABLE);
        }
    }

    public Response getImage(String[] primaryKeys) {
        try {

            byte[] image = getService().getImage(primaryKeys);

            return Response.ok(image).build();

        } catch (ObjectNotFoundException e) {

            return Response.status(Response.Status.NOT_FOUND).build();

        }
    }

    public Response update(String[] primaryKeys, GenericUpdateObjectRequest updater,
            InputStream uploadedFileStream) {
        byte[] fileBytes = null;

        if (uploadedFileStream != null) {

            try (InputStream inputStream = uploadedFileStream) {

                fileBytes = inputStream.readAllBytes();

            } catch (IOException ex) {

                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Image Upload").build();
            }

        }

        return updateObjectInternal(
                primaryKeys,
                updater,
                fileBytes
        );

    }

    // AUXILIAR METHODS --------------------------------------------------------
    /**
     * Method responsible for return the specific service
     *
     * @return specific service of the entity
     */
    protected abstract GenericImageService<T> getService();

    /**
     * Get the list of the objects to sent in the respose | objectsGetter
     * .getAllEntities() .stream() .map(GenericObjectResponse::new) .toList()
     *
     * @param objectsGetter
     * @param parameters
     * @return
     */
    protected abstract List<GenericObjectResponse> getObjects(
            GenericImageService<T> objectsGetter, String[] parameters);

    /**
     * Method responsible for return a specific entity response
     *
     * @param entity
     * @return
     */
    protected abstract GenericObjectResponse toResponse(T entity);

}
