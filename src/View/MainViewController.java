package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainViewController {
  private ProjectModelManager modelManager;

  @FXML private OnGoingProjectsController onGoingProjectsController;
  @FXML private CompletedProjectsController completedProjectsController;

  public void initialize(){
    modelManager = new ProjectModelManager("projects.bin");
    onGoingProjectsController.initialize(modelManager);
    completedProjectsController.initialize(modelManager);
  }
}
