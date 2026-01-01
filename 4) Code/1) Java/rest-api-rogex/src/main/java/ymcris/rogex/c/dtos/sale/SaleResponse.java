package ymcris.rogex.c.dtos.sale;

import java.time.LocalDate;
import ymcris.rogex.e.models.sale.Sale;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The SaleResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class SaleResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private double videogamePrice;
    private LocalDate saleDate;
    private double commissionPercentage;
    private double profit;
    private String userEmail;
    private String videogameTitle;
    private String enterpriseName;
    private String walletName;
    private BanckType walletBanck;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public SaleResponse(Sale sale) {
        this.videogamePrice = sale.getVideogamePrice();
        this.saleDate = sale.getSaleDate();
        this.commissionPercentage = sale.getCommissionPercentage();
        this.profit = sale.getProfit();
        this.userEmail = sale.getUserEmail();
        this.videogameTitle = sale.getVideogameTitle();
        this.enterpriseName = sale.getEnterpriseName();
        this.walletBanck = sale.getWalletBanck();
        this.walletName = sale.getWalletName();
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

    public String getWalletName() {
        return walletName;
    }

    public BanckType getWalletBanck() {
        return walletBanck;
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

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setWalletBanck(BanckType walletBanck) {
        this.walletBanck = walletBanck;
    }

}
