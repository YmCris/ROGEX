package ymcris.rogex.e.models.transaction;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.wallets.BanckType;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The Transaction class is the class responsible for
 *
 * @author YmCris
 * @since Dec 20, 2025
 */
public class Transaction {

    // REFERENCE VARIABLES -----------------------------------------------------
    private LocalDateTime transactionDate;
    private String walletName;
    private String description;
    private BanckType walletBanck;
    private String userEmail;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private double amount;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Transaction(LocalDateTime transactionDate, String walletName,
            String description, BanckType walletBanck, String userEmail, double amount) {
        this.transactionDate = transactionDate;
        this.walletName = walletName;
        this.description = description;
        this.walletBanck = walletBanck;
        this.userEmail = userEmail;
        this.amount = amount;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        Validator validator = new Validator();
        return validator.isValidLocalDateTime(transactionDate)
                && !StringUtils.isAnyBlank(
                        walletName,
                        description,
                        userEmail)
                && walletBanck != null
                && amount > 0;
    }

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
