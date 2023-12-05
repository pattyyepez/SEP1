package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FrontPageController {
  private ProjectModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private ImageView image;

  public void initialize(ViewHandler viewHandler, ProjectModelManager modelManager) {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
  }

  public void changeImageLight(){
    image.setImage(new Image(getClass().getResource("images/logoLight.png").toExternalForm()));
  }

  public void changeImageDark(){
    image.setImage(new Image(getClass().getResource("images/logoDark.png").toExternalForm()));
  }

}
