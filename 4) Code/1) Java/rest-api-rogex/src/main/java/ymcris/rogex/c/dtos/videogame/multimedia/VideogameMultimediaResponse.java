package ymcris.rogex.c.dtos.videogame.multimedia;

import ymcris.rogex.e.models.videogame.multimedia.VideogameMultimedia;
import ymcris.rogex.g.commons.constants.RestConstants;
import ymcris.rogex.g.commons.dtos.GenericObjectResponse;

/**
 * The VideogameMultimediaResponse class is the class responsible for
 *
 * @author YmCris
 * @since Dec 29, 2025
 */
public class VideogameMultimediaResponse implements GenericObjectResponse {

    // REFERENCE VARIABLES -----------------------------------------------------
    private boolean image;
    private String videogameTitle;
    private String enterpriseName;
    private int id;
    private String imageUrl;

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameMultimediaResponse(VideogameMultimedia multimedia) {
        this.image = multimedia.isImage();
        this.videogameTitle = multimedia.getVideogameTitle();
        this.enterpriseName = multimedia.getEnterpriseName();
        this.id = multimedia.getId();
        this.imageUrl = RestConstants.API_URL+"videogames/multimedia/"+id+"/image";
    }

    // GETTERS -----------------------------------------------------------------
    public boolean isImage() {
        return image;
    }

    public String getVideogameTitle() {
        return videogameTitle;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // SETTERS -----------------------------------------------------------------
    public void setImage(boolean image) {
        this.image = image;
    }

    public void setVideogameTitle(String videogameTitle) {
        this.videogameTitle = videogameTitle;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
