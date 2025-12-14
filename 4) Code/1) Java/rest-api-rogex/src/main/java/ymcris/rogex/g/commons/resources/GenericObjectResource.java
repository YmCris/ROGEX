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
 * The GenericObjectResource class is the class responsible for be the "servlet"
 * of the Generic CRUD
 *
 * @author YmCris
 * @param <T> type of the api
 * @since Dec 11, 2025
 */
public abstract class GenericObjectResource<T> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    public final Response createObjectInternal(GenericNewObjectRequest genericNewObjectRequest) {

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
    public final Response getAllObjectsInternal() {
        GenericService<T> objectsGetter = createCRUD();

        List<GenericObjectResponse> objects = getObjects(objectsGetter);

        return Response.ok(objects).build();
    }

    // DELETE ------------------------------------------------------------------
    public final Response deleteObjectInternal(GenericNewObjectRequest genericNewObjectRequest) {
        
        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericService<T> deleteObject = createCRUD();

        try {

            deleteObject.deleteEntity(genericNewObjectRequest.getPrimaryKeys());

            return jSONResponse.sendJSONResponse("Deleted",
                    Response.Status.OK);

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);
        }
    }
    
    // PUT ---------------------------------------------------------------------
    public final Response updateObject(GenericUpdateObjectRequest genericUpdateObjectRequest,
            GenericNewObjectRequest genericNewObjectRequest) throws ObjectNotFoundException {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericService<T> updateObject = createCRUD();

        try {

            updateObject.updateObject(genericNewObjectRequest.getPrimaryKeys(), genericUpdateObjectRequest);

            return jSONResponse.sendJSONResponse("Updated",
                    Response.Status.OK);

        } catch (InvalidUserParametersException | ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        }

    }

    // AUXILIAR METHODS --------------------------------------------------------
    protected abstract GenericService<T> createCRUD();

    /**
     * objectsGetter .getAllEntities() .stream()
     * .map(GenericObjectResponse::new) .toList()
     *
     * @param objectsGetter
     * @return
     */
    protected abstract List<GenericObjectResponse> getObjects(GenericService<T> objectsGetter);
}
