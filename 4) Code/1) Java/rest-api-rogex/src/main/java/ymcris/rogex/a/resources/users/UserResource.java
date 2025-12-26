package ymcris.rogex.a.resources.users;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.c.dtos.users.UserResponse;
import ymcris.rogex.c.dtos.users.NewUserRequest;
import ymcris.rogex.b.services.users.UserService;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The UsersResources class is the class responsible for be the "servlet" of the
 * CRUD users
 *
 * @author YmCris
 * @see UserResponse
 * @see NewUserRequest
 * @see UserService
 * @since Dec 11, 2025
 */
@Path("users")
public class UserResource extends GenericResource<User> {

    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(NewUserRequest newUserRequest) {
        System.out.println("CREANDO USUARIOOOOOOOOOOOOO");
        return createObjectInternal(newUserRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return getAllObjectsInternal(null);
    }

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@PathParam("email") String email) {

        UserService userSerivicer = new UserService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            User existingUser = userSerivicer.getEntity(new String[]{email});

            return Response.ok(new UserResponse(existingUser)).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.NOT_FOUND);

        }
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{email}")
    public Response deleteUser(@PathParam("email") String email) {

        GenericNewObjectRequest pk = new GenericNewObjectRequest();
        pk.setPrimaryKeysSQLs(new String[]{email});

        return deleteObjectInternal(pk);
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("email") String email,
            UpdateUserRequest updateUserRequest) {

        UserService userSerivicer = new UserService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            User userUpdated = userSerivicer.updateEntity(new String[]{email}, updateUserRequest);

            return Response.ok(new UserResponse(userUpdated)).build();

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (ObjectNotFoundException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.NOT_FOUND);

        }

    }

    // PATCH -------------------------------------------------------------------
    @Override
    protected GenericService<User> getService() {
        return new UserService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<User> objectsGetter, String[] parameters) {
        
        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(user -> (GenericObjectResponse) new UserResponse(user))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(User user) {
        return new UserResponse(user);
    }

}
