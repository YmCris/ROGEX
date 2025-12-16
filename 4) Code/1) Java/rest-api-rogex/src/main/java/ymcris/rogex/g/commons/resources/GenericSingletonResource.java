package ymcris.rogex.g.commons.resources;

import jakarta.ws.rs.core.Response;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericSingletonService;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The GenericSingletonResource class is the class responsible for be the
 * template of a singleton entity resource
 *
 * @author YmCris
 * @param <T> type
 * @since Dec 16, 2025
 */
public abstract class GenericSingletonResource<T> {

    // SPECIFIC METHODS --------------------------------------------------------
    public final Response getObjectInternalSingleton() {
        GenericJSONResponse jsonResponse = new GenericJSONResponse();
        GenericSingletonService<T> service = createCRUD();

        try {

            T entity = service.getSingletonEntity();
            return Response.ok(toResponse(entity)).build();

        } catch (ObjectNotFoundException e) {

            return jsonResponse.sendJSONResponse(
                    e.getMessage(),
                    Response.Status.NOT_FOUND
            );
        }
    }

    public final Response updateObjectInternalSingleton(
            GenericUpdateObjectRequest updateRequest) {

        GenericJSONResponse jsonResponse = new GenericJSONResponse();
        GenericSingletonService<T> service = createCRUD();

        try {

            T updated = service.updateSingleton(updateRequest);
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

    // ABSTRACT METHODS --------------------------------------------------------
    protected abstract GenericSingletonService<T> createCRUD();

    protected abstract GenericObjectResponse toResponse(T entity);
}
