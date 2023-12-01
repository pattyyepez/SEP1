package View;

import Model.Project;
import Model.ProjectModelManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private MainViewController mainViewController;
  private OnGoingProjectsController onGoingProjectsController;
  private CompletedProjectsController completedProjectsController;
  private ViewEditProjectController projectInfoController;
  private AddProjectController addProjectController;
  private UpdateProjectController updateProjectController;

  private ProjectModelManager modelManager;

  public ViewHandler(Stage stage, ProjectModelManager modelManager) {
    this.stage = stage;
    this.modelManager = modelManager;

    stage.setResizable(false);
  }

  public void start()
  {
    loadViewMain();
    openView("main", null);
  }

  public void openView(String id, Project project)
  {
    switch (id)
    {
      case "main":
        stage.setScene(mainViewController.getScene());
        mainViewController.getOnGoingProjectsController().updateProjects();
        mainViewController.getCompletedProjectsController().updateProjects();
        break;
      case "add":
        loadAddProject();
        stage.setScene(addProjectController.getScene());
        break;
      case "view":
        loadViewProject(project);
        stage.setScene(projectInfoController.getScene());
        break;
      case "update":
        loadUpdateProject(project);
        stage.setScene(updateProjectController.getScene());
        break;
    }

    String title = "";

    if(stage.getScene().getRoot().getUserData() !=null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
    }

    stage.setTitle(title);
//    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  private void loadViewMain()
  {
    try
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainView.fxml"));
        Region root = loader.load();
        mainViewController = loader.getController();
        mainViewController.initialize(this, new Scene(root), modelManager);
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
    addProjectController = loader.getController();
    addProjectController.initialize(this, new Scene(root), modelManager);
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
        projectInfoController = loader.getController();
        projectInfoController.initialize(this, new Scene(root), modelManager, project);
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
      updateProjectController = loader.getController();
      updateProjectController.initialize(new Scene(root), this, modelManager, project);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
