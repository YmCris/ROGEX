package ymcris.rogex.c.dtos.videogame.comments;

import java.time.LocalDateTime;
import ymcris.rogex.e.models.videogames.comments.VideogameComment;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The VideogameCommentResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameCommentResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String videogameTitle;
    private String enterpriseName;
    private String userEmail;
    private String commentText;
    private LocalDateTime commentDate;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private int id;
    private Integer parentCommentId;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameCommentResponse(VideogameComment videogameComment) {
        this.videogameTitle = videogameComment.getVideogameTitle();
        this.enterpriseName = videogameComment.getEnterpriseName();
        this.userEmail = videogameComment.getUserEmail();
        this.commentText = videogameComment.getCommentText();
        this.commentDate = videogameComment.getCommentDate();
        this.id = videogameComment.getId();
        this.parentCommentId = videogameComment.getParentCommentId();
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

    public String getCommentText() {
        return commentText;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public int getId() {
        return id;
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

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

}
