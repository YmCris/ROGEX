package ymcris.rogex.e.models.users;

import java.io.File;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;

/**
 * The User class is the class responsible for represent a final user
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class User {

    // REFERENCE VARIABLES -----------------------------------------------------
    private File photo;
    private String nickname;
    private String password;
    private LocalDateTime birthDate;
    private String email;
    private String phoneNumber;
    private String country;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean publicLibrary;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public User(File photo, String nickname, String password, LocalDateTime birthDate,
            String email, String phoneNumber, String country, boolean publicLibrary) {
        this.photo = photo;
        this.nickname = nickname;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.publicLibrary = publicLibrary;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isAnyBlank(
                nickname,
                password,
                email,
                phoneNumber,
                country
        ) || birthDate == null;
    }

    // GETTERS -----------------------------------------------------------------
    public File getPhoto() {
        return photo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getBirthDate() {
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
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDateTime birthDate) {
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
