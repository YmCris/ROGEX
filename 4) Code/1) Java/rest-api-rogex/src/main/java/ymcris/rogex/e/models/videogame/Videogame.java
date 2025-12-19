package ymcris.rogex.e.models.videogame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.category.Category;

/**
 * The Videogame class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class Videogame {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String title;//<-PK
    private String description;
    private double price;
    private String minimumRequirements;
    private AgeRating ageRating;
    private LocalDate releaseDate;
    private Integer downloads;
    private String enterpriseName;//<-PK
    private boolean suspensionOfSale;
    private boolean hiddenComments;
    private boolean hidden;
    private List<Category> categories;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Videogame(String title, String description, double price,
            String minimumRequirements, AgeRating ageRating,
            LocalDate releaseDate, Integer downloads, String enterpriseName,
            boolean suspensionOfSale, boolean hiddenComments, boolean hidden) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.minimumRequirements = minimumRequirements;
        this.ageRating = ageRating;
        this.releaseDate = releaseDate;
        this.downloads = downloads;
        this.enterpriseName = enterpriseName;
        this.suspensionOfSale = suspensionOfSale;
        this.hiddenComments = hiddenComments;
        this.hidden = hidden;
        this.categories = new ArrayList<>();
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(
                title,
                description,
                minimumRequirements,
                enterpriseName)
                && price > 0
                && ageRating != null
                && downloads >= 0
                && !releaseDate.isAfter(LocalDate.now());
    }

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getDownloads() {
        return downloads;
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

    public List<Category> getCategories() {
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

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
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

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
