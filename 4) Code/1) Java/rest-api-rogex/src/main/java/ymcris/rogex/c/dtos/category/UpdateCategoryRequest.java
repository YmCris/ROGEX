package ymcris.rogex.c.dtos.category;

import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateCategoryRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class UpdateCategoryRequest extends GenericUpdateObjectRequest {

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
