package ymcris.rogex.b.services.banner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ymcris.rogex.c.dtos.banner.NewMainBannerRequest;
import ymcris.rogex.c.dtos.banner.UpdateMainBannerRequest;
import ymcris.rogex.d.daos.banner.MainBannerDAO;
import ymcris.rogex.e.models.banner.MainBanner;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.DAOException;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The MainBannerService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
public class MainBannerService extends GenericImageService<MainBanner> {

    // LOGGER ------------------------------------------------------------------
    private static final Logger logger
            = LoggerFactory.getLogger(MainBanner.class);

    // INSTANCES ---------------------------------------------------------------
    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MainBannerService() {
        super(new MainBannerDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected MainBanner createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        try {

            NewMainBannerRequest request = (NewMainBannerRequest) newObjectRequest;
            request.setPrimaryKeysSQLs(new String[]{request.getLink()});

            MainBanner banner = new MainBanner(
                    null,
                    false,
                    request.getLink()
            );

            if (!banner.isValid()) {
                throw new InvalidUserParametersException("Data sent is invalid");
            }

            return banner;

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }
    }

    @Override
    protected void updateObject(MainBanner banner,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        try {

            UpdateMainBannerRequest update = (UpdateMainBannerRequest) updateObjectRequest;

            if (update.getLink() != null) {
                banner.setLink(update.getLink());
            }

            if (!banner.isValid()) {
                throw new InvalidUserParametersException("Invalid data to update banner");
            }

        } catch (DAOException e) {
            logger.error("Error executing {}", e.getOperation(), e);
            throw e;
        }

    }

}
