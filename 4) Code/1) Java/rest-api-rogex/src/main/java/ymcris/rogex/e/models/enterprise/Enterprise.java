package ymcris.rogex.e.models.enterprise;

import org.apache.commons.lang3.StringUtils;

/**
 * The Enterprise class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class Enterprise {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name; //<- PK
    private String description;
    private Double specificCommission;
    private boolean hiddenAllComments;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private byte[] logo;
    private byte[] cover;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Enterprise(String name, String description, Double specificCommission, boolean hiddenAllComments, byte[] logo, byte[] cover) {
        this.name = name;
        this.description = description;
        this.specificCommission = specificCommission;
        this.hiddenAllComments = hiddenAllComments;
        this.logo = logo;
        this.cover = cover;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(name, description)
                && specificCommission > 0;
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

    public boolean getHiddenAllComments() {
        return hiddenAllComments;
    }

    public byte[] getLogo() {
        return logo;
    }

    public byte[] getCover() {
        return cover;
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

    public void setHiddenAllComments(boolean hiddenAllComments) {
        this.hiddenAllComments = hiddenAllComments;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
}
