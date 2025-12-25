package ymcris.rogex.c.dtos.videogame.ratings;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateVideogameRatingRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class UpdateVideogameRatingRequest extends GenericUpdateObjectRequest {

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int rating;

    // GETTERS -----------------------------------------------------------------
    public int getRating() {
        return rating;
    }

    // SETTERS -----------------------------------------------------------------
    public void setRating(int rating) {
        this.rating = rating;
    }

}
