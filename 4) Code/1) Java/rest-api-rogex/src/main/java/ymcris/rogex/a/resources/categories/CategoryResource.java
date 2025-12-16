package ymcris.rogex.a.resources.categories;

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
import ymcris.rogex.b.services.categories.CategoryService;
import ymcris.rogex.c.dtos.category.CategoryResponse;
import ymcris.rogex.c.dtos.category.NewCategoryRequest;
import ymcris.rogex.c.dtos.category.UpdateCategoryRequest;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The CategoryResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
@Path("categories")
public class CategoryResource extends GenericResource<Category> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(NewCategoryRequest newCategoryRequest) {
        return createObjectInternal(newCategoryRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") String id) {

        return getObjectInternal(new String[]{id});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") String id) {
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{id})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") String id,
            UpdateCategoryRequest updateCategoryRequest) {

        return updateObjectInternal(
                new String[]{id},
                updateCategoryRequest
        );
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Category> createCRUD() {
        return new CategoryService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<Category> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(category -> (GenericObjectResponse) new CategoryResponse(category))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Category entity) {
        return new CategoryResponse(entity);
    }

}
