package ymcris.rogex.b.services.users;

import java.util.Base64;
import ymcris.rogex.e.models.users.User;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.c.dtos.users.NewUserRequest;
import ymcris.rogex.c.dtos.users.UpdateUserRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
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

        NewUserRequest req = (NewUserRequest) newObjectRequest;

        byte[] photoBytes = null;

        String base64 = ((NewUserRequest) newObjectRequest).getPhoto();
        if (base64 != null && !base64.isBlank()) {
            photoBytes = Base64.getDecoder().decode(base64);
        }

        req.setPrimaryKeys(new String[]{req.getEmail()});

        return new User(
                photoBytes,
                req.getNickname(),
                req.getPassword(),
                req.getBirthDate(),
                req.getEmail(),
                req.getPhoneNumber(),
                req.getCountry(),
                req.isPublicLibrary()
        );
    }

    @Override
    protected void updateEntity(User user,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        byte[] photoBytes = null;

        String base64 = ((UpdateUserRequest) updateObjectRequest).getPhoto();
        if (base64 != null && !base64.isBlank()) {
            photoBytes = Base64.getDecoder().decode(base64);
        }

        if (photoBytes != null) {
            user.setPhoto(photoBytes);
        }
        user.setBirthDate(((UpdateUserRequest) updateObjectRequest).getBirthDate());
        user.setPhoneNumber(((UpdateUserRequest) updateObjectRequest).getPhoneNumber());
        user.setCountry(((UpdateUserRequest) updateObjectRequest).getCountry());
        user.setPublicLibrary(((UpdateUserRequest) updateObjectRequest).isPublicLibrary());

        if (!user.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the user");
        }
    }

}
