package ymcris.rogex.b.services.enterprises;

import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.c.dtos.enterprises.NewEnterpriseRequest;
import ymcris.rogex.c.dtos.enterprises.UpdateEnterpriseRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

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
    protected Enterprise createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewEnterpriseRequest newEnterpriseRequest = (NewEnterpriseRequest) newObjectRequest;
        newEnterpriseRequest.setPrimaryKeys(new String[]{newEnterpriseRequest.getName()});

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
    protected void updateEntity(Enterprise enterprise,
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

}
