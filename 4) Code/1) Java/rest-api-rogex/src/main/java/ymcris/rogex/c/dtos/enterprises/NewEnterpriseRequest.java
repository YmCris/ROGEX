package ymcris.rogex.c.dtos.enterprises;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewEnterpriseRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class NewEnterpriseRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private String description;
    private Double specificCommission;
    private Boolean hiddenAllComments;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private String logo;
    private String cover;

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public Double getSpecificCommission() {
        return specificCommission;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setSpecificCommission(Double specificCommission) {
        this.specificCommission = specificCommission;
    }

}
