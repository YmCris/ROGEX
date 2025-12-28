package ymcris.rogex.c.dtos.users;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * The UpdateUserRequest class is the class responsible for do the update
 * request from an user
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UpdateUserRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;
    private String phoneNumber;
    private String country;
    private Boolean publicLibrary;

    // GETTERS -----------------------------------------------------------------
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public Boolean isPublicLibrary() {
        return publicLibrary;
    }

    // SETTERS -----------------------------------------------------------------
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPublicLibrary(boolean publicLibrary) {
        this.publicLibrary = publicLibrary;
    }

}
