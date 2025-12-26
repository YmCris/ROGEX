package ymcris.rogex.a.resources.banner;

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
import ymcris.rogex.b.services.banner.MainBannerService;
import ymcris.rogex.c.dtos.banner.MainBannerResponse;
import ymcris.rogex.c.dtos.banner.NewMainBannerRequest;
import ymcris.rogex.c.dtos.banner.UpdateMainBannerRequest;
import ymcris.rogex.e.models.banner.MainBanner;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericImageResource;
import ymcris.rogex.g.commons.services.GenericImageService;

/**
 * The MainBannerResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
@Path("videogames/banners")
public class MainBannerResource extends GenericImageResource<MainBanner> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBanner(@FormDataParam("data") NewMainBannerRequest newMainBannerRequest,
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails) {

        byte[] fileBytes;

        try (InputStream inputStream = uploadedFileStream) {

            fileBytes = inputStream.readAllBytes();

        } catch (IOException ex) {

            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Image Upload").build();
        }

        return createObjectInternal(newMainBannerRequest, fileBytes);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{link}/image")
    @Produces({"image/png", "image/jpeg"})
    public Response getBannerImage(@PathParam("link") String link) {
        return getImage(new String[]{link});
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBanners() {
        return getAllObjectsInternal(null);
    }

    @GET
    @Path("{link}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBanner(@PathParam("link") String link) {

        return getObjectInternal(new String[]{link});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{link}")
    public Response deleteBanner(@PathParam("link") String link) {
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{link})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{link}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBanner(@PathParam("link") String link,
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("data") UpdateMainBannerRequest updateMainBannerRequest) {

        return update(new String[]{link}, updateMainBannerRequest, uploadedFileStream);
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericImageService<MainBanner> getService() {
        return new MainBannerService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericImageService<MainBanner> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(banner -> (GenericObjectResponse) new MainBannerResponse(banner))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(MainBanner banner) {
        return new MainBannerResponse(banner);
    }

}
