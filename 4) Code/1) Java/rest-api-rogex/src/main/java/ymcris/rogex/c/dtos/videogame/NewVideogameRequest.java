package ymcris.rogex.c.dtos.videogame;

import java.util.ArrayList;
import java.util.List;
import ymcris.rogex.e.models.videogame.AgeRating;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewVideogameRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class NewVideogameRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String title;//<-PK
    private String description;
    private double price;
    private String minimumRequirements;
    private AgeRating ageRating;
    private String enterpriseName;//<-PK
    private boolean suspensionOfSale;
    private boolean hiddenComments;
    private boolean hidden;

    private List<String> categories = new ArrayList<>();

    // GETTERS -----------------------------------------------------------------
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getMinimumRequirements() {
        return minimumRequirements;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public boolean isSuspensionOfSale() {
        return suspensionOfSale;
    }

    public boolean isHiddenComments() {
        return hiddenComments;
    }

    public boolean isHidden() {
        return hidden;
    }

    public List<String> getCategories() {
        return categories;
    }

    // SETTERS -----------------------------------------------------------------
    public void setTitle(String title) {
        this.title = title;
    }

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

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setSuspensionOfSale(boolean suspensionOfSale) {
        this.suspensionOfSale = suspensionOfSale;
    }

    public void setHiddenComments(boolean hiddenComments) {
        this.hiddenComments = hiddenComments;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
