package ymcris.rogex.c.dtos.groups;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateGroupRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class UpdateGroupRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private int quantity;

    // GETTERS -----------------------------------------------------------------
    public int getQuantity() {
        return quantity;
    }

    // SETTERS -----------------------------------------------------------------
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
