package View;

import Model.Project;
import Model.ProjectModelManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private ProjectModelManager modelManager;
  double xOffset, yOffset;

  private MainViewController mainViewController;
  private OnGoingProjectsController onGoingProjectsController;
  private CompletedProjectsController completedProjectsController;
  private FrontPageController frontPageController;
  private ViewEditProjectController projectInfoController;
  private AddProjectController addProjectController;
  private UpdateProjectController updateProjectController;



  public ViewHandler(Stage stage, ProjectModelManager modelManager) {
    this.stage = stage;
    this.modelManager = modelManager;

    this.stage.initStyle(StageStyle.TRANSPARENT);
    this.stage.setResizable(false);
  }

  public void start()
  {
    loadViewMain();
    openView("main", null);
  }

  public void openView(String id, Project project)
  {
    Scene window = null;
    switch (id)
    {
      case "main":
        window = mainViewController.getScene();
        mainViewController.getOnGoingProjectsController().updateProjects();
        mainViewController.getCompletedProjectsController().updateProjects();
        break;
      case "add":
        loadAddProject();
        window = addProjectController.getScene();
        break;
      case "view":
        loadViewProject(project);
        window = projectInfoController.getScene();
        break;
      case "update":
        loadUpdateProject(project);
        window = updateProjectController.getScene();
        break;
    }

    stage.setScene(window);

    String title = "";

    if(stage.getScene().getRoot().getUserData() !=null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
    }

    if(window != null){
      window.setOnMousePressed(event -> {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      });

      window.setOnMouseDragged(event -> {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
      });
    }

    stage.setTitle(title);
    stage.show();
  }

  private void loadViewMain()
  {
    try
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainView.fxml"));
        Region root = loader.load();

        Scene window = new Scene(root);
        window.setFill(Color.TRANSPARENT);

        mainViewController = loader.getController();
        mainViewController.initialize(this, window, modelManager);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }

  private void loadAddProject() {
  try {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("AddProject.fxml"));
    Region root = loader.load();
    Scene window = new Scene(root);
    window.setFill(Color.TRANSPARENT);
    window.getStylesheets().setAll(mainViewController.getScene().getStylesheets());
    addProjectController = loader.getController();
    addProjectController.initialize(this, window, modelManager);
  }
  catch (IOException e) {
    e.printStackTrace();
  }
}

  private void loadViewProject(Project project) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewEditProject.fxml"));
        Region root = loader.load();
        Scene window = new Scene(root);
        window.setFill(Color.TRANSPARENT);
        window.getStylesheets().setAll(mainViewController.getScene().getStylesheets());
        projectInfoController = loader.getController();
        projectInfoController.initialize(this, window, modelManager, project);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }

  private void loadUpdateProject(Project project) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("UpdateProject.fxml"));
      Region root = loader.load();
      Scene window = new Scene(root);
      window.setFill(Color.TRANSPARENT);
      window.getStylesheets().setAll(mainViewController.getScene().getStylesheets());
      updateProjectController = loader.getController();
      updateProjectController.initialize(this, window, modelManager, project);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Stage getStage(){
    return stage;
  }
}
