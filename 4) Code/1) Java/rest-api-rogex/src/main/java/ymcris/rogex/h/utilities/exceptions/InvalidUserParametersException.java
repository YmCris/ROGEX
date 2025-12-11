package ymcris.rogex.h.utilities.exceptions;

/**
 * Exception InvalidUserParametersException is the exception responsible for
 * annunciament that the user is stupid :)
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class InvalidUserParametersException extends Exception {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    /**
     * Constructs an instance of InvalidUserParametersException with the
     * specified detail message.
     *
     * @param message is the cause of the exception.
     */
    public InvalidUserParametersException(String message) {
        super(message);
    }
}
