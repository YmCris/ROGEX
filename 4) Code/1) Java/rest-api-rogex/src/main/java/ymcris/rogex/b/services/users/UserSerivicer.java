package ymcris.rogex.b.services.users;

import ymcris.rogex.c.dtos.users.NewUserRequest;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The UserSerivicer class is the class responsible for
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UserSerivicer extends GenericService<User> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserSerivicer() {
        super(new UserDAO());
        
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected User createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {
        return new User(
                ((NewUserRequest) newObjectRequest).getPhoto(),
                ((NewUserRequest) newObjectRequest).getNickname(),
                ((NewUserRequest) newObjectRequest).getPassword(),
                ((NewUserRequest) newObjectRequest).getBirthDate(),
                ((NewUserRequest) newObjectRequest).getEmail(),
                ((NewUserRequest) newObjectRequest).getPhoneNumber(),
                ((NewUserRequest) newObjectRequest).getCountry(),
                ((NewUserRequest) newObjectRequest).isPublicLibrary()
        );
    }

    @Override
    protected void updateEntity(User user,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        user.setPhoto(((UpdateUserRequest) updateObjectRequest).getPhoto());
        user.setBirthDate(((UpdateUserRequest) updateObjectRequest).getBirthDate());
        user.setPhoneNumber(((UpdateUserRequest) updateObjectRequest).getPhoneNumber());
        user.setCountry(((UpdateUserRequest) updateObjectRequest).getCountry());
        user.setPublicLibrary(((UpdateUserRequest) updateObjectRequest).isPublicLibrary());

        if (!user.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the user");
        }
    }

}
