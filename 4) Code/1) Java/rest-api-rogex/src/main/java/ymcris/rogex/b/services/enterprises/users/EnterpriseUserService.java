package ymcris.rogex.b.services.enterprises.users;

import ymcris.rogex.c.dtos.enterprises.users.NewEnterpriseUserRequest;
import ymcris.rogex.c.dtos.enterprises.users.UpdateEnterpriseUserRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.enterprises.users.EnterpriseUserDAO;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The EnterpriseUserService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseUserService extends GenericService<EnterpriseUser> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseUserService() {
        super(new EnterpriseUserDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected EnterpriseUser createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewEnterpriseUserRequest newEnterpriseUserRequest = (NewEnterpriseUserRequest) newObjectRequest;
        newEnterpriseUserRequest.setPrimaryKeysSQLs(new String[]{newEnterpriseUserRequest.getEmail()});
        EnterpriseDAO enterpriseDAO = new EnterpriseDAO();
        Validator validator = new Validator();

        EnterpriseUser enterpriseUser = new EnterpriseUser(
                newEnterpriseUserRequest.getEmail(),
                newEnterpriseUserRequest.getName(),
                newEnterpriseUserRequest.getPassword(),
                newEnterpriseUserRequest.getBirthDate(),
                newEnterpriseUserRequest.getEnterpriseName()
        );

        if (!enterpriseUser.isValid()
                || !validator.isEmail(newEnterpriseUserRequest.getEmail())
                || !validator.isValidDate(newEnterpriseUserRequest.getBirthDate())) {
            throw new InvalidUserParametersException("Data sent to crate the enterprise user is invalid");
        }

        if (!enterpriseDAO.entityExists(new String[]{newEnterpriseUserRequest.getEnterpriseName()})) {
            throw new InvalidUserParametersException("The enterprise does'nt exists");
        }

        return enterpriseUser;
    }

    @Override
    protected void updateObject(EnterpriseUser enterpriseUser,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateEnterpriseUserRequest updateEnterpriseUserRequest
                = (UpdateEnterpriseUserRequest) updateObjectRequest;
        Validator validator = new Validator();

        if (updateEnterpriseUserRequest.getName() != null) {
            enterpriseUser.setName(updateEnterpriseUserRequest.getName());
        }

        if (updateEnterpriseUserRequest.getPassword() != null) {
            enterpriseUser.setPassword(updateEnterpriseUserRequest.getPassword());
        }
        if (updateEnterpriseUserRequest.getBirthDate() != null) {
            enterpriseUser.setBirthDate(updateEnterpriseUserRequest.getBirthDate());
        }

        if (!enterpriseUser.isValid()
                || !validator.isEmail(enterpriseUser.getEmail())
                || !validator.isValidDate(enterpriseUser.getBirthDate())) {
            throw new InvalidUserParametersException("Data sent to update the entity is invalid");
        }
    }

}
