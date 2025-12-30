package ymcris.rogex.b.services.videogame.multimedia;

import ymcris.rogex.c.dtos.videogame.multimedia.NewVideogameMultimediaRequest;
import ymcris.rogex.d.daos.videogame.multimedia.VideogameMultimediaDAO;
import ymcris.rogex.e.models.videogame.multimedia.VideogameMultimedia;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericImageService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The VideogameMultimediaService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
public class VideogameMultimediaService extends GenericImageService<VideogameMultimedia> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameMultimediaService() {
        super(new VideogameMultimediaDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected VideogameMultimedia createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewVideogameMultimediaRequest request = (NewVideogameMultimediaRequest) newObjectRequest;

        VideogameMultimedia multimedia = new VideogameMultimedia(
                null,
                false,
                request.getVideogameTitle(),
                request.getEnterpriseName()
        );

        if (!multimedia.isValid()) {
            throw new InvalidUserParametersException("The data sent is invalid");
        }

        return multimedia;
    }

    @Override
    protected void updateObject(VideogameMultimedia entity,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

    }

}
