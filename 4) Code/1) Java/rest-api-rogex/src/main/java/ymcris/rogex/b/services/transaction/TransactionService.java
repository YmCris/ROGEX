package ymcris.rogex.b.services.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.transaction.NewTransactionRequest;
import ymcris.rogex.d.daos.transaction.TransactionDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.wallets.WalletDAO;
import ymcris.rogex.e.models.transaction.Transaction;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The TransactionService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 22, 2025
 */
public class TransactionService extends GenericService<Transaction> {

    // LOOGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(TransactionService.class);

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public TransactionService() {
        super(new TransactionDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Transaction createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {
        try {
            NewTransactionRequest newTransactionRequest = (NewTransactionRequest) newObjectRequest;

            Wallet wallet = getWallet(
                    newTransactionRequest.getUserEmail(),
                    newTransactionRequest.getWalletName(),
                    newTransactionRequest.getWalletBanck());

            Transaction transaction = new Transaction(
                    newTransactionRequest.getTransactionDate(),
                    newTransactionRequest.getWalletName(),
                    newTransactionRequest.getDescription(),
                    newTransactionRequest.getWalletBanck(),
                    newTransactionRequest.getUserEmail(),
                    newTransactionRequest.getAmount()
            );

            if (!transaction.isValid()) {
                throw new InvalidUserParametersException("Data sent is invalid");
            }

            updateWallet(wallet, newTransactionRequest.getAmount());

            return transaction;
        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }

    }

    @Override
    protected void updateObject(Transaction entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        throw new UnsupportedOperationException("YOU CANT UPDATE A TRANSACTION.");
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private Wallet getWallet(String email, String walletName,
            BanckType walletBanck)
            throws InvalidUserParametersException {

        UserDAO userDAO = new UserDAO();
        WalletDAO walletDAO = new WalletDAO();

        //1. See if the user exists
        if (!userDAO.entityExists(new String[]{email})) {
            throw new InvalidUserParametersException("The user doesn't exists");
        }

        //2. See if the wallet exists and is of the user
        return walletDAO.getWalletByUserEmailAndWallet(
                email,
                walletName,
                walletBanck.name()
        );

    }

    private void updateWallet(Wallet wallet, Double price) throws InvalidUserParametersException {
        WalletDAO walletDAO = new WalletDAO();

        if (wallet == null) {
            throw new InvalidUserParametersException(
                    "The wallet of the user does'nt exists");
        }

        //3. See if the wallet has the necesary found        
        if (wallet.getFund() < price) {
            throw new InvalidUserParametersException("Your wallet " + wallet.getName()
                    + " of the banck " + wallet.getBanck() + " has'nt sufficient funds");
        } else {
            Double fund = wallet.getFund() - price;
            walletDAO.updateEntity(new String[]{wallet.getName(), String.valueOf(wallet.getBanck())}, fund);
        }
    }

}
