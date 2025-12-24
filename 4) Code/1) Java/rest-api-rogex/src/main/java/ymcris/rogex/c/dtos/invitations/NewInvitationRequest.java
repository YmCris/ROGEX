package ymcris.rogex.c.dtos.messages;

import java.time.LocalDateTime;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewMesssageRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 23, 2025
 */
public class NewMesssageRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String messageText;
    private LocalDateTime sentDate;
    private byte[] multimedia;
    private String senderEmail;
    private String receiverEmail;

    // GETTERS -----------------------------------------------------------------
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

    // SETTERS -----------------------------------------------------------------
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

}
