package ymcris.rogex.b.services.users;

import ymcris.rogex.e.models.users.User;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.c.dtos.users.NewUserRequest;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The UserService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UserService extends GenericImageService<User> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserService() {
        super(new UserDAO());

    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected User createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewUserRequest newUserRequest = (NewUserRequest) newObjectRequest;
        newUserRequest.setPrimaryKeysSQLs(new String[]{newUserRequest.getEmail()});

        User user = new User(
                null,
                newUserRequest.getNickname(),
                newUserRequest.getPassword(),
                newUserRequest.getBirthDate(),
                newUserRequest.getEmail(),
                newUserRequest.getPhoneNumber(),
                newUserRequest.getCountry(),
                newUserRequest.isPublicLibrary()
        );

        if (!user.isValid()) {

            throw new InvalidUserParametersException("Data sent is invalid");
        }

        return user;
    }

    @Override
    protected void updateObject(User user,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateUserRequest updateUserRequest = (UpdateUserRequest) updateObjectRequest;

        if (updateUserRequest.getBirthDate() != null) {
            user.setBirthDate(updateUserRequest.getBirthDate());
        }

        if (updateUserRequest.getPhoneNumber() != null && !updateUserRequest.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        }

        if (updateUserRequest.getCountry() != null && !updateUserRequest.getCountry().isBlank()) {
            user.setCountry(updateUserRequest.getCountry());
        }

        if (updateUserRequest.isPublicLibrary() != null) {
            user.setPublicLibrary(updateUserRequest.isPublicLibrary());
        }

        if (!user.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the user");
        }
    }

    public User logInUser(String email, String password) {

        UserDAO userDAO = new UserDAO();

        if (!userDAO.logIn(email, password).isEmpty()) {

            return userDAO.logIn(email, password).get();
        }

        return null;

    }

}
