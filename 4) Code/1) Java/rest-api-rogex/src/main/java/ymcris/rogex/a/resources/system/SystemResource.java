package ymcris.rogex.a.resources.system;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import ymcris.rogex.b.services.system.SystemService;
import ymcris.rogex.c.dtos.system.SystemResponse;
import ymcris.rogex.c.dtos.system.UpdateSystemRequest;
import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The SystemResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
@Path("system/config")
public class SystemResource extends GenericResource<SystemConfig> {

    @Context
    UriInfo uriInfo;

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSystemConfig() {

        return getObjectInternalSingleton();
    }

    // POST --------------------------------------------------------------------
    @POST
    public Response create() {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    public Response delete() {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSystemConfig(
            UpdateSystemRequest updateSystemRequest) {

        return updateObjectInternalSingleton(updateSystemRequest);

    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<SystemConfig> createCRUD() {
        return new SystemService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<SystemConfig> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(systemConfig -> (GenericObjectResponse) new SystemResponse(systemConfig))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(SystemConfig systemConfig) {
        return new SystemResponse(systemConfig);
    }

}
