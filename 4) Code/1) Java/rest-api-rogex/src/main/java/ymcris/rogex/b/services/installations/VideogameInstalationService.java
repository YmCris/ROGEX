package ymcris.rogex.b.services.installations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.instalations.NewVideogameInstalationRequest;
import ymcris.rogex.c.dtos.instalations.UpdateVideogameInstalationRequest;
import ymcris.rogex.d.daos.instalations.VideogameInstalationDAO;
import ymcris.rogex.d.daos.users.UserDAO;
import ymcris.rogex.d.daos.users.videogames.UserVideogameDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.instalation.VideogameInstalation;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The VideogameInstalationService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameInstalationService extends GenericService<VideogameInstalation> {

    // LOGGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(VideogameInstalationService.class);

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameInstalationService() {
        super(new VideogameInstalationDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected VideogameInstalation createObject(
            GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        try {
            NewVideogameInstalationRequest request = (NewVideogameInstalationRequest) newObjectRequest;
            request.setPrimaryKeysSQLs(new String[]{request.getUserEmail(), request.getVideogameTitle(),
                request.getEnterpriseName(), request.getVideogameInstallationDate().toString()});

            VideogameInstalation instalation = new VideogameInstalation(
                    request.getVideogameInstallationDate(),
                    request.getVideogameDesinstallationDate(),
                    request.getUserEmail(),
                    request.getVideogameTitle(),
                    request.getEnterpriseName()
            );

            if (!instalation.isValid()) {
                throw new InvalidUserParametersException("Data sent to create the instalation is invalid");
            }

            //1. Check if the user have the videogame
            userHasVideogame(
                    instalation.getUserEmail(),
                    instalation.getVideogameTitle(),
                    instalation.getEnterpriseName()
            );

            return instalation;

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }

    }

    @Override
    protected void updateObject(VideogameInstalation videogameInstalation,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        try {
            UpdateVideogameInstalationRequest update = (UpdateVideogameInstalationRequest) updateObjectRequest;

            videogameInstalation.setVideogameDesinstallationDate(update.getVideogameDesinstallationDate());

            if (!videogameInstalation.isValid()) {
                throw new InvalidUserParametersException("Date of desinstalation is invalid");
            }
        } catch (DAOException e) {
            logger.error("Error executing {} ", e.getOperation(), e);
            throw e;
        }
    }

    // AUXILIAR METHODS --------------------------------------------------------
    private void userHasVideogame(String userEmail, String videogameTitle,
            String enterpriseName) throws InvalidUserParametersException {

        UserDAO userDAO = new UserDAO();
        VideogameDAO videogameDAO = new VideogameDAO();
        UserVideogameDAO userVideogameDAO = new UserVideogameDAO();

        if (!userDAO.entityExists(new String[]{userEmail})) {
            throw new InvalidUserParametersException("The user does'nt exist");
        }
        if (!videogameDAO.entityExists(new String[]{videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The videogame does'nt exist");
        }
        if (!userVideogameDAO.entityExists(new String[]{userEmail, videogameTitle, enterpriseName})) {
            throw new InvalidUserParametersException("The user does'nt have the videogame in his library");
        }

    }
}
