package ymcris.rogex.a.resources.users.videogames;

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
import ymcris.rogex.b.services.user.videogames.UserVideogamesService;
import ymcris.rogex.c.dtos.users.videogames.NewUserVideogameRequest;
import ymcris.rogex.c.dtos.users.videogames.UpdateUserVideogameRequest;
import ymcris.rogex.c.dtos.users.videogames.UserVideogameResponse;
import ymcris.rogex.e.models.users.videogames.UserVideogame;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The UserVideogameResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
@Path("users/videogames")
public class UserVideogameResource extends GenericResource<UserVideogame> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserVideogame(NewUserVideogameRequest newUserVideogameRequest) {
        throw new UnsupportedOperationException("YOU CANT CREATE THIS");
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserVideogames(@PathParam("email") String email) {
        return getAllObjectsInternal(new String[]{email});
    }

    @GET
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserVideogame(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {
        return getObjectInternal(new String[]{userEmail, videogameTitle, enterpriseName});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    public Response deleteUserVideogame(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        throw new UnsupportedOperationException("YOU CANT DELETE SOME VIDOEGAME OF YOUR LIBRARY");
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserVideogame(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName, UpdateUserVideogameRequest updateUserVideogameRequest) {

        return updateObjectInternal(
                new String[]{userEmail, videogameTitle, enterpriseName},
                updateUserVideogameRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<UserVideogame> getService() {
        return new UserVideogamesService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<UserVideogame> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(userVideogame -> (GenericObjectResponse) new UserVideogameResponse(userVideogame))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(UserVideogame userVideogame) {
        return new UserVideogameResponse(userVideogame);
    }

}
