package ymcris.rogex.c.dtos.instalations;

import java.time.LocalDateTime;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateVideogameInstalationRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class UpdateVideogameInstalationRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime videogameDesinstallationDate;

    // GETTERS -----------------------------------------------------------------
    public LocalDateTime getVideogameDesinstallationDate() {
        return videogameDesinstallationDate;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogameDesinstallationDate(LocalDateTime videogameDesinstallationDate) {
        this.videogameDesinstallationDate = videogameDesinstallationDate;
    }

}
