package ymcris.rogex.a.resources.transaction;

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
import ymcris.rogex.b.services.transaction.TransactionService;
import ymcris.rogex.c.dtos.transaction.NewTransactionRequest;
import ymcris.rogex.c.dtos.transaction.TransactionResponse;
import ymcris.rogex.e.models.transaction.Transaction;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The TransactionResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 22, 2025
 */
@Path("transactions")
public class TransactionResource extends GenericResource<Transaction> {

    // CONTEXT INFO ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // HTTP METHODS ------------------------------------------------------------
    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTransaction(NewTransactionRequest newTransactionRequest) {
        return createObjectInternal(newTransactionRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTransactions(@PathParam("email") String email) {
        return getAllObjectsInternal(new String[]{email});
    }

    @GET
    @Path("{walletName}/{walletBanck}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("walletName") String walletName,
            @PathParam("walletBanck") String walletBanck, @PathParam("email") String email) {
        return getObjectInternal(new String[]{walletName, walletBanck, email});
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{name}")
    public Response deleteTransaction(@PathParam("name") String name) {

        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{walletName}/{walletBanck}/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransaction(@PathParam("walletName") String walletName,
            @PathParam("walletBanck") String walletBanck, @PathParam("email") String email) {

        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Transaction> getService() {
        return new TransactionService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(
            GenericService<Transaction> objectsGetter, String[] parameters) {

        return objectsGetter.getAllEntities(parameters)
                .stream()
                .map(transaction -> (GenericObjectResponse) new TransactionResponse(transaction))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Transaction transaction) {
        return new TransactionResponse(transaction);
    }

}
