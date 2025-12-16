package ymcris.rogex.c.dtos.system;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateSystemRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class UpdateSystemRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String description;
    private Double globalCommissionPercentage;

    // GETTERS -----------------------------------------------------------------
    public String getDescription() {
        return description;
    }

    public Double getGlobalCommissionPercentage() {
        return globalCommissionPercentage;
    }

    // SETTERS -----------------------------------------------------------------
    public void setDescription(String description) {
        this.description = description;
    }

    public void setGlobalCommissionPercentage(Double globalCommissionPercentage) {
        this.globalCommissionPercentage = globalCommissionPercentage;
    }

}
