package ymcris.rogex.c.dtos.login;

/**
 * The LogInResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 27, 2025
 */
public class LogInResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String email;
    private String name;
    private Role role;

    // OPTIONALS ---------------------------------------------------------------
    private String enterpriseName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public LogInResponse(String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // GETTERS -----------------------------------------------------------------
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
