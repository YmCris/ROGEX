package ymcris.rogex.c.dtos.videogame;

import ymcris.rogex.e.models.videogame.AgeRating;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateVideogameRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class UpdateVideogameRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String description;
    private Double price;
    private String minimumRequirements;
    private AgeRating ageRating;
    private Boolean suspensionOfSale;
    private Boolean hiddenComments;
    private Boolean hidden;

    private String newCategory;
    private String existingCategory;

    // GETTERS -----------------------------------------------------------------
    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getMinimumRequirements() {
        return minimumRequirements;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public Boolean getSuspensionOfSale() {
        return suspensionOfSale;
    }

    public Boolean getHiddenComments() {
        return hiddenComments;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public String getNewCategory() {
        return newCategory;
    }

    public String getExistingCategory() {
        return existingCategory;
    }

    // SETTERS -----------------------------------------------------------------
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMinimumRequirements(String minimumRequirements) {
        this.minimumRequirements = minimumRequirements;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSuspensionOfSale(Boolean suspensionOfSale) {
        this.suspensionOfSale = suspensionOfSale;
    }

    public void setHiddenComments(Boolean hiddenComments) {
        this.hiddenComments = hiddenComments;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public void setExistingCategory(String existingCategory) {
        this.existingCategory = existingCategory;
    }

}
