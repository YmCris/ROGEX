package ymcris.rogex.e.models.users;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.e.models.wallets.Wallet;
import ymcris.rogex.h.utilities.interfaces.Visualizable;

/**
 * The User class is the class responsible for represent a final user
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class User implements Visualizable {

    // REFERENCE VARIABLES -----------------------------------------------------
    private byte[] photo;
    private String nickname;
    private String password;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String country;
    private List<Wallet> wallets;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean publicLibrary;
    private boolean imagen;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public User(byte[] photo, String nickname, String password, LocalDate birthDate,
            String email, String phoneNumber, String country, boolean publicLibrary) {
        wallets = new ArrayList<>();
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
        return !StringUtils.isAnyBlank(
                nickname,
                password,
                email,
                phoneNumber,
                country
        ) && birthDate != null;
    }

    //OVERRIDE METHODS ---------------------------------------------------------
    @Override
    public boolean isImage() {
        return imagen;
    }

    @Override
    public void setImage(boolean isImage) {
        this.imagen = isImage;
    }

    // GETTERS -----------------------------------------------------------------
    @Override
    public byte[] getPhoto() {
        return photo;
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

    public List<Wallet> getWallets() {
        return wallets;
    }

    // SETTERS -----------------------------------------------------------------
    @Override
    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }

}
