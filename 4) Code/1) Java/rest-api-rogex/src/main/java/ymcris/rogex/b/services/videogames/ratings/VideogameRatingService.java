package ymcris.rogex.b.services.videogames.ratings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.videogame.ratings.NewVideogameRatingRequest;
import ymcris.rogex.c.dtos.videogame.ratings.UpdateVideogameRatingRequest;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.users.videogames.UserVideogameDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.d.daos.videogames.ratings.VideogameRatingDAO;
import ymcris.rogex.e.models.videogames.ratings.VideogameRating;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The VideogameRatingService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameRatingService extends GenericService<VideogameRating> {

    // LOGGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(VideogameRatingService.class);

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameRatingService() {
        super(new VideogameRatingDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected VideogameRating createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {
        try {

            NewVideogameRatingRequest request = (NewVideogameRatingRequest) newObjectRequest;

            request.setPrimaryKeysSQLs(new String[]{
                request.getVideogameTitle(),
                request.getEnterpriseName(),
                request.getUserEmail()
            });

            VideogameRating videogameRating = new VideogameRating(
                    request.getVideogameTitle(),
                    request.getEnterpriseName(),
                    request.getUserEmail(),
                    request.getRating()
            );

            //0. Not null or invalid parametes
            if (!videogameRating.isValid()) {
                throw new InvalidUserParametersException("Invalid data sent");
            }

            //1. Validate if the user exists
            validateUserExistances(videogameRating.getUserEmail());

            //2. Validate if the enterprise exists
            validateEnterpriseExistances(videogameRating.getEnterpriseName());

            //3. Validate if the videogame exists
            validateVideogameExistances(videogameRating.getEnterpriseName(), videogameRating.getVideogameTitle());

            //4. Validate if the user has the videogame in this library
            validateUserHaveVideogame(videogameRating.getUserEmail(), videogameRating.getEnterpriseName(), videogameRating.getVideogameTitle());

            return videogameRating;

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }
    }

    @Override
    protected void updateObject(VideogameRating videogameRating,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        try {
            UpdateVideogameRatingRequest updater = (UpdateVideogameRatingRequest) updateObjectRequest;

            videogameRating.setRating(updater.getRating());

            if (!videogameRating.isValid()) {

                throw new InvalidUserParametersException("The rating have to be bettwen 0-5");
            }

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void validateUserExistances(String userEmail)
            throws InvalidUserParametersException {

        UserDAO userDAO = new UserDAO();
        if (!userDAO.entityExists(new String[]{userEmail})) {
            throw new InvalidUserParametersException("The user does'nt exist");
        }
    }

    private void validateEnterpriseExistances(String enterpriseName)
            throws InvalidUserParametersException {

        EnterpriseDAO enterpriseDAO = new EnterpriseDAO();

        if (!enterpriseDAO.entityExists(new String[]{enterpriseName})) {
            throw new InvalidUserParametersException("The enterprise does'nt exist");
        }
    }

    private void validateVideogameExistances(String enterpriseName,
            String videogameTitle) throws InvalidUserParametersException {

        VideogameDAO videogameDAO = new VideogameDAO();

        if (!videogameDAO.entityExists(new String[]{videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The videogame does'nt exist");
        }
    }

    private void validateUserHaveVideogame(String userEmail, String enterpriseName,
            String videogameTitle) throws InvalidUserParametersException {

        UserVideogameDAO userVideogameDAO = new UserVideogameDAO();

        if (!userVideogameDAO.entityExists(new String[]{userEmail, videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The user does'nt have the videogame");
        }
    }

}
