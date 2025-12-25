package ymcris.rogex.e.models.videogames.ratings;

import org.apache.commons.lang3.StringUtils;

/**
 * The VideogameRating class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameRating {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;
    private String userEmail;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int rating;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameRating(String videogameTitle, String enterpriseName,
            String userEmail, int rating) {

        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
        this.userEmail = userEmail;
        this.rating = rating;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(
                videogameTitle,
                enterpriseName,
                userEmail)
                && rating <= 5 && rating >= 0;
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
