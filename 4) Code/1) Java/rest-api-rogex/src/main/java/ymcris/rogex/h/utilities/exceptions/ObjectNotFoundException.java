package ymcris.rogex.h.utilities.exceptions;

/**
 * Exception ObjectNotFoundException is the exception responsible for show that
 * some object does'nt found
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class ObjectNotFoundException extends Exception {

    /**
     * Constructs an instance of <code> ObjectNotFoundException </code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
