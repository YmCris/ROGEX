package ymcris.rogex.a.resources.enterprises.users;

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
import ymcris.rogex.b.services.enterprises.users.EnterpriseUserService;
import ymcris.rogex.c.dtos.enterprises.users.EnterpriseUserResponse;
import ymcris.rogex.c.dtos.enterprises.users.NewEnterpriseUserRequest;
import ymcris.rogex.c.dtos.enterprises.users.UpdateEnterpriseUserRequest;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The EnterpriseUserResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
@Path("users/enterprises")
public class EnterpriseUserResource extends GenericResource<EnterpriseUser> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEnterpriseUser(NewEnterpriseUserRequest newEnterpriseUserRequest) {
        return createObjectInternal(newEnterpriseUserRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersEnterprise() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserEnterprise(@PathParam("email") String email) {

        return getObjectInternal(new String[]{email});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{email}")
    public Response deleteEnterprise(@PathParam("email") String email) {
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{email})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEnterprise(@PathParam("email") String name,
            UpdateEnterpriseUserRequest updateUserEnterpriseRequest) {

        return updateObjectInternal(
                new String[]{name},
                updateUserEnterpriseRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<EnterpriseUser> createCRUD() {
        return new EnterpriseUserService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<EnterpriseUser> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(userEnterprise -> (GenericObjectResponse) new EnterpriseUserResponse(userEnterprise))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(EnterpriseUser enterpriseUser) {
        return new EnterpriseUserResponse(enterpriseUser);
    }

}
