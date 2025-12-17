package ymcris.rogex.c.dtos.enterprises;

import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The EnterpriseResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private String description;
    private Double specificCommission;
    private Boolean hiddenAllComments;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private String logoUrl;
    private String coverUrl;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseResponse(Enterprise enterprise) {
        this.name = enterprise.getName();
        this.description = enterprise.getDescription();
        this.specificCommission = enterprise.getSpecificCommission();
        this.hiddenAllComments = enterprise.getHiddenAllComments();
        this.logoUrl = "/api/v1/enterprises/" + name + "/logo";
        this.coverUrl = "/api/v1/enterprises/" + name + "/cover";
    }

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getSpecificCommission() {
        return specificCommission;
    }

    public Boolean getHiddenAllComments() {
        return hiddenAllComments;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecificCommission(Double specificCommission) {
        this.specificCommission = specificCommission;
    }

    public void setHiddenAllComments(Boolean hiddenAllComments) {
        this.hiddenAllComments = hiddenAllComments;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

}
