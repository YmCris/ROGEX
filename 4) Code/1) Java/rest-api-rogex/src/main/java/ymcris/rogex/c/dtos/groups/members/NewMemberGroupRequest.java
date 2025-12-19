package ymcris.rogex.c.dtos.groups.members;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewMemberGroupRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 19, 2025
 */
public class NewMemberGroupRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String userEmail;
    private String groupName;

    // GETTERS -----------------------------------------------------------------
    public String getUserEmail() {
        return userEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
