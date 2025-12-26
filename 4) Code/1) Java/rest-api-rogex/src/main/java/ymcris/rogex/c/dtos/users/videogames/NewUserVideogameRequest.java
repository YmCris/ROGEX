package ymcris.rogex.c.dtos.users.videogames;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewUserVideogameRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class NewUserVideogameRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String userEmail;

    // GETTERS -----------------------------------------------------------------
    public String getUserEmail() {
        return userEmail;
    }

    // SETTERS -----------------------------------------------------------------
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
