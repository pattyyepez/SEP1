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
    Scene window = null;                                                        // T = 2 = O(1)
    switch (id)                                                                 // T = 1 = O(1)
    {
      case "main":
        window = mainViewController.getScene();                                 // T = 2 = O(1)
        mainViewController.getOnGoingProjectsController().updateProjects();     // T = 1 + n = O(n)
        mainViewController.getCompletedProjectsController().updateProjects();   // T = 1 + n = O(n)
        break;                                                                  // T = 1 = O(1)
      case "add":
        loadAddProject();                                                       // T = 1 = O(1)
        window = addProjectController.getScene();                               // T = 2 = O(1)
        break;
      case "view":
        loadViewProject(project);                                               // T = 1 = O(1)
        window = viewEditProjectController.getScene();                          // T = 2 = O(1)
        break;                                                                  // T = 1 = O(1)
      case "update":
        loadUpdateProject(project);                                             // T = 1 = O(1)
        window = updateProjectController.getScene();                            // T = 2 = O(1)
        break;                                                                  // T = 1 = O(1)
    }

    stage.setScene(window);                                                     // T = 1 = O(1)

    String title = "";                                                          // T = 2 = O(1)

    if(stage.getScene().getRoot().getUserData() !=null)                         // T = 4 = O(1)
    {
      title = stage.getScene().getRoot().getUserData().toString();              // T = 5 = O(1)
    }

    if(window != null){                                                         // T = 1 = O(1)
      window.setOnMousePressed(event -> {                                       // T = 1 = O(1)
        xOffset = event.getSceneX();                                            // T = 2 = O(1)
        yOffset = event.getSceneY();                                            // T = 2 = O(1)
      });

      window.setOnMouseDragged(event -> {                                       // T = 1 = O(1)
        stage.setX(event.getScreenX() - xOffset);                               // T = 3 = O(1)
        stage.setY(event.getScreenY() - yOffset);                               // T = 3 = O(1)
      });
    }

    stage.setTitle(title);                                                      // T = 1 = O(1)
    stage.show();                                                               // T = 1 = O(1)
  }                                                                             // Final Big O notation of this method is O(n) linear time, due to its
                                                                                // use of the updateProjects() methods, both of which have a time complexity
                                                                                // of O(n), as they have for loops that cycle through the entire length of the
                                                                                // ProjectList.

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
