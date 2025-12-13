package ymcris.rogex.a.resources.users;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
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
import ymcris.rogex.b.services.users.UserSerivicer;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.resources.GenericObjectResource;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The UsersResources class is the class responsible for be the "servlet" of the
 * CRUD users
 *
 * @author YmCris
 * @see UserResponse
 * @see NewUserRequest
 * @see UserSerivicer
 * @since Dec 11, 2025
 */
@Path("users")
public class UserResource extends GenericObjectResource<User> {

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
        return getAllObjectsInternal();
    }

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByEmail(@PathParam("email") String email) {

        UserSerivicer userSerivicer = new UserSerivicer();

        try {

            User existingUser = userSerivicer.getEntity(new String[]{email});

            return Response.ok(new UserResponse(existingUser)).build();

        } catch (ObjectNotFoundException e) {

            return Response.status(Response.Status.NOT_FOUND).build();

        }
    }

    // DELETE ------------------------------------------------------------------
    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("email") String email,
            UpdateUserRequest updateUserRequest) {

        UserSerivicer userSerivicer = new UserSerivicer();

        try {

            User userUpdated = userSerivicer.updateObject(new String[]{email}, updateUserRequest);

            return Response.ok(new UserResponse(userUpdated)).build();

        } catch (InvalidUserParametersException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (ObjectNotFoundException ex) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    // PATCH -------------------------------------------------------------------
    @Override
    protected GenericService<User> createCRUD() {
        return new UserSerivicer();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<User> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(user -> (GenericObjectResponse) new UserResponse(user))
                .toList();
    }

}
