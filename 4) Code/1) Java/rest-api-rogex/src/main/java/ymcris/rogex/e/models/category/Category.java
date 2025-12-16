package ymcris.rogex.e.models.category;

import org.apache.commons.lang3.StringUtils;

/**
 * The Category class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class Category {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private Integer id;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public Category(String name) {
        this.name = name;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isNotBlank(name);
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
