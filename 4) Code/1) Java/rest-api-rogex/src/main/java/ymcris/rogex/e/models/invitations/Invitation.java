package ymcris.rogex.e.models.invitations;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The Invitation class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class Invitation {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String invitationText;
    private LocalDateTime sentDate;
    private String groupName;
    private String senderEmail;
    private String receiverEmail;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Invitation(String invitationText, LocalDateTime sentDate,
            String groupName, String senderEmail,
            String receiverEmail) {

        this.invitationText = invitationText;
        this.sentDate = sentDate;
        this.groupName = groupName;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        Validator validator = new Validator();
        return !StringUtils.isAnyBlank(invitationText,
                groupName,
                senderEmail,
                receiverEmail)
                && sentDate != null
                && validator.isValidLocalDateTime(sentDate)
                && validator.isEmail(senderEmail)
                && validator.isEmail(receiverEmail);
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
