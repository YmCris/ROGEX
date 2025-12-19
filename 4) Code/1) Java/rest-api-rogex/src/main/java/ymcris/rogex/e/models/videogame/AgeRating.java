package ymcris.rogex.e.models.videogame;

/**
 * The enum AgeRating is the enum responsible for representing
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public enum AgeRating {

    // ENUMS -------------------------------------------------------------------
    E(0, "Para todos"),
    T(14, "Adolescentes"),
    M(18, "Adultos");

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private Integer ageLimit;
    private String type;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    private AgeRating(Integer ageLimit, String type) {
        this.ageLimit = ageLimit;
        this.type = type;
    }

    // GETTERS -----------------------------------------------------------------
    public Integer getAgeLimit() {
        return ageLimit;
    }

    public String getType() {
        return type;
    }

}
