package ymcris.rogex.a.resources.videogame;

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
import ymcris.rogex.b.services.videogame.VideogameService;
import ymcris.rogex.c.dtos.videogame.NewVideogameRequest;
import ymcris.rogex.c.dtos.videogame.UpdateVideogameRequest;
import ymcris.rogex.c.dtos.videogame.VideogameResponse;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The VideogameResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
@Path("videogames")
public class VideogameResource extends GenericResource<Videogame> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVideogame(NewVideogameRequest newVideogameRequest) {
        return createObjectInternal(newVideogameRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVideogames() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{title}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideogame(@PathParam("title") String title,
            @PathParam("enterpriseName") String enterpriseName) {

        return getObjectInternal(new String[]{title, enterpriseName});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{title}/{enterpriseName}/{category}")
    public Response deleteVideogameCategory(@PathParam("title") String title,
            @PathParam("enterpriseName") String enterpriseName,
            @PathParam("category") String category) {
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            VideogameDAO videogameDAO = new VideogameDAO();
            if (videogameDAO.hasCategories(title, enterpriseName)) {
                videogameDAO.deleteCategoryFromVideogame(title, enterpriseName, category);

                return jSONResponse.sendJSONResponse("Deleted",
                        Response.Status.OK);
            }

            return jSONResponse.sendJSONResponse("The videogame can't less than 1 categories",
                    Response.Status.BAD_REQUEST);

        } catch (ObjectNotFoundException e) {
            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);
        }

    }

    @DELETE
    @Path("{title}/{enterpriseName}")
    public Response deleteVideogame(@PathParam("title") String title,
            @PathParam("enterpriseName") String enterpriseName) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{title}/{enterpriseName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVideogame(@PathParam("title") String title,
            @PathParam("enterpriseName") String enterpriseName,
            UpdateVideogameRequest updateVideogameRequest) {

        return updateObjectInternal(
                new String[]{title, enterpriseName},
                updateVideogameRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Videogame> createCRUD() {
        return new VideogameService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<Videogame> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(videogame -> (GenericObjectResponse) new VideogameResponse(videogame))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Videogame videogame) {
        return new VideogameResponse(videogame);
    }

}
