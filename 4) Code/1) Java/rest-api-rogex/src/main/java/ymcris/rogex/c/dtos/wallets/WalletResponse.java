package ymcris.rogex.c.dtos.wallets;

import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The WalletResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 14, 2025
 */
public class WalletResponse extends GenericObjectResponse<Wallet> {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String userEmail;
    private String name;
    private Double fund;
    private BanckType banck;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public WalletResponse(Wallet wallet) {
        super(wallet);
        this.userEmail = wallet.getUser().getEmail();
        this.name = wallet.getName();
        this.fund = wallet.getFund();
        this.banck = wallet.getBanck();
    }

    // GETTERS -----------------------------------------------------------------
    public String getUser() {
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
    public void setUser(String userEmail) {
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
