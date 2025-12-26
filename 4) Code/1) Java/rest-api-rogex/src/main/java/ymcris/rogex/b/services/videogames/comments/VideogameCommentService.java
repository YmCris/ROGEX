package ymcris.rogex.b.services.videogames.comments;

import java.time.LocalDateTime;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.videogame.comments.NewVideogameCommentRequest;
import ymcris.rogex.c.dtos.videogame.comments.UpdateVideogameCommentRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.users.videogames.UserVideogameDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.d.daos.videogames.comments.VideogameCommentDAO;
import ymcris.rogex.e.models.videogames.comments.VideogameComment;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The VideogameCommentService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameCommentService extends GenericService<VideogameComment> {

    // LOGGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(VideogameCommentService.class);
    // INSTANCES ---------------------------------------------------------------
    private final UserDAO userDAO;
    private final EnterpriseDAO enterpriseDAO;
    private final VideogameDAO videogameDAO;
    private final UserVideogameDAO userVideogameDAO;
    private final VideogameCommentDAO commentDAO;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameCommentService() {
        super(new VideogameCommentDAO());
        this.commentDAO = new VideogameCommentDAO();
        this.userVideogameDAO = new UserVideogameDAO();
        this.videogameDAO = new VideogameDAO();
        this.enterpriseDAO = new EnterpriseDAO();
        this.userDAO = new UserDAO();
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected VideogameComment createObject(
            GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewVideogameCommentRequest request = (NewVideogameCommentRequest) newObjectRequest;

        try {

            VideogameComment comment = new VideogameComment(
                    request.getVideogameTitle(),
                    request.getEnterpriseName(),
                    request.getUserEmail(),
                    request.getCommentText(),
                    LocalDateTime.now(),
                    request.getParentCommentId()
            );

            if (!comment.isValid()) {
                throw new InvalidUserParametersException("Invalid Data sent to create the comment");
            }

            //0. Validate if the comment parent exists (if it have)
            validateParentComment(comment.getParentCommentId(), comment.getVideogameTitle(), comment.getEnterpriseName());

            //1. Validate if the user exists
            validateUserExistances(comment.getUserEmail());

            //2. Validate if the enterprise exists
            validateEnterpriseExistances(comment.getEnterpriseName());

            //3. Validate if the videogame exists
            validateVideogameExistances(comment.getEnterpriseName(), comment.getVideogameTitle());

            //4. Validate if the user has the videogame in this library
            validateUserHaveVideogame(comment.getUserEmail(), comment.getEnterpriseName(), comment.getVideogameTitle());

            return comment;

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        } catch (ObjectNotFoundException ex) {
            logger.error("Error executing comment parent id Not found", ex);
            throw new InvalidUserParametersException("Parent comment does not exist");
        }
    }

    @Override
    protected void updateObject(VideogameComment comment,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateVideogameCommentRequest updater = (UpdateVideogameCommentRequest) updateObjectRequest;

        try {

            comment.setCommentText(updater.getCommentText());
            if (!comment.isValid()) {
                throw new InvalidUserParametersException("The text is invalid");
            }

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }
    }

    @Override
    public VideogameComment insertObject(GenericNewObjectRequest request)
            throws InvalidUserParametersException {

        VideogameComment comment = createObject(request);

        genericDAO.createEntity(comment);

        return comment;
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void validateUserExistances(String userEmail)
            throws InvalidUserParametersException {

        if (!userDAO.entityExists(new String[]{userEmail})) {
            throw new InvalidUserParametersException("The user does'nt exist");
        }
    }

    private void validateEnterpriseExistances(String enterpriseName)
            throws InvalidUserParametersException {

        if (!enterpriseDAO.entityExists(new String[]{enterpriseName})) {
            throw new InvalidUserParametersException("The enterprise does'nt exist");
        }
    }

    private void validateVideogameExistances(String enterpriseName,
            String videogameTitle) throws InvalidUserParametersException {

        if (!videogameDAO.entityExists(new String[]{videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The videogame does'nt exist");
        }
    }

    private void validateUserHaveVideogame(String userEmail, String enterpriseName,
            String videogameTitle) throws InvalidUserParametersException {

        if (!userVideogameDAO.entityExists(new String[]{userEmail, videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The user does'nt have the videogame");
        }
    }

    private void validateParentComment(Integer parentId, String videogameTitle,
            String enterpriseName)
            throws InvalidUserParametersException, ObjectNotFoundException {

        if (parentId == null) {
            return;
        }

        Optional<VideogameComment> parent = commentDAO.getEntityByPrimaryKeys(new String[]{parentId.toString()});

        if (parent.isEmpty()) {
            throw new InvalidUserParametersException("Parent comment does not exist");
        }

        VideogameComment parentComment = parent.get();
        if (!parentComment.getVideogameTitle().equals(videogameTitle)
                || !parentComment.getEnterpriseName().equals(enterpriseName)) {
            throw new InvalidUserParametersException("Invalid parent comment");
        }
    }

}
