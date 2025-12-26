package ymcris.rogex.c.dtos.banner;

import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;

/**
 * The NewMainBannerRequest class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
public class NewMainBannerRequest extends GenericNewObjectRequest {

    // REFERENCE VARIABLES -----------------------------------------------------
    private String link;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean isImage;

    // GETTERS -----------------------------------------------------------------
    public String getLink() {
        return link;
    }

    public boolean isIsImage() {
        return isImage;
    }

    // SETTERS -----------------------------------------------------------------
    public void setLink(String link) {
        this.link = link;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }

}
