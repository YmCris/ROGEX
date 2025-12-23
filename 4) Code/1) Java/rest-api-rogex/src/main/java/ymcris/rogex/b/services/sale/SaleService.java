package ymcris.rogex.b.services.sale;

import java.time.LocalDate;
import ymcris.rogex.c.dtos.sale.NewSaleRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.sale.SaleDAO;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.e.models.videogame.Videogame;
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

            commission = systemConfigDAO.getSystemConfig().getGlobalCommissionPercentage();
            enterprise = enterpriseDAO.getEntityByPrimaryKeys(new String[]{newSaleRequest.getEnterpriseName()}).get();

        } catch (ObjectNotFoundException ex) {
            throw new InvalidUserParametersException(ex.getMessage());
        }

        if (enterprise.getSpecificCommission() != null && enterprise.getSpecificCommission() > 0) {
            commission = enterprise.getSpecificCommission();
        }

        double profit = (videogame.getPrice() - (videogame.getPrice() * (commission / 100)));

        Sale sale = new Sale(
                videogame.getPrice(),
                newSaleRequest.getSaleDate(),
                commission,
                profit,
                newSaleRequest.getUserEmail(),
                videogame.getTitle(),
                videogame.getEnterpriseName()
        );

        System.out.println(sale.getVideogamePrice() + " " + sale.getSaleDate() + " " + sale.getCommissionPercentage() + " " + sale.getProfit()
                + " " + sale.getUserEmail() + " " + sale.getVideogameTitle() + " " + sale.getEnterpriseName());
        if (!sale.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to the sale isn't valid");
        }
        return sale;
    }

    @Override
    protected void updateObject(Sale entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {
        throw new UnsupportedOperationException("YOU CANT UPDATE A SALE.");
    }

}
