package ymcris.rogex.h.utilities.exceptions;

/**
 * Exception ObjectAlreadyExistsException is the exception responsible for say
 * that an entity already exists
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class ObjectAlreadyExistsException extends Exception {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     * Constructs an instance of ObjectAlreadyExistsException with the specified
     * detail message.
     *
     * @param message is the cause of the exception.
     */
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
