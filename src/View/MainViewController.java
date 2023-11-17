package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;

public class MainViewController {
  private ProjectModelManager modelManager;

  @FXML private AllProjectsController allProjectsController;

  public void initialize(){
    modelManager = new ProjectModelManager("projects.bin");
    allProjectsController.initialize(modelManager);
  }
}
