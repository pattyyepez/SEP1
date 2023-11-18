package View;

import Model.ProjectModelManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import javax.swing.text.View;

public class MainViewController {
  @FXML private Tab onGoingTab;
  //  @FXML private VBox onGoingProjects;
//  @FXML private VBox addProject;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Scene window;

  @FXML private OnGoingProjectsController onGoingProjectsController;
  @FXML private CompletedProjectsController completedProjectsController;
  @FXML private ProjectInfoController projectInfoController;

  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager){
    this.viewHandler = viewHandler;
    this.window = window;
    this.modelManager = modelManager;
//    addProject.setVisible(false);
////    addProject.setManaged(false);
//    addProject.managedProperty().bind(addProject.visibleProperty());
    onGoingProjectsController.initialize(viewHandler, modelManager, window);
    completedProjectsController.initialize(viewHandler, modelManager);
//    projectInfoController.initialize(window, "ADD");

  }

  public Scene getScene(){
    return window;
  }

  public OnGoingProjectsController getOnGoingProjectsController(){
    return onGoingProjectsController;
  }

  public CompletedProjectsController completedProjectsController(){
    return completedProjectsController;
  }
}
