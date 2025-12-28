package ymcris.rogex.e.models.banner;

import org.apache.commons.lang3.StringUtils;
import ymcris.rogex.h.utilities.interfaces.Visualizable;

/**
 * The MainBanner class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
public class MainBanner implements Visualizable {

    // REFERENCE VARIABLES -----------------------------------------------------
    private byte[] multimedia;
    private boolean image;
    private String link;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MainBanner(byte[] multimedia, boolean isImage, String link) {
        this.multimedia = multimedia;
        this.image = isImage;
        this.link = link;
    }

    // SPECIFIC METHODS --------------------------------------------------------
    public boolean isValid() {
        return StringUtils.isNotBlank(link)
                && multimedia != null
                && multimedia.length > 0;
    }

    // GETTERS -----------------------------------------------------------------
    @Override
    public byte[] getPhoto() {
        return multimedia;
    }

    @Override
    public boolean isImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    // SETTERS -----------------------------------------------------------------
    @Override
    public void setPhoto(byte[] multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public void setImage(boolean image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
