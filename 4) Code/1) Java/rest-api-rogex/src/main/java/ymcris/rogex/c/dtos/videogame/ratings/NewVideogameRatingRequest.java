package ymcris.rogex.c.dtos.videogame.ratings;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewVideogameRatingRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class NewVideogameRatingRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;
    private String userEmail;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int rating;

    // GETTERS -----------------------------------------------------------------
    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getRating() {
        return rating;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
