package ymcris.rogex.c.dtos.system;

import ymcris.rogex.e.models.system.SystemConfig;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The SystemResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class SystemResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String description;
    private Double globalCommissionPercentage;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SystemResponse(SystemConfig systemConfig) {
        this.description = systemConfig.getDescription();
        this.globalCommissionPercentage = systemConfig.getGlobalCommissionPercentage();
    }

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
