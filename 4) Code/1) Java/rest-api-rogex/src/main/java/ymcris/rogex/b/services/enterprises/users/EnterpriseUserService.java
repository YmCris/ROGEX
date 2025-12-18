package ymcris.rogex.b.services.enterprises.users;

import ymcris.rogex.c.dtos.enterprises.users.NewEnterpriseUserRequest;
import ymcris.rogex.c.dtos.enterprises.users.UpdateEnterpriseUserRequest;
import ymcris.rogex.d.daos.enterprises.users.EnterpriseUserDAO;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

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
    protected EnterpriseUser createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewEnterpriseUserRequest newEnterpriseUserRequest = (NewEnterpriseUserRequest) newObjectRequest;
        newEnterpriseUserRequest.setPrimaryKeys(new String[]{newEnterpriseUserRequest.getEmail()});

        EnterpriseUser enterpriseUser = new EnterpriseUser(newEnterpriseUserRequest.getEmail(),
                newEnterpriseUserRequest.getName(),
                newEnterpriseUserRequest.getPassword(),
                newEnterpriseUserRequest.getBirthDate(),
                newEnterpriseUserRequest.getEnterpriseName()
        );

        if (!enterpriseUser.isValid()) {
            throw new InvalidUserParametersException("Data sent to crate the enterprise user is invalid");
        }

        return enterpriseUser;
    }

    @Override
    protected void updateEntity(EnterpriseUser entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateEnterpriseUserRequest updateEnterpriseUserRequest = (UpdateEnterpriseUserRequest) updateObjectRequest;
        
        

    }
}
