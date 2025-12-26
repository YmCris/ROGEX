package ymcris.rogex.e.models.videogames.comments;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The VideogameComment class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameComment {

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
    public VideogameComment(String videogameTitle, String enterpriseName,
            String userEmail, String commentText, LocalDateTime commentDate,
            int id, Integer parentCommentId) {

        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
        this.userEmail = userEmail;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.id = id;
        this.parentCommentId = parentCommentId;
    }

    public VideogameComment(String videogameTitle, String enterpriseName,
            String userEmail, String commentText, LocalDateTime commentDate,
            Integer parentCommentId) {

        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
        this.userEmail = userEmail;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.parentCommentId = parentCommentId;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        Validator validator = new Validator();
        return !StringUtils.isAnyBlank(
                videogameTitle,
                enterpriseName,
                userEmail,
                commentText)
                && validator.isValidLocalDateTime(commentDate);
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
