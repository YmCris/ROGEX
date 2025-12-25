package ymcris.rogex.c.dtos.instalations;

import java.time.LocalDateTime;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewVideogameInstalationRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class NewVideogameInstalationRequest extends GenericNewObjectRequest{

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime videogameInstallationDate;
    private LocalDateTime videogameDesinstallationDate;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // GETTERS -----------------------------------------------------------------
    public LocalDateTime getVideogameInstallationDate() {
        return videogameInstallationDate;
    }

    public LocalDateTime getVideogameDesinstallationDate() {
        return videogameDesinstallationDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogameInstallationDate(LocalDateTime videogameInstallationDate) {
        this.videogameInstallationDate = videogameInstallationDate;
    }

    public void setVideogameDesinstallationDate(LocalDateTime videogameDesinstallationDate) {
        this.videogameDesinstallationDate = videogameDesinstallationDate;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
