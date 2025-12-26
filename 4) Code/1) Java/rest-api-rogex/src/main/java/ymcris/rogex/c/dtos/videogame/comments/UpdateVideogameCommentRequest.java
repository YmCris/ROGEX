package ymcris.rogex.c.dtos.videogame.comments;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateVideogameCommentRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class UpdateVideogameCommentRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String commentText;

    // GETTERS -----------------------------------------------------------------
    public String getCommentText() {
        return commentText;
    }

    // SETTERS -----------------------------------------------------------------
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
