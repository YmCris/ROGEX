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
 * The UserSerivice class is the class responsible for
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UserSerivice extends GenericService<User> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserSerivice() {
        super(new UserDAO());

    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected User createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewUserRequest newUserRequest = (NewUserRequest) newObjectRequest;

        byte[] photoBytes = null;

        String base64 = ((NewUserRequest) newObjectRequest).getPhoto();
        if (base64 != null && !base64.isBlank()) {
            photoBytes = Base64.getDecoder().decode(base64);
        }

        newUserRequest.setPrimaryKeys(new String[]{newUserRequest.getEmail()});

        User user = new User(
                photoBytes,
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
        UpdateUserRequest req = (UpdateUserRequest) updateObjectRequest;

        if (req.getBirthDate() != null) {
            user.setBirthDate(req.getBirthDate());
        }

        if (req.getPhoneNumber() != null && !req.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(req.getPhoneNumber());
        }

        if (req.getCountry() != null && !req.getCountry().isBlank()) {
            user.setCountry(req.getCountry());
        }

        if (req.isPublicLibrary() != null) {
            user.setPublicLibrary(req.isPublicLibrary());
        }

        if (!user.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the user");
        }
    }

}
