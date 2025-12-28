package ymcris.rogex.c.dtos.users;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * The NewUserRequest class is the class responsible for do a request to create
 * a new user
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class NewUserRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String nickname;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String country;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean publicLibrary;

    // GETTERS -----------------------------------------------------------------
    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public boolean isPublicLibrary() {
        return publicLibrary;
    }

    // SETTERS -----------------------------------------------------------------
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
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
