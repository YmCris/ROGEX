package ymcris.rogex.c.dtos.banner;

import ymcris.rogex.e.models.banner.MainBanner;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The MainBannerResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 26, 2025
 */
public class MainBannerResponse implements GenericObjectResponse{

    // REFERENCE VARIABLES -----------------------------------------------------
    private String link;

    // PRIMITIVE VARIABLES -----------------------------------------------------
    private boolean isImage;
    
    // CONSTRUCTOR METHOD ------------------------------------------------------
    public MainBannerResponse(MainBanner mainBanner) {
        this.link = mainBanner.getLink();
        this.isImage = mainBanner.isImage();
    }

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
