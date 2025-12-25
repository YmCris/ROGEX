package ymcris.rogex.c.dtos.users.videogames;

import ymcris.rogex.e.models.users.videogames.UserVideogame;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The UserVideogameResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class UserVideogameResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean instaled;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserVideogameResponse(UserVideogame userVideogame) {
        this.userEmail = userEmail;
        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
        this.instaled = instaled;
    }

    // GETTERS -----------------------------------------------------------------
    public String getUserEmail() {
        return userEmail;
    }

    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public boolean isInstaled() {
        return instaled;
    }

    // SETTERS -----------------------------------------------------------------
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setInstaled(boolean instaled) {
        this.instaled = instaled;
    }

}
