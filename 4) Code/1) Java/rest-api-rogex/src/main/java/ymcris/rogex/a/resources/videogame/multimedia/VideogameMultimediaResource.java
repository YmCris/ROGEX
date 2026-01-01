package ymcris.rogex.a.resources.videogame.multimedia;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ymcris.rogex.b.services.videogame.multimedia.VideogameMultimediaService;
import ymcris.rogex.c.dtos.videogame.multimedia.NewVideogameMultimediaRequest;
import ymcris.rogex.c.dtos.videogame.multimedia.UpdateVideogameMultimediaRequest;
import ymcris.rogex.c.dtos.videogame.multimedia.VideogameMultimediaResponse;
import ymcris.rogex.e.models.videogame.multimedia.VideogameMultimedia;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericImageResource;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The VideogameMultimediaResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
@Path("videogames/multimedia")
public class VideogameMultimediaResource extends GenericImageResource<VideogameMultimedia> {

    // CONTTEXT ----------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVideogameMultimedia(
            @FormDataParam("data") NewVideogameMultimediaRequest newRequest,
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails) {

        byte[] fileBytes = null;
        if (uploadedFileStream != null) {
            try (InputStream inputStream = uploadedFileStream) {

                fileBytes = inputStream.readAllBytes();

                String mime = bodyPart.getMediaType().toString();
                if (!mime.startsWith("image/")) {
                    return Response.status(400).build();
                }

            } catch (IOException ex) {

                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Image Upload").build();
            }
        }

        return createObjectInternal(newRequest, fileBytes);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{title}/{enterprise}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVideogameMultimedia(
            @PathParam("title") String title,
            @PathParam("enterprise") String enterprise) {

        return getAllObjectsInternal(new String[]{title, enterprise});
    }

    @GET
    @Path("{id}/image")
    @Produces({"image/jpeg", "image/png"})
    public Response getImage(@PathParam("id") String id) {
        return getImage(new String[]{id});
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideogameMultimedia(@PathParam("id") String id) {

        VideogameMultimediaService service = new VideogameMultimediaService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            VideogameMultimedia existingVideogameMultimedia = service.getEntity(new String[]{id});

            return Response.ok(new VideogameMultimediaResponse(existingVideogameMultimedia)).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.NOT_FOUND);

        }
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{id}")
    public Response deleteMultimedia(@PathParam("id") String id) {

        GenericNewObjectRequest pk = new GenericNewObjectRequest();
        pk.setPrimaryKeysSQLs(new String[]{id});

        return deleteObjectInternal(pk);
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("data") UpdateVideogameMultimediaRequest update,
            @PathParam("id") String id) {

        String mime = bodyPart.getMediaType().toString();
        if (!mime.startsWith("image/")) {
            return Response.status(400).build();
        }

        return update(new String[]{id}, update, uploadedFileStream);

    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericImageService<VideogameMultimedia> getService() {
        return new VideogameMultimediaService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericImageService<VideogameMultimedia> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(multimedia -> (GenericObjectResponse) new VideogameMultimediaResponse(multimedia))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(VideogameMultimedia multimedia) {
        return new VideogameMultimediaResponse(multimedia);
    }

}
