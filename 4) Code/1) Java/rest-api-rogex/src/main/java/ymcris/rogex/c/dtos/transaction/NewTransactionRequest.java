package ymcris.rogex.c.dtos.transaction;

import java.time.LocalDateTime;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewTransactionRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 22, 2025
 */
public class NewTransactionRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime transactionDate;
    private String walletName;
    private String description;
    private BanckType walletBanck;
    private String userEmail;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private double amount;

    // GETTERS -----------------------------------------------------------------
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getWalletName() {
        return walletName;
    }

    public String getDescription() {
        return description;
    }

    public BanckType getWalletBanck() {
        return walletBanck;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public double getAmount() {
        return amount;
    }

    // SETTERS -----------------------------------------------------------------
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWalletBanck(BanckType walletBanck) {
        this.walletBanck = walletBanck;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
