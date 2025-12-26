package ymcris.rogex.c.dtos.invitations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDateTime;
import ymcris.rogex.e.models.invitations.Invitation;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The InvitationResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class InvitationResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String invitationText;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime sentDate;
    private String groupName;
    private String senderEmail;
    private String receiverEmail;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public InvitationResponse(Invitation invitation) {
        this.invitationText = invitation.getInvitationText();
        this.sentDate = invitation.getSentDate();
        this.groupName = invitation.getGroupName();
        this.senderEmail = invitation.getSenderEmail();
        this.receiverEmail = invitation.getReceiverEmail();
    }

    // GETTERS -----------------------------------------------------------------
    public String getInvitationText() {
        return invitationText;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
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

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
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
