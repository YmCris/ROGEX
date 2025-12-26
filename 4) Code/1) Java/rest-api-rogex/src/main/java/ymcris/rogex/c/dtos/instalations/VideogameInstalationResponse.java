package ymcris.rogex.c.dtos.instalations;

import java.time.LocalDateTime;
import ymcris.rogex.e.models.instalation.VideogameInstalation;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The VideogameInstalationResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public class VideogameInstalationResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime videogameInstallationDate;
    private LocalDateTime videogameDesinstallationDate;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameInstalationResponse(VideogameInstalation videogameInstallation) {
        this.videogameInstallationDate = videogameInstallation.getVideogameInstallationDate();
        this.videogameDesinstallationDate = videogameInstallation.getVideogameDesinstallationDate();
        this.userEmail = videogameInstallation.getUserEmail();
        this.videogameTitle = videogameInstallation.getVideogameTitle();
        this.enterpriseName = videogameInstallation.getVideogameTitle();
    }

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
