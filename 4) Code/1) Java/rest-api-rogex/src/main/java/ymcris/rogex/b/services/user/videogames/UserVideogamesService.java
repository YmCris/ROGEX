package ymcris.rogex.b.services.user.videogames;

import ymcris.rogex.c.dtos.users.videogames.UpdateUserVideogameRequest;
import ymcris.rogex.d.daos.users.videogames.UserVideogameDAO;
import ymcris.rogex.e.models.users.videogames.UserVideogame;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The UserVideogamesService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class UserVideogamesService extends GenericService<UserVideogame> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserVideogamesService() {
        super(new UserVideogameDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected UserVideogame createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {
        throw new UnsupportedOperationException("YOU DON'T NEED USE THIS");
    }

    @Override
    protected void updateObject(UserVideogame userVideogame,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateUserVideogameRequest update = (UpdateUserVideogameRequest) updateObjectRequest;

        userVideogame.setInstaled(update.isInstaled());

    }
}
