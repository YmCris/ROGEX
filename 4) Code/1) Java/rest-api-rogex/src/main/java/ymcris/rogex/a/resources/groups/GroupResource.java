package ymcris.rogex.a.resources.groups;

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
import ymcris.rogex.b.services.groups.GroupService;
import ymcris.rogex.c.dtos.groups.GroupResponse;
import ymcris.rogex.c.dtos.groups.NewGroupRequest;
import ymcris.rogex.c.dtos.groups.UpdateGroupRequest;
import ymcris.rogex.e.models.groups.Group;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The GroupResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
@Path("groups")
public class GroupResource extends GenericResource<Group> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGroup(NewGroupRequest newGroupRequest) {
        return createObjectInternal(newGroupRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGroups() {
        return getAllObjectsInternal(null);
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroup(@PathParam("name") String name) {
        return getObjectInternal(new String[]{name});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{name}")
    public Response deleteGroup(@PathParam("name") String name) {

        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{name})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGroup(@PathParam("name") String name,
            UpdateGroupRequest updateGroupRequest) {

        return updateObjectInternal(
                new String[]{name},
                updateGroupRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Group> getService() {
        return new GroupService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<Group> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(group -> (GenericObjectResponse) new GroupResponse(group))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Group entity) {
        return new GroupResponse(entity);
    }
}
