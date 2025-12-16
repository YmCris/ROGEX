package ymcris.rogex.g.commons.response;

/**
 * The MessageResponse class is the class responsible for almacen the message
 * response
 *
 * @author YmCris
 * @since Dec 13, 2025
 */
public class MessageResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String message;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MessageResponse(String message) {
        this.message = message;
    }

    // GETTERS -----------------------------------------------------------------
    public String getMessage() {
        return message;
    }
}
