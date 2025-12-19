package ymcris.rogex.c.dtos.enterprises.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import ymcris.rogex.e.models.enterprise.users.EnterpriseUser;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    private String enterpriseName;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public EnterpriseUserResponse(EnterpriseUser enterpriseUser) {
        this.email = enterpriseUser.getEmail();
        this.name = enterpriseUser.getName();
        this.birthDate = enterpriseUser.getBirthDate();
        this.enterpriseName = enterpriseUser.getEnterpriseName();
    }

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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEnterprise(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
