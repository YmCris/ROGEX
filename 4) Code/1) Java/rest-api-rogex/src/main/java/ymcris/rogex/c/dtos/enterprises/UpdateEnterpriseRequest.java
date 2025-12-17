package ymcris.rogex.c.dtos.enterprises;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateEnterpriseRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class UpdateEnterpriseRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String description;
    private Double specificCommission;
    private Boolean hiddenAllComments;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private String logo;
    private String cover;

    // GETTERS -----------------------------------------------------------------
    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecificCommission(Double specificCommission) {
        this.specificCommission = specificCommission;
    }

    public void setHiddenAllComments(Boolean hiddenAllComments) {
        this.hiddenAllComments = hiddenAllComments;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    // SETTERS -----------------------------------------------------------------
    public String getDescription() {
        return description;
    }

    public Double getSpecificCommission() {
        return specificCommission;
    }

    public Boolean getHiddenAllComments() {
        return hiddenAllComments;
    }

    public String getLogo() {
        return logo;
    }

    public String getCover() {
        return cover;
    }

}
