package ymcris.rogex.b.services.system;

import java.util.ArrayList;
import ymcris.rogex.c.dtos.system.UpdateSystemRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericSingletonService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The SystemService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class SystemService extends GenericSingletonService<SystemConfig> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SystemService() {
        super(new SystemConfigDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected void updateEntity(SystemConfig systemConfig,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateSystemRequest updateSystemRequest
                = (UpdateSystemRequest) updateObjectRequest;

        if (updateSystemRequest.getDescription() != null) {
            systemConfig.setDescription(updateSystemRequest.getDescription());
        }

        if (updateSystemRequest.getGlobalCommissionPercentage() != null) {
            systemConfig.setGlobalCommissionPercentage(
                    updateSystemRequest.getGlobalCommissionPercentage());

            // Automatic adjust to all enterprises
            adjustSpecificCommissionsToEnterprises(updateSystemRequest.getGlobalCommissionPercentage());
        }

        if (!systemConfig.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the wallet");
        }

    }

    private void adjustSpecificCommissionsToEnterprises(double newCommission)
            throws InvalidUserParametersException {

        try {

            EnterpriseDAO enterpriseDAO = new EnterpriseDAO();

            ArrayList<Enterprise> enterprises = (ArrayList<Enterprise>) enterpriseDAO.getAllEntities(null);// I shoul use a where but nah

            if (enterprises == null) {
                return;
            }

            for (Enterprise enterprise : enterprises) {

                if (enterprise.getSpecificCommission() > newCommission) {

                    enterprise.setSpecificCommission(newCommission);
                    enterpriseDAO.updateEntity(new String[]{enterprise.getName()}, enterprise);

                }
            }

        } catch (DAOException e) {
            throw new InvalidUserParametersException(e.getMessage());
        }

    }

}
