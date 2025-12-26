package ymcris.rogex.e.models.instalation;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The VideogameInstalation class is the class responsible for
 *
 * @author YmCris
 * @since Dec 24, 2025
 */
public class VideogameInstalation {

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime videogameInstallationDate;
    private LocalDateTime videogameDesinstallationDate;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameInstalation(LocalDateTime videogameInstallationDate,
            LocalDateTime videogameDesinstallationDate, String userEmail,
            String videogameTitle, String enterpriseName) {

        this.videogameInstallationDate = videogameInstallationDate;
        this.videogameDesinstallationDate = videogameDesinstallationDate;
        this.userEmail = userEmail;
        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        Validator validator = new Validator();
        return validator.isValidLocalDateTime(videogameInstallationDate)
                && !StringUtils.isAnyBlank(
                        userEmail,
                        videogameTitle,
                        enterpriseName
                );
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
