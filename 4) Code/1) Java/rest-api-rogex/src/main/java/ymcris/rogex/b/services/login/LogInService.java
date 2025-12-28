package ymcris.rogex.b.services.login;

import ymcris.rogex.b.services.enterprises.users.EnterpriseUserService;
import ymcris.rogex.b.services.users.UserService;
import ymcris.rogex.c.dtos.login.LogInRequest;
import ymcris.rogex.c.dtos.login.LogInResponse;
import ymcris.rogex.c.dtos.login.Role;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The LogInService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 27, 2025
 */
public class LogInService {

    // CONSTANTS ---------------------------------------------------------------
    private static final String ADMIN_EMAIL = "admin@gmail.com";

    // SPECIFIC METHODS --------------------------------------------------------
    public LogInResponse getRole(LogInRequest request) throws ObjectNotFoundException {

        UserService userSerivicer = new UserService();
        EnterpriseUserService enterpriseUserSerivice = new EnterpriseUserService();

        User existingUser = userSerivicer.logInUser(request.getEmail(), request.getPassword());

        if (existingUser != null) {

            if (existingUser.getEmail().equals(ADMIN_EMAIL)) {

                //Return the admin
                return new LogInResponse(
                        existingUser.getEmail(),
                        existingUser.getNickname(),
                        Role.ADMIN
                );

            }

            //Return an normal user
            return new LogInResponse(
                    existingUser.getEmail(),
                    existingUser.getNickname(),
                    Role.USER
            );
        }

        EnterpriseUser existingEnterpriseUser
                = enterpriseUserSerivice.logInEnterpriseUser(
                        request.getEmail(),
                        request.getPassword()
                );

        if (existingEnterpriseUser != null) {
            //Return an enterprise role
            return new LogInResponse(
                    existingEnterpriseUser.getEmail(),
                    existingEnterpriseUser.getName(),
                    Role.ENTERPRISE
            );
        }
        throw new ObjectNotFoundException("The user does'nt exist");
    }

}
