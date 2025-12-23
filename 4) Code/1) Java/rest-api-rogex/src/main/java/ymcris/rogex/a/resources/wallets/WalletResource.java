package ymcris.rogex.a.resources.wallets;

import java.util.List;
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
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.c.dtos.wallets.WalletResponse;
import ymcris.rogex.c.dtos.wallets.NewWalletRequest;
import ymcris.rogex.b.services.wallets.WalletService;
import ymcris.rogex.c.dtos.wallets.UpdateWalletRequest;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import ymcris.rogex.g.commons.resources.GenericResource;
import ymcris.rogex.g.commons.services.GenericService;

/**
 * The WalletResource class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
@Path("wallets")
public class WalletResource extends GenericResource<Wallet> {

    // HTTP METHODS ------------------------------------------------------------
    @Context
    UriInfo uriInfo;

    // POST --------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createWallet(NewWalletRequest newWalletRequest) {
        return createObjectInternal(newWalletRequest);
    }

    // GET ---------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllWallets() {
        return getAllObjectsInternal();
    }

    @GET
    @Path("{name}/{banck}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWallet(@PathParam("name") String name,
            @PathParam("banck") String banck) {

        return getObjectInternal(new String[]{banck, name});

        /*
        WalletService walletSerivice = new WalletService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            Wallet existingWallet = walletSerivice.getEntity(new String[]{name, banck});

            return Response.ok(new WalletResponse(existingWallet)).build();

        } catch (ObjectNotFoundException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.NOT_FOUND);

        }
         */
    }

    // DELETE ------------------------------------------------------------------
    @DELETE
    @Path("{name}/{banck}")
    public Response deleteWallet(@PathParam("name") String name,
            @PathParam("banck") String banck) {
        /*
        GenericNewObjectRequest pk = new GenericNewObjectRequest();
        pk.setPrimaryKeys(new String[]{name, banck});

        return deleteObjectInternal(pk);*/
        return deleteObjectInternal(
                new GenericNewObjectRequest(new String[]{banck, name})
        );
    }

    // PUT ---------------------------------------------------------------------
    @PUT
    @Path("{name}/{banck}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWallet(@PathParam("name") String name,
            @PathParam("banck") String banck, UpdateWalletRequest updateWalletRequest) {

        return updateObjectInternal(
                new String[]{banck, name},
                updateWalletRequest
        );
        /*
        WalletService walletService = new WalletService();
        GenericJSONResponse jSONResponse = new GenericJSONResponse();

        try {

            Wallet walletUpdated = walletService.updateEntity(new String[]{name, banck},
                    updateWalletRequest);

            return Response.ok(new WalletResponse(walletUpdated)).build();

        } catch (InvalidUserParametersException e) {

            return jSONResponse.sendJSONResponse(e.getMessage(),
                    Response.Status.BAD_REQUEST);

        } catch (ObjectNotFoundException ex) {

            return jSONResponse.sendJSONResponse(ex.getMessage(),
                    Response.Status.NOT_FOUND);

        }
         */
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected GenericService<Wallet> getService() {
        return new WalletService();
    }

    @Override
    protected List<GenericObjectResponse> getObjects(GenericService<Wallet> objectsGetter) {
        return objectsGetter.getAllEntities()
                .stream()
                .map(wallet -> (GenericObjectResponse) new WalletResponse(wallet))
                .toList();
    }

    @Override
    protected GenericObjectResponse toResponse(Wallet wallet) {
        return new WalletResponse(wallet);
    }

}
