package ymcris.rogex.c.dtos.users;

import java.io.File;
import java.time.LocalDateTime;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;

/**
 * The UpdateUserRequest class is the class responsible for do the update
 * request from an user
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class UpdateUserRequest extends GenericUpdateObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private File photo;
    private LocalDateTime birthDate;
    private String phoneNumber;
    private String country;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean publicLibrary;

    // GETTERS -----------------------------------------------------------------
    public File getPhoto() {
        return photo;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
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

    public void setBirthDate(LocalDateTime birthDate) {
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
