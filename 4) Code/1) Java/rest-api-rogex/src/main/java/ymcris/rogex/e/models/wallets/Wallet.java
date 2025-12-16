package ymcris.rogex.e.models.wallets;

import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.users.User;

/**
 * The Wallet class is the class responsible for be the wallet
 *
 * @author YmCris
 * @since Dec 14, 2025
 */
public class Wallet {

    // REFERENCE VARIABLES -----------------------------------------------------
    private User user;
    private String name;
    private Double fund;
    private BanckType banck;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Wallet(String name, Double fund, BanckType banck) {
        this.name = name;
        this.fund = fund;
        this.banck = banck;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isNotBlank(name)
                && user != null
                && banck != null
                && fund > 0;
    }

    // GETTERS -----------------------------------------------------------------
    public User getUser() {
        return user;
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
    public void setName(String name) {
        this.name = name;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public void setBanck(BanckType banck) {
        this.banck = banck;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
