package ymcris.rogex.g.commons.resources;

import java.util.List;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
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
    public Response createObjectInternal(GenericNewObjectRequest genericNewObjectRequest) {

        GenericJSONResponse jSONResponse = new GenericJSONResponse();
        GenericService<T> objectCreator = createCRUD();

        try {

            objectCreator.createObject(genericNewObjectRequest);

            return Response.status(Response.Status.CREATED).build();

        } catch (InvalidUserParametersException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (ObjectAlreadyExistsException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.CREATED);

        }
    }

    // GET ---------------------------------------------------------------------
    public Response getAllObjectsInternal() {
        GenericService<T> objectsGetter = createCRUD();

        List<GenericObjectResponse> objects = getObjects(objectsGetter);

        return Response.ok(objects).build();
    }

    // DELETE ------------------------------------------------------------------
    // PUT ---------------------------------------------------------------------
    // PATCH--------------------------------------------------------------------
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
