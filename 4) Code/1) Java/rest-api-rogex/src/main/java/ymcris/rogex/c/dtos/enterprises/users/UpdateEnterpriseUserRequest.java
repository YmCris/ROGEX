package ymcris.rogex.c.dtos.enterprises.users;

import java.time.LocalDate;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateEnterpriseUserRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class UpdateEnterpriseUserRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String name;
    private String password;
    private LocalDate birthDate;

    // GETTERS -----------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
