package ymcris.rogex.c.dtos.users;

import java.time.LocalDate;
import ymcris.rogex.e.models.users.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * The UserResponse class is the class responsible for send the user created in
 * the response
 *
 * @see User
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UserResponse extends GenericObjectResponse<User> {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String photoUrl;
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

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public UserResponse(User user) {
        super(user);
        this.photoUrl = "/api/v1/users/" + user.getEmail() + "/photo";
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.country = user.getCountry();
        this.publicLibrary = user.isPublicLibrary();
    }

    // GETTERS -----------------------------------------------------------------
    public String getPhotoUrl() {
        return photoUrl;
    }

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
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

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
