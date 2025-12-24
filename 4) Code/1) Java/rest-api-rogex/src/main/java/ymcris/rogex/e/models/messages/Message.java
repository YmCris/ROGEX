package ymcris.rogex.e.models.messages;

import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.validations.Validator;

/**
 * The Message class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class Message {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String messageText;
    private LocalDateTime sentDate;
    private byte[] multimedia;
    private String senderEmail;
    private String receiverEmail;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Message(String messageText, LocalDateTime sentDate, byte[] multimedia,
            String senderEmail, String receiverEmail) {
        this.messageText = messageText;
        this.sentDate = sentDate;
        this.multimedia = multimedia;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        Validator validator = new Validator();
        return !StringUtils.isAnyBlank(
                messageText,
                senderEmail,
                receiverEmail)
                && sentDate != null
                && validator.isValidLocalDateTime(sentDate)
                && validator.isEmail(senderEmail)
                && validator.isEmail(receiverEmail);
    }

    // GETTERS -----------------------------------------------------------------
    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public byte[] getMultimedia() {
        return multimedia;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    // SETTERS -----------------------------------------------------------------
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public void setMultimedia(byte[] multimedia) {
        this.multimedia = multimedia;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

}
