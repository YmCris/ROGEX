package ymcris.rogex.c.dtos.category;

import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The CategoryResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class CategoryResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private Integer id;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public CategoryResponse(Category category) {
        this.name = category.getName();
        this.id = category.getId();
    }

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
    // SETTERS -----------------------------------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
