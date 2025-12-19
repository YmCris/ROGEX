package ymcris.rogex.c.dtos.sale;

import java.time.LocalDate;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewSaleRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class NewSaleRequest extends GenericNewObjectRequest{

    // REFERENCE VARIABLES -----------------------------------------------------
    private double videogamePrice;
    private LocalDate saleDate;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // GETTERS -----------------------------------------------------------------
    public double getVideogamePrice() {
        return videogamePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogamePrice(double videogamePrice) {
        this.videogamePrice = videogamePrice;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
