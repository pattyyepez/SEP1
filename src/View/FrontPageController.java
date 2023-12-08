package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FrontPageController {

  @FXML private ImageView image;

  public void changeImageLight(){
    image.setImage(new Image(getClass().getResource("images/logoLight.png").toExternalForm()));
  }

  public void changeImageDark(){
    image.setImage(new Image(getClass().getResource("images/logoDark.png").toExternalForm()));
  }

}
