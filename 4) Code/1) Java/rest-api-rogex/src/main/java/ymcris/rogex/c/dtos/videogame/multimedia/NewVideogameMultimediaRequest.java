package ymcris.rogex.c.dtos.videogame.multimedia;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewVideogameMultimediaRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
public class NewVideogameMultimediaRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;

    // GETTERS -----------------------------------------------------------------
    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
