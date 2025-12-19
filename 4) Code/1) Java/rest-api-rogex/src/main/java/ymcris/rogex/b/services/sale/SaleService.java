package ymcris.rogex.b.services.sale;

import java.time.LocalDate;
import ymcris.rogex.c.dtos.sale.NewSaleRequest;
import ymcris.rogex.d.daos.sale.SaleDAO;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

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
    protected Sale createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewSaleRequest newSaleRequest = (NewSaleRequest) newObjectRequest;
        newSaleRequest.setPrimaryKeysSQLs(new String[]{newSaleRequest.getUserEmail(),
            newSaleRequest.getVideogameTitle(), newSaleRequest.getEnterpriseName()});

        UserDAO userDAO = new UserDAO();
        VideogameDAO videogameDAO = new VideogameDAO();
        SystemConfigDAO systemConfigDAO = new SystemConfigDAO();

        if (!videogameDAO.entityExists(new String[]{newSaleRequest.getVideogameTitle(),
            newSaleRequest.getEnterpriseName()})) {
            throw new InvalidUserParametersException("The videogame does'nt exists");
        }
        /*
        Videogame videogame = videogameDAO.getEntityByPrimaryKeys(new String[]{newSaleRequest.getVideogameTitle(),
            newSaleRequest.getEnterpriseName()}).get();
         */
        
        if (!userDAO.entityExists(new String[]{newSaleRequest.getUserEmail()})) {
            throw new InvalidUserParametersException("The user does'nt exists");
        }

        Double commission = systemConfigDAO.getSystemConfig().getGlobalCommissionPercentage();

        /*
        Sale group = new Sale(
                videogame.getPrice(),
                LocalDate.now(),
                systemConfigDAO.getSystemConfig().getGlobalCommissionPercentage(),
                0,
                userEmail,
                videogameTitle,
                enterpriseName
        );
        System.out.println("Ejecuntando CREATE ENTITY");

        if (!group.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to create the group isn't valid");
        }
         */
        return null;
    }

    @Override
    protected void updateEntity(Sale entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {
        throw new UnsupportedOperationException("YOU CANT UPDATE A SALE.");
    }

}
