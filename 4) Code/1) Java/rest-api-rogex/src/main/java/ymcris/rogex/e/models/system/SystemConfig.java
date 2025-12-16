package ymcris.rogex.e.models.system;

import org.apache.commons.lang3.StringUtils;

/**
 * The SystemConfig class is the class responsible for
 *
 * @author YmCris
 * @since Dec 15, 2025
 */
public class SystemConfig {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String description;
    private Double globalCommissionPercentage;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SystemConfig(String description, Double globalCommissionPercentage) {
        this.description = description;
        this.globalCommissionPercentage = globalCommissionPercentage;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isNotBlank(description)
                && globalCommissionPercentage > 0;
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
