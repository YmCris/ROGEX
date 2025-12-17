package ymcris.rogex.e.models.enterprise.users;

import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.enterprise.Enterprise;

/**
 * The EnterpriseUser class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class EnterpriseUser {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String email;
    private String name;
    private String password;
    private LocalDate birthDate;
    private Enterprise enterprise;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseUser(String email, String name, String password, LocalDate birthDate, String enterpriseName) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.enterprise = new Enterprise(
                enterpriseName,
                null,
                null,
                false,
                null,
                null
        );
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isAnyBlank(
                email,
                name,
                password,
                enterprise.getName())
                && birthDate != null;
    }

    // GETTERS -----------------------------------------------------------------
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEnterpriseName() {
        return enterprise.getName();
    }

    // SETTERS -----------------------------------------------------------------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

}
