package ymcris.rogex.g.commons.resources;

import java.util.List;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ymcris.rogex.g.commons.services.GenericService;
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
        GenericService<T> objectCreator = createCRUD();

        try {

            objectCreator.createObject(genericNewObjectRequest);

            return jSONResponse.sendJSONResponse("Created",
                    Response.Status.CREATED);

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (ObjectAlreadyExistsException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.CONFLICT);

        }
    }

    // GET ---------------------------------------------------------------------
    /**
     * Method responsible for send all objects
     *
     * @return Response with the list of objects respornse
     */
    public final Response getAllObjectsInternal() {
        GenericService<T> objectsGetter = createCRUD();

        List<GenericObjectResponse> objects = getObjects(objectsGetter);

        return Response.ok(objects).build();
    }

    /**
     * Method responsible for get an object with pks
     *
     * @param primaryKeys unique atributes of an entity
     * @return Resonse in json
     */
    public final Response getObjectInternal(String[] primaryKeys) {

        GenericJSONResponse jsonResponse = new GenericJSONResponse();
        GenericService<T> service = createCRUD();

        try {

            T entity = service.getEntity(primaryKeys);
            return Response.ok(toResponse(entity)).build();

        } catch (ObjectNotFoundException e) {

            return jsonResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND
            );
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
        GenericService<T> deleteObject = createCRUD();

        try {

            deleteObject.deleteEntity(genericNewObjectRequest.getPrimaryKeysSQLs());

            return jSONResponse.sendJSONResponse("Deleted",
                    Response.Status.OK);

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);
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

        GenericJSONResponse jsonResponse = new GenericJSONResponse();
        GenericService<T> service = createCRUD();

        try {

            T updated = service.updateObject(primaryKeys, updateRequest);
            return Response.ok(toResponse(updated)).build();

        } catch (InvalidUserParametersException e) {

            return jsonResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.BAD_REQUEST
            );

        } catch (ObjectNotFoundException e) {

            return jsonResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND
            );
        }
    }

    // AUXILIAR METHODS --------------------------------------------------------
    /**
     * Method responsible for return the specific service
     *
     * @return specific service of the entity
     */
    protected abstract GenericService<T> createCRUD();

    /**
     * Get the list of the objects to sent in the respose | objectsGetter
     * .getAllEntities() .stream() .map(GenericObjectResponse::new) .toList()
     *
     * @param objectsGetter
     * @return
     */
    protected abstract List<GenericObjectResponse> getObjects(GenericService<T> objectsGetter);

    /**
     * Method responsible for return a specific entity response
     *
     * @param entity
     * @return
     */
    protected abstract GenericObjectResponse toResponse(T entity);

}
