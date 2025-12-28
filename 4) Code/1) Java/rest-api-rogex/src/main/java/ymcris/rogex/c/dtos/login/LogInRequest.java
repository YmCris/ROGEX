package ymcris.rogex.c.dtos.login;

/**
 * The LogInRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 27, 2025
 */
public class LogInRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String email;
    private String password;

    // GETTERS -----------------------------------------------------------------
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // SETTERS -----------------------------------------------------------------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
