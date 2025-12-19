package ymcris.rogex.c.dtos.groups;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewGroupRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class NewGroupRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private String creatorEmail;

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

}
