package ymcris.rogex.c.dtos.videogame.ratings;

import ymcris.rogex.e.models.videogames.ratings.VideogameRating;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The VideogameRatingResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameRatingResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;
    private String userEmail;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int rating;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameRatingResponse(VideogameRating videogameRating) {
        this.videogameTitle = videogameRating.getVideogameTitle();
        this.enterpriseName = videogameRating.getEnterpriseName();
        this.userEmail = videogameRating.getUserEmail();
        this.rating = videogameRating.getRating();
    }

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
