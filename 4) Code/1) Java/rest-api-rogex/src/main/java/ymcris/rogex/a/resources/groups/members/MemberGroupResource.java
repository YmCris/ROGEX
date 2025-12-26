package ymcris.rogex.a.resources.groups.members;

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
import ymcris.rogex.b.services.groups.MemberGroupService;
import ymcris.rogex.c.dtos.groups.members.MemberGroupResponse;
import ymcris.rogex.c.dtos.groups.members.NewMemberGroupRequest;
import ymcris.rogex.e.models.groups.MemberGroup;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The MemberGroupResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
@Path("groups/members")
public class MemberGroupResource extends GenericResource<MemberGroup> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMemberGroup(NewMemberGroupRequest newMemberGroupRequest) {
        return createObjectInternal(newMemberGroupRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{groupName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMembersOfSomeGroup(@PathParam("groupName") String groupName) {
        return getAllObjectsInternal(new String[]{groupName});
    }

    @GET
    @Path("{email}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroup(@PathParam("email") String email,
            @PathParam("name") String name) {
        return getObjectInternal(new String[]{email, name});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{email}/{name}")
    public Response deleteMemberGroup(@PathParam("email") String email,
            @PathParam("name") String name) {
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{email, name})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGroup(@PathParam("name") String name
    ) {

        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<MemberGroup> getService() {
        return new MemberGroupService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<MemberGroup> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(memberGroup -> (GenericObjectResponse) new MemberGroupResponse(memberGroup))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(MemberGroup memberGroup) {
        return new MemberGroupResponse(memberGroup);
    }

}
