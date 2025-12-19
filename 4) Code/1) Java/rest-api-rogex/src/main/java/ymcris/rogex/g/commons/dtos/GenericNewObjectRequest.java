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
    private String[] primaryKeysSQLs;
    private String[] primaryKeysAdditionals;

    // CONSTRUCTOR -------------------------------------------------------------
    public GenericNewObjectRequest(String[] primaryKeys) {
        this.primaryKeysSQLs = primaryKeys;
    }

    public GenericNewObjectRequest() {

    }

    // GETTERS -----------------------------------------------------------------
    public String[] getPrimaryKeysSQLs() {
        return primaryKeysSQLs;
    }

    public String[] getPrimaryKeysAdditionals() {
        return primaryKeysAdditionals;
    }

    // SETTERS -----------------------------------------------------------------
    /**
     * Setter all primary keys
     *
     * @param primaryKeysSQLs array of primary keys
     */
    public void setPrimaryKeysSQLs(String[] primaryKeysSQLs) {
        this.primaryKeysSQLs = primaryKeysSQLs;
    }

    public void setPrimaryKeysAdditionals(String[] primaryKeysAdditionals) {
        this.primaryKeysAdditionals = primaryKeysAdditionals;
    }

}
