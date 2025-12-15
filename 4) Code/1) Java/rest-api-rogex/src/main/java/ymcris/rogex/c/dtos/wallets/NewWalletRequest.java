package ymcris.rogex.c.dtos.wallets;

import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewWalletRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 14, 2025
 */
public class NewWalletRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String userEmail;
    private String name;
    private Double fund;
    private BanckType banck;

    // GETTERS -----------------------------------------------------------------
    public String getUserEmail() {
        return userEmail;
    }

    public String getName() {
        return name;
    }

    public Double getFund() {
        return fund;
    }

    public BanckType getBanck() {
        return banck;
    }

    // SETTERS -----------------------------------------------------------------
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public void setBanck(BanckType banck) {
        this.banck = banck;
    }

}
