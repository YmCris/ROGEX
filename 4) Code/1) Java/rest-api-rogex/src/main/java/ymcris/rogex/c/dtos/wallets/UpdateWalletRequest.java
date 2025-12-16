package ymcris.rogex.c.dtos.wallets;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateWalletRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 14, 2025
 */
public class UpdateWalletRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private Double fund;

    // GETTERS -----------------------------------------------------------------
    public Double getFund() {
        return fund;
    }

    // SETTERS -----------------------------------------------------------------
    public void setFund(Double fund) {
        this.fund = fund;
    }

}
