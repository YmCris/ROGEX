package ymcris.rogex.a.resources.invitations;

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
import ymcris.rogex.b.services.invitations.InvitationService;
import ymcris.rogex.c.dtos.invitations.InvitationResponse;
import ymcris.rogex.c.dtos.invitations.NewInvitationRequest;
import ymcris.rogex.e.models.invitations.Invitation;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The InvitationResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
@Path("invitations")
public class InvitationResource extends GenericResource<Invitation> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInvitation(NewInvitationRequest newInvitationRequest) {
        return createObjectInternal(newInvitationRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{receiverEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInvitations(@PathParam("receiverEmail") String receiverEmail) {
        return getAllObjectsInternal(new String[]{receiverEmail});
    }

    @GET
    @Path("{groupName}/{receiverEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvitationForUserToGroup(@PathParam("groupName") String groupName,
            @PathParam("receiverEmail") String receiverEmail) {
        return getObjectInternal(new String[]{groupName, receiverEmail});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{groupName}/{receiverEmail}")
    public Response deleteInvitation(@PathParam("groupName") String groupName,
            @PathParam("receiverEmail") String receiverEmail) {

        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{groupName, receiverEmail})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{groupName}/{receiverEmail}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransaction(@PathParam("groupName") String groupName,
            @PathParam("receiverEmail") String receiverEmail) {

        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Invitation> getService() {
        return new InvitationService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<Invitation> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(invitation -> (GenericObjectResponse) new InvitationResponse(invitation))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Invitation Invitation) {
        return new InvitationResponse(Invitation);
    }

}
