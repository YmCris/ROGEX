package ymcris.rogex.h.utilities.exceptions;

/**
 * The DAOException class is the class responsible for
 *
 * @author YmCris
 * @since Dec 22, 2025
 */
public class DAOException extends RuntimeException {

    // REFERENCE VARIABLES -----------------------------------------------------
    private final String operation;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public DAOException(String operation, Throwable cause) {
        super(operation, cause);
        this.operation = operation;
    }

    // GETTERS -----------------------------------------------------------------
    public String getOperation() {
        return operation;
    }

}
