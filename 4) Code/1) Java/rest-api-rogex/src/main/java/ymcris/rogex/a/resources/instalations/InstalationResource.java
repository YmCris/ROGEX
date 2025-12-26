package ymcris.rogex.a.resources.instalations;

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
import ymcris.rogex.b.services.installations.VideogameInstalationService;
import ymcris.rogex.c.dtos.instalations.NewVideogameInstalationRequest;
import ymcris.rogex.c.dtos.instalations.UpdateVideogameInstalationRequest;
import ymcris.rogex.c.dtos.instalations.VideogameInstalationResponse;
import ymcris.rogex.e.models.instalation.VideogameInstalation;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The InstalationResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
@Path("instalations")
public class InstalationResource extends GenericResource<VideogameInstalation> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInstalation(NewVideogameInstalationRequest newVideogameInstalationRequest) {
        return createObjectInternal(newVideogameInstalationRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("all/{userEmail}/{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInstalationsOfUserVideogame(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        return getAllObjectsInternal(new String[]{userEmail, videogameTitle, enterpriseName});
    }

    @GET
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserVideogameInstalation(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        return getObjectInternal(new String[]{userEmail, videogameTitle, enterpriseName});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    public Response delete(@PathParam("email") String email) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}/{instalation}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInstalation(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName,
            @PathParam("instalation") String instalation,
            UpdateVideogameInstalationRequest updateVideogameInstalationRequest) {

        return updateObjectInternal(
                new String[]{userEmail, videogameTitle, enterpriseName, instalation},
                updateVideogameInstalationRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<VideogameInstalation> getService() {
        return new VideogameInstalationService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<VideogameInstalation> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(instalation -> (GenericObjectResponse) new VideogameInstalationResponse(instalation))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(VideogameInstalation videogame) {
        return new VideogameInstalationResponse(videogame);
    }

}
