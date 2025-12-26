package ymcris.rogex.a.resources.videogames.ratings;

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
import ymcris.rogex.b.services.videogames.ratings.VideogameRatingService;
import ymcris.rogex.c.dtos.videogame.ratings.NewVideogameRatingRequest;
import ymcris.rogex.c.dtos.videogame.ratings.UpdateVideogameRatingRequest;
import ymcris.rogex.c.dtos.videogame.ratings.VideogameRatingResponse;
import ymcris.rogex.e.models.videogames.ratings.VideogameRating;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The VideogameRatingResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
@Path("videogames/ratings")
public class VideogameRatingResource extends GenericResource<VideogameRating> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVideogameRating(NewVideogameRatingRequest newVideogameRatingRequest) {
        return createObjectInternal(newVideogameRatingRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVideogamesRaitings(@PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        return getAllObjectsInternal(new String[]{videogameTitle, enterpriseName});
    }

    @GET
    @Path("{videogameTitle}/{enterpriseName}/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserVideogameRating(@PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName,
            @PathParam("userEmail") String userEmail) {

        return getObjectInternal(new String[]{videogameTitle, enterpriseName, userEmail});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{videogameTitle}/{enterpriseName}/{userEmail}")
    public Response deleteVideogameRating(
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName,
            @PathParam("userEmail") String userEmail) {

        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{videogameTitle, enterpriseName, userEmail})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{videogameTitle}/{enterpriseName}/{userEmail}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVideogameRating(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName,
            UpdateVideogameRatingRequest updateVideogameRatingRequest) {

        return updateObjectInternal(
                new String[]{videogameTitle, enterpriseName, userEmail},
                updateVideogameRatingRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<VideogameRating> getService() {
        return new VideogameRatingService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<VideogameRating> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(rating -> (GenericObjectResponse) new VideogameRatingResponse(rating))
                .toList();

    }

    @Override
    protected GenericObjectResponse toResponse(VideogameRating video) {
        return new VideogameRatingResponse(video);
    }

}
