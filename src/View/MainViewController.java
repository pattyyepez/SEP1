package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;

public class MainViewController {
  @FXML private Tab onGoingTab;
  //  @FXML private VBox onGoingProjects;
//  @FXML private VBox addProject;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Scene window;

  @FXML private OnGoingProjectsController onGoingProjectsController;
  @FXML private CompletedProjectsController completedProjectsController;
  @FXML private ViewEditProjectController projectInfoController;

  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager){
    this.viewHandler = viewHandler;
    this.window = window;
    this.modelManager = modelManager;

    onGoingProjectsController.initialize(viewHandler, modelManager);
    completedProjectsController.initialize(viewHandler, modelManager);
  }

  public Scene getScene(){
    return window;
  }

  public OnGoingProjectsController getOnGoingProjectsController(){
    return onGoingProjectsController;
  }

  public CompletedProjectsController getCompletedProjectsController(){
    return completedProjectsController;
  }
}
