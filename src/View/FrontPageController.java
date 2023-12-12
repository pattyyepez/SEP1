package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Probably the most (with all due respect) useless controller, used for changing the image on the
 * "Welcome" screen according to the current theme of the application.
 */
public class FrontPageController {

  @FXML private ImageView image;

  /**
   * Changes the image to a dark grayish - blackish version of BBCC's logo (designer Maria Yepez)
   */
  public void changeImageLight(){
    image.setImage(new Image(getClass().getResource("images/logoLight.png").toExternalForm()));
  }

  /**
   * Changes the image to a light whitish version of BBCC's logo (designer Maria Yepez)
   */
  public void changeImageDark(){
    image.setImage(new Image(getClass().getResource("images/logoDark.png").toExternalForm()));
  }

}
