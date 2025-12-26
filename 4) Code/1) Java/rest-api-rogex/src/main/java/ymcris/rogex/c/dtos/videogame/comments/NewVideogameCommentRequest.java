package ymcris.rogex.c.dtos.videogame.comments;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewVideogameCommentRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class NewVideogameCommentRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;
    private String userEmail;
    private String commentText;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private Integer parentCommentId;

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

    public String getCommentText() {
        return commentText;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
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

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

}
