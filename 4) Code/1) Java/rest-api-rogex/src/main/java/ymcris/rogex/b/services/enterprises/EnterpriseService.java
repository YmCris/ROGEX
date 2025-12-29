package ymcris.rogex.b.services.enterprises;

import java.util.ArrayList;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.c.dtos.enterprises.NewEnterpriseRequest;
import ymcris.rogex.c.dtos.enterprises.UpdateEnterpriseRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.enterprises.users.EnterpriseUserDAO;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The EnterpriseService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseService extends GenericService<Enterprise> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseService() {
        super(new EnterpriseDAO());
    }

    // SPECIFIC METHODS --------------------------------------------------------
    @Override
    protected Enterprise createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewEnterpriseRequest newEnterpriseRequest = (NewEnterpriseRequest) newObjectRequest;
        newEnterpriseRequest.setPrimaryKeysSQLs(new String[]{newEnterpriseRequest.getName()});

        byte[] coverBytes = null;
        byte[] logoBytes = null;

        String base64Cover = newEnterpriseRequest.getCover();
        String base64Logo = newEnterpriseRequest.getLogo();

        if (StringUtils.isNotBlank(base64Cover)) {
            coverBytes = Base64.getDecoder().decode(base64Cover);
        }
        if (StringUtils.isNotBlank(base64Logo)) {
            logoBytes = Base64.getDecoder().decode(base64Logo);
        }

        if (newEnterpriseRequest.getHiddenAllComments() == null) {
            throw new InvalidUserParametersException("Invalid data sent to create the enterprise");
        }

        //Asign the global comission to the enterprise
        if (newEnterpriseRequest.getSpecificCommission() == null) {
            newEnterpriseRequest.setSpecificCommission(getGlobalCommission());
        }

        // Validation to the "Integrity rule"
        if (newEnterpriseRequest.getSpecificCommission() > getGlobalCommission()) {
            throw new InvalidUserParametersException("The specific commission can't be greater than the global commission");
        }

        Enterprise enterprise = new Enterprise(
                newEnterpriseRequest.getName(),
                newEnterpriseRequest.getDescription(),
                newEnterpriseRequest.getSpecificCommission(),
                newEnterpriseRequest.getHiddenAllComments(),
                logoBytes,
                coverBytes
        );

        if (!enterprise.isValid()) {
            throw new InvalidUserParametersException("Invalid data sent to create the enterprise");
        }

        return enterprise;

    }

    @Override
    protected void updateObject(Enterprise enterprise,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateEnterpriseRequest updateEnterpriseRequest
                = (UpdateEnterpriseRequest) updateObjectRequest;

        byte[] coverBytes = null;
        byte[] logoBytes = null;

        String base64Cover = updateEnterpriseRequest.getCover();
        String base64Logo = updateEnterpriseRequest.getLogo();

        if (StringUtils.isNotBlank(base64Cover)) {
            coverBytes = Base64.getDecoder().decode(base64Cover);
        }
        if (StringUtils.isNotBlank(base64Logo)) {
            logoBytes = Base64.getDecoder().decode(base64Logo);
        }

        if (updateEnterpriseRequest.getDescription() != null) {
            enterprise.setDescription(updateEnterpriseRequest.getDescription());
        }

        if (updateEnterpriseRequest.getSpecificCommission() != null) {

            // Validation to the "Integrity rule"
            if (updateEnterpriseRequest.getSpecificCommission() > getGlobalCommission()) {
                throw new InvalidUserParametersException("The specific commission can't be greater than the global commission");
            }

            enterprise.setSpecificCommission(updateEnterpriseRequest.getSpecificCommission());
        }

        if (logoBytes != null) {
            enterprise.setLogo(logoBytes);
        }

        if (coverBytes != null) {
            enterprise.setCover(coverBytes);
        }

        if (updateEnterpriseRequest.getHiddenAllComments() != null) {
            enterprise.setHiddenAllComments(updateEnterpriseRequest.getHiddenAllComments());
        }

        if (!enterprise.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the enterprise");
        }

    }

    @Override
    public void deleteEntity(String[] primaryKeys)
            throws ObjectNotFoundException {

        getEntity(primaryKeys);
        enterpriseCanBeDeleted(primaryKeys[0]);
        genericDAO.deleteEntity(primaryKeys);
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void enterpriseCanBeDeleted(String enterpriseName) throws ObjectNotFoundException {
        try {
            EnterpriseUserDAO enterpriseUserDAO = new EnterpriseUserDAO();
            VideogameDAO videogameDAO = new VideogameDAO();

            //enterprise_users validations
            ArrayList<EnterpriseUser> enterpriseUsers
                    = (ArrayList<EnterpriseUser>) enterpriseUserDAO.getAllEntities(new String[]{enterpriseName});

            if (!enterpriseUsers.isEmpty()) {
                throw new ObjectNotFoundException("Can't delete this enterprise, because it have enterprise users");
            }

            //videogame validations
            ArrayList<Videogame> videogames
                    = (ArrayList<Videogame>) videogameDAO.getAllEntities(new String[]{enterpriseName});

            if (!videogames.isEmpty()) {
                throw new ObjectNotFoundException("Can't delete this enterprise, because it have videogames");
            }

        } catch (DAOException e) {
            throw new ObjectNotFoundException("Internal exception " + e.getMessage());
        }

    }

    public double getGlobalCommission() {
        SystemConfigDAO configDAO = new SystemConfigDAO();
        SystemConfig config = configDAO.getSystemConfig();
        return config.getGlobalCommissionPercentage();
    }

}
