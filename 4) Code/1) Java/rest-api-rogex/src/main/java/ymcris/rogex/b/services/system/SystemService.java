package ymcris.rogex.b.services.system;

import ymcris.rogex.c.dtos.system.UpdateSystemRequest;
import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.d.daos.system.SystemConfigDAO;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The SystemService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class SystemService extends GenericService<SystemConfig> {

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
        }

        if (!systemConfig.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the wallet");
        }

    }

    @Override
    protected SystemConfig createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        throw new UnsupportedOperationException("You can't create a system entity");
    }

}
