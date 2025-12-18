package ymcris.rogex.c.dtos.enterprises.users;

import java.time.LocalDate;
import ymcris.rogex.e.models.enterprise.Enterprise;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The EnterpriseUserResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseUserResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String email;
    private String name;
    private LocalDate birthDate;
    private Enterprise enterprise;

    // GETTERS -----------------------------------------------------------------
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
