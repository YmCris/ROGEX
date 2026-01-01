package ymcris.rogex.b.services.sale;

import java.time.LocalDate;
import java.time.Period;
import ymcris.rogex.c.dtos.sale.NewSaleRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.sale.SaleDAO;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.users.videogames.UserVideogameDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.d.daos.wallets.WalletDAO;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.e.models.users.videogames.UserVideogame;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The SaleService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class SaleService extends GenericService<Sale> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SaleService() {
        super(new SaleDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Sale createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewSaleRequest newSaleRequest = (NewSaleRequest) newObjectRequest;
        newSaleRequest.setPrimaryKeysSQLs(new String[]{newSaleRequest.getUserEmail(),
            newSaleRequest.getVideogameTitle(), newSaleRequest.getEnterpriseName()});

        UserDAO userDAO = new UserDAO();
        EnterpriseDAO enterpriseDAO = new EnterpriseDAO();
        VideogameDAO videogameDAO = new VideogameDAO();
        SystemConfigDAO systemConfigDAO = new SystemConfigDAO();

        Videogame videogame = null;
        User user = null;
        Enterprise enterprise = null;
        Double commission = null;

        try {

            if (!videogameDAO.entityExists(new String[]{newSaleRequest.getVideogameTitle(),
                newSaleRequest.getEnterpriseName()})) {
                throw new InvalidUserParametersException("The videogame does'nt exists");
            }

            videogame = videogameDAO.getEntityByPrimaryKeys(new String[]{newSaleRequest.getVideogameTitle(),
                newSaleRequest.getEnterpriseName()}).get();

            if (!userDAO.entityExists(new String[]{newSaleRequest.getUserEmail()})) {
                throw new InvalidUserParametersException("The user does'nt exists");
            }
            user = userDAO.getEntityByPrimaryKeys(new String[]{newSaleRequest.getUserEmail()}).get();

            commission = systemConfigDAO.getSystemConfig().getGlobalCommissionPercentage();
            enterprise = enterpriseDAO.getEntityByPrimaryKeys(new String[]{newSaleRequest.getEnterpriseName()}).get();

        } catch (ObjectNotFoundException ex) {
            throw new InvalidUserParametersException(ex.getMessage());
        }

        if (enterprise.getSpecificCommission() != null && enterprise.getSpecificCommission() > 0) {
            commission = enterprise.getSpecificCommission();
        }

        Wallet wallet = getWallet(
                newSaleRequest.getUserEmail(),
                newSaleRequest.getWalletName(),
                newSaleRequest.getWalletBanck()
        );

        double profit = videogame.getPrice() * (commission / 100.0);

        Sale sale = new Sale(
                videogame.getPrice(),
                newSaleRequest.getSaleDate(),
                commission,
                profit,
                newSaleRequest.getUserEmail(),
                videogame.getTitle(),
                videogame.getEnterpriseName(),
                newSaleRequest.getWalletName(),
                newSaleRequest.getWalletBanck()
        );

        if (!sale.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to the sale isn't valid");
        }

        int userAge = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
        System.out.println("Fecha nacimiento: " + user.getBirthDate());

        System.out.println("Edad requerida: " + videogame.getAgeRating().getAgeLimit());
        System.out.println("Edad usuario: " + userAge);
        if (userAge < videogame.getAgeRating().getAgeLimit()) {
            throw new InvalidUserParametersException(
                    "You can't buy this game, you'r too young");
        }

        updateWallet(wallet, videogame.getPrice());
        addVideogame(sale.getUserEmail(), sale.getVideogameTitle(), sale.getEnterpriseName());

        return sale;
    }

    @Override
    protected void updateObject(Sale entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {
        throw new UnsupportedOperationException("YOU CANT UPDATE A SALE.");
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void addVideogame(String userEmail, String videogameTitle, String enterpriseName) {
        UserVideogameDAO userVideogameDAO = new UserVideogameDAO();
        UserVideogame userVideogame = new UserVideogame(
                userEmail, videogameTitle, enterpriseName, false
        );

        userVideogameDAO.createEntity(userVideogame);
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
            walletDAO.updateEntity(new String[]{String.valueOf(wallet.getBanck()), wallet.getName()}, fund);
        }
    }
}
