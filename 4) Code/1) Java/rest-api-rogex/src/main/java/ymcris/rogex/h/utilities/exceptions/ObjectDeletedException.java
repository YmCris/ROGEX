package ymcris.rogex.h.utilities.exceptions;

/**
 * Exception ObjectDeletedException is the exception responsible for show that
 * some object was'nt deleted
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class ObjectDeletedException extends Exception {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     * Constructs an instance of ObjectDeletedException with the specified
     * detail message.
     *
     * @param message is the cause of the exception.
     */
    public ObjectDeletedException(String message) {
        super(message);
    }
}
