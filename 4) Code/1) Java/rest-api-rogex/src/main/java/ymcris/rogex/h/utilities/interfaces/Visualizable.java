package ymcris.rogex.h.utilities.interfaces;


/**
 * The Visualizable interface is responsible for providing the ability to save
 * imagines of DB
 *
 * @author YmCris
 * @since Dec 25, 2025
 */
public interface Visualizable {

    // ABSTRACT METHODS --------------------------------------------------------
    public byte[] getMultimedia();

    public void setMultimedia(byte[] multimedia);

    public boolean isImage();

    public void setImage(boolean isImage);
}
