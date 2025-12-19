package ymcris.rogex.e.models.sale;

import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;

/**
 * The Sale class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class Sale {

    // REFERENCE VARIABLES -----------------------------------------------------
    private double videogamePrice;
    private LocalDate saleDate;
    private double commissionPercentage;
    private double profit;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Sale(double videogamePrice, LocalDate saleDate,
            double commissionPercentage, double profit, String userEmail,
            String videogameTitle, String enterpriseName) {
        this.videogamePrice = videogamePrice;
        this.saleDate = saleDate;
        this.commissionPercentage = commissionPercentage;
        this.profit = profit;
        this.userEmail = userEmail;
        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(
                userEmail,
                videogameTitle,
                enterpriseName)
                && videogamePrice > 0
                && saleDate != null
                && commissionPercentage > 0
                && profit > 0;
    }

    // GETTERS -----------------------------------------------------------------
    public double getVideogamePrice() {
        return videogamePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public double getProfit() {
        return profit;
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

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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
