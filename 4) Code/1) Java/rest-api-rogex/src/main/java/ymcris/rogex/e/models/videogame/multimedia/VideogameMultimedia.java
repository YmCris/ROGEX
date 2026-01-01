package ymcris.rogex.e.models.videogame.multimedia;

import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.interfaces.Visualizable;

/**
 * The VideogameMultimedia class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
public class VideogameMultimedia implements Visualizable {

    // REFERENCE VARIABLES -----------------------------------------------------
    private byte[] photo;
    private boolean image;
    private String videogameTitle;
    private String enterpriseName;
    private int id;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameMultimedia(byte[] photo, boolean image, String videogameTitle,
            String enterpriseName) {

        this.photo = photo;
        this.image = image;
        this.videogameTitle = videogameTitle;
        this.enterpriseName = enterpriseName;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return !StringUtils.isAnyBlank(
                videogameTitle,
                enterpriseName);
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    public byte[] getPhoto() {
        return photo;
    }

    @Override
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean isImage() {
        return image;
    }

    @Override
    public void setImage(boolean isImage) {
        this.image = isImage;
    }

    public int getId() {
        return id;
    }

    // GETTERS -----------------------------------------------------------------
    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    // SETTERS -----------------------------------------------------------------
    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setId(int id) {
        this.id = id;
    }

}
