package ymcris.rogex.a.resources.enterprises;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import ymcris.rogex.b.services.enterprises.EnterpriseService;
import ymcris.rogex.c.dtos.enterprises.EnterpriseResponse;
import ymcris.rogex.c.dtos.enterprises.NewEnterpriseRequest;
import ymcris.rogex.c.dtos.enterprises.UpdateEnterpriseRequest;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The EnterpriseResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
@Path("enterprises")
public class EnterpriseResource extends GenericResource<Enterprise> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEnterprise(NewEnterpriseRequest newEnterpriseRequest) {
        return createObjectInternal(newEnterpriseRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEnterprises() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnterprise(@PathParam("name") String name) {

        return getObjectInternal(new String[]{name});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{name}")
    public Response deleteEnterprise(@PathParam("name") String name) {
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{name})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEnterprise(@PathParam("name") String name,
            UpdateEnterpriseRequest updateEnterpriseRequest) {

        return updateObjectInternal(
                new String[]{name},
                updateEnterpriseRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Enterprise> createCRUD() {
        return new EnterpriseService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<Enterprise> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(enterprise -> (GenericObjectResponse) new EnterpriseResponse(enterprise))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Enterprise enterprise) {
        return new EnterpriseResponse(enterprise);
    }

}
