package ymcris.rogex.c.dtos.enterprises.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewEnterpriseUserRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 17, 2025
 */
public class NewEnterpriseUserRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String email;//<- PK
    private String name;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    private String enterpriseName;

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
        return enterpriseName;
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

    public void setEnterprise(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
