package ymcris.rogex.b.services.wallets;

import ymcris.rogex.d.daos.wallets.WalletDAO;
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.c.dtos.wallets.NewWalletRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.c.dtos.wallets.UpdateWalletRequest;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The WalletService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class WalletService extends GenericService<Wallet> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public WalletService() {
        super(new WalletDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Wallet createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewWalletRequest newWalletRequest = (NewWalletRequest) newObjectRequest;

        newWalletRequest.setPrimaryKeysSQLs(new String[]{
            newWalletRequest.getName(),
            newWalletRequest.getBanck().name()
        });

        Wallet wallet = new Wallet(
                newWalletRequest.getName(),
                newWalletRequest.getFund(),
                newWalletRequest.getBanck()
        );

        wallet.setUser(new User(null, null, null, null, newWalletRequest.getUserEmail(), null, null, true));

        if (!wallet.isValid()) {
            throw new InvalidUserParametersException("Data sent is invalid");
        }

        return wallet;

    }

    @Override
    protected void updateObject(Wallet wallet,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateWalletRequest updateWalletRequest
                = (UpdateWalletRequest) updateObjectRequest;

        if (updateWalletRequest.getFund() != null) {
            wallet.setFund(updateWalletRequest.getFund());
        }

        if (!wallet.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the wallet");
        }

    }

}
