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
import java.io.IOException;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.c.dtos.users.UserResponse;
import ymcris.rogex.c.dtos.users.NewUserRequest;
import ymcris.rogex.b.services.users.UserService;
import ymcris.rogex.c.dtos.banner.UpdateMainBannerRequest;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.resources.GenericImageResource;
import ymcris.rogex.g.commons.response.GenericJSONResponse;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

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
public class UserResource extends GenericImageResource<User> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@FormDataParam("data") NewUserRequest newUserRequest,
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails) {

        byte[] fileBytes = null;
        if (uploadedFileStream != null) {//<- In the sign up the photo is optional
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

        return createObjectInternal(newUserRequest, fileBytes);
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(
            @FormDataParam("fileObject") InputStream uploadedFileStream,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("data") UpdateMainBannerRequest updateMainBannerRequest,
            @PathParam("email") String email,
            UpdateUserRequest updateUserRequest) {

        String mime = bodyPart.getMediaType().toString();
        if (!mime.startsWith("image/")) {
            return Response.status(400).build();
        }

        return update(new String[]{email}, updateMainBannerRequest, uploadedFileStream);

    }

    // PATCH -------------------------------------------------------------------
    @Override
    protected GenericImageService<User> getService() {
        return new UserService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericImageService<User> objectsGetter, String[] parameters) {

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
