package ymcris.rogex.a.resources.sale;

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
import ymcris.rogex.b.services.sale.SaleService;
import ymcris.rogex.c.dtos.sale.NewSaleRequest;
import ymcris.rogex.c.dtos.sale.SaleResponse;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The SaleResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
@Path("sales")
public class SaleResource extends GenericResource<Sale> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSale(NewSaleRequest newSaleRequest) {
        return createObjectInternal(newSaleRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSales() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSale(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {

        return getObjectInternal(new String[]{userEmail, videogameTitle, enterpriseName});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    public Response deleteCategory(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{userEmail}/{videogameTitle}/{enterpriseName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("userEmail") String userEmail,
            @PathParam("videogameTitle") String videogameTitle,
            @PathParam("enterpriseName") String enterpriseName) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Sale> createCRUD() {
        return new SaleService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<Sale> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(sale -> (GenericObjectResponse) new SaleResponse(sale))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Sale sale) {
        return new SaleResponse(sale);
    }

}
