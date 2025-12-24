package ymcris.rogex.c.dtos.invitations;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewInvitationRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class NewInvitationRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String invitationText;
    private String groupName;
    private String senderEmail;
    private String receiverEmail;

    // GETTERS -----------------------------------------------------------------
    public String getInvitationText() {
        return invitationText;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    // SETTERS -----------------------------------------------------------------
    public void setInvitationText(String invitationText) {
        this.invitationText = invitationText;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

}
