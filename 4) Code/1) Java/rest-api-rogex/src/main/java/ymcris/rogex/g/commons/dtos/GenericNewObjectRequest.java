package ymcris.rogex.g.commons.dtos;

/**
 * The GenericNewObjectRequest class is the class responsible for be the
 * template of all "New_Request" in the programm
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String[] primaryKeys;

    // CONSTRUCTOR -------------------------------------------------------------
    public GenericNewObjectRequest(String[] primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public GenericNewObjectRequest() {

    }

    // GETTERS -----------------------------------------------------------------
    public String[] getPrimaryKeys() {
        return primaryKeys;
    }

    // SETTERS -----------------------------------------------------------------
    /**
     * Setter all primary keys
     *
     * @param primaryKeys array of primary keys
     */
    public void setPrimaryKeys(String[] primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

}
