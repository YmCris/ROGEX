package ymcris.rogex.h.utilities.exceptions;

/**
 * Exception ObjectDoesNotExistsException is the exception responsible for show
 * that some object does'nt exists
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class ObjectDoesNotExistsException extends Exception {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     * Constructs an instance of ObjectDoesNotExistsException with the specified
     * detail message.
     *
     * @param message is the cause of the exception.
     */
    public ObjectDoesNotExistsException(String message) {
        super(message);
    }
}
