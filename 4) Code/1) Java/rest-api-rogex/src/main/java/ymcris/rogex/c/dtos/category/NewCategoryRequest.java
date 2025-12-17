package ymcris.rogex.c.dtos.category;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewCategoryRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class NewCategoryRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

}
