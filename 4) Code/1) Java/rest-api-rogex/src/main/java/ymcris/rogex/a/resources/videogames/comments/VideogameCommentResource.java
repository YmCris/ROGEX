package ymcris.rogex.a.resources.videogames.comments;

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
import ymcris.rogex.b.services.videogames.comments.VideogameCommentService;
import ymcris.rogex.c.dtos.videogame.comments.NewVideogameCommentRequest;
import ymcris.rogex.c.dtos.videogame.comments.UpdateVideogameCommentRequest;
import ymcris.rogex.c.dtos.videogame.comments.VideogameCommentResponse;
import ymcris.rogex.e.models.videogames.comments.VideogameComment;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The VideogameCommentResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
@Path("videogames/comments")
public class VideogameCommentResource extends GenericResource<VideogameComment> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVideogameComment(NewVideogameCommentRequest newVideogameCommentRequest) {
        return createObjectInternal(newVideogameCommentRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVideogamesComments(@PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        return getAllObjectsInternal(new String[]{videogameTitle, enterpriseName});
    }

    @GET
    @Path("user/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserVideogameComments(@PathParam("commentId") String commentId) {

        return getObjectInternal(new String[]{commentId});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{commentId}")
    public Response deleteVideogameComment(@PathParam("commentId") String commentId) {

        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{commentId})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{commentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVideogameComment(@PathParam("commentId") String commentId,
            UpdateVideogameCommentRequest updateVideogameCommentRequest) {

        return updateObjectInternal(
                new String[]{commentId},
                updateVideogameCommentRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<VideogameComment> getService() {
        return new VideogameCommentService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<VideogameComment> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(comment -> (GenericObjectResponse) new VideogameCommentResponse(comment))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(VideogameComment comment) {
        return new VideogameCommentResponse(comment);
    }

}
