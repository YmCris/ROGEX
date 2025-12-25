package ymcris.rogex.c.dtos.users.videogames;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateUserVideogameRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class UpdateUserVideogameRequest extends GenericUpdateObjectRequest {

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean instaled;

    // GETTERS -----------------------------------------------------------------
    public boolean isInstaled() {
        return instaled;
    }

    // SETTERS -----------------------------------------------------------------
    public void setInstaled(boolean instaled) {
        this.instaled = instaled;
    }

}
