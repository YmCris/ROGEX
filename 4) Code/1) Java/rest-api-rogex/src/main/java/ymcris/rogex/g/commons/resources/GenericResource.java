package ymcris.rogex.g.commons.resources;

import java.util.List;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The GenericResource class is the class responsible for be the "servlet" of
 * the Generic CRUD
 *
 * @author YmCris
 * @param <T> type of the api
 * @since Dec 11, 2025
 */
public abstract class GenericResource<T> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    /**
     * Method responsible for create an object send the resonse via JSON
     *
     * @param genericNewObjectRequest request with the necesary data for create
     * this
     * @return Response to server
     */
    public final Response createObjectInternal(
            GenericNewObjectRequest genericNewObjectRequest) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericService<T> objectCreator = getService();

        try {

            objectCreator.insertObject(genericNewObjectRequest);

            return jSONResponse.sendJSONResponse("Created",
                    Response.Status.CREATED);

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (ObjectAlreadyExistsException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.CONFLICT);

        } catch (DAOException exc) {

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

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

            GenericService<T> objectsGetter = getService();

            List<GenericObjectResponse> objects = getObjects(objectsGetter, parameters);

            return Response.ok(objects).build();

        } catch (DAOException exc) {

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
        GenericService<T> service = getService();

        try {

            T entity = service.getEntity(primaryKeys);
            return Response.ok(toResponse(entity)).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND);

        } catch (DAOException exc) {

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
        GenericService<T> deleteObject = getService();

        try {

            deleteObject.deleteEntity(genericNewObjectRequest.getPrimaryKeysSQLs());

            return jSONResponse.sendJSONResponse("Deleted",
                    Response.Status.OK);

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (DAOException exc) {

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
     * @return Response in JSON or entity
     */
    public final Response updateObjectInternal(
            String[] primaryKeys,
            GenericUpdateObjectRequest updateRequest) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericService<T> service = getService();

        try {

            T updated = service.updateEntity(primaryKeys, updateRequest);
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

            return jSONResponse.sendJSONResponse(exc.getMessage(),
                    Response.Status.EXPECTATION_FAILED);

        }
    }

    // AUXILIAR METHODS --------------------------------------------------------
    /**
     * Method responsible for return the specific service
     *
     * @return specific service of the entity
     */
    protected abstract GenericService<T> getService();

    /**
     * Get the list of the objects to sent in the respose | objectsGetter
     * .getAllEntities() .stream() .map(GenericObjectResponse::new) .toList()
     *
     * @param objectsGetter
     * @param parameters
     * @return
     */
    protected abstract List<GenericObjectResponse> getObjects(
            GenericService<T> objectsGetter, String[] parameters);

    /**
     * Method responsible for return a specific entity response
     *
     * @param entity
     * @return
     */
    protected abstract GenericObjectResponse toResponse(T entity);

}
