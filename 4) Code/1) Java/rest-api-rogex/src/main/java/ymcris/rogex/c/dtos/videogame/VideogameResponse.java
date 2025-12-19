package ymcris.rogex.c.dtos.videogame;

import java.time.LocalDate;
import java.util.List;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.e.models.videogame.AgeRating;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The VideogameResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class VideogameResponse implements GenericObjectResponse {

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
    public VideogameResponse(Videogame videogame) {
        this.title = videogame.getTitle();
        this.description = videogame.getDescription();
        this.price = videogame.getPrice();
        this.minimumRequirements = videogame.getMinimumRequirements();
        this.ageRating = videogame.getAgeRating();
        this.releaseDate = videogame.getReleaseDate();
        this.downloads = videogame.getDownloads();
        this.enterpriseName = videogame.getEnterpriseName();
        this.suspensionOfSale = videogame.isSuspensionOfSale();
        this.hiddenComments = videogame.isHiddenComments();
        this.hidden = videogame.isHidden();
        this.categories = videogame.getCategories();
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
