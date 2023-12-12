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

/**
 * The class responsible for managing and showing all the different windows and pages that have to be loaded in.
 * Also makes the application draggable.
 */
public class ViewHandler
{
  private Stage stage;
  private ProjectModelManager modelManager;
  double xOffset, yOffset;

  private MainViewController mainViewController;
  private ViewEditProjectController viewEditProjectController;
  private AddProjectController addProjectController;
  private UpdateProjectController updateProjectController;

  /**
   * Constructor that assigns the parameters to their respective fields and sets the style of the stage
   * as transparent, so that we can create a custom style for it ourselves. Also makes the application non-resizable.
   *
   * @param stage
   * @param modelManager
   */
  public ViewHandler(Stage stage, ProjectModelManager modelManager) {
    this.stage = stage;
    this.modelManager = modelManager;

    this.stage.initStyle(StageStyle.TRANSPARENT);
    this.stage.setResizable(false);
  }

  /**
   * Makes sure that the mainView is the first window that's loaded upon start-up of the application.
   */
  public void start()
  {
    loadViewMain();
    openView("main", null);
  }

  /**
   * Opens all the different windows that are part of the GUI, but not shown by the mainView window as one of the tabs.
   *
   * @param id        String ID of the window to be initialized, loaded and shown.
   * @param project   Some windows, such as viewing/editing and updating, require a specific project for their functionin,
   *                  while some don't, such as the mainView or adding a new project.
   */
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
        window = viewEditProjectController.getScene();
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

  /**
   * Loads in the mainView's FXML file and initializes its controller.
   */
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

  /**
   * Loads in the addProject's FXML file and initializes its controller.
   */
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

  /**
   * Loads in the ViewEditProject's FXML file and initializes its controller.
   */
  private void loadViewProject(Project project) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewEditProject.fxml"));
        Region root = loader.load();
        Scene window = new Scene(root);
        window.setFill(Color.TRANSPARENT);
        window.getStylesheets().setAll(mainViewController.getScene().getStylesheets());
        viewEditProjectController = loader.getController();
        viewEditProjectController.initialize(this, window, modelManager, project);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }

  /**
   * Loads in the UpdateProject's FXML file and initializes its controller.
   */
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

  /**
   * Returns the stage used by the application to display all the FXML files.
   *
   * @return The application's stage used to display a given window's Scene.
   */
  public Stage getStage(){
    return stage;
  }
}
