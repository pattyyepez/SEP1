package View;

import Model.Project;
import Model.ProjectModelManager;
import View.CompletedProjectsController;
import View.MainViewController;
import View.OnGoingProjectsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private MainViewController mainViewController;
  private OnGoingProjectsController onGoingProjectsController;
  private CompletedProjectsController completedProjectsController;
  private ProjectInfoController projectInfoController;

  private ProjectModelManager modelManager;

  public ViewHandler(Stage stage, ProjectModelManager modelManager) {
    this.stage = stage;
    this.modelManager = modelManager;

    stage.setResizable(false);
  }

  public void start()
  {
    loadViewMain();
    openView("MainView", null);
  }

  public void openView(String id, Project project)
  {
    switch (id)
    {
      case "MainView":
        stage.setScene(mainViewController.getScene());
        mainViewController.getOnGoingProjectsController().updateProjects();
        break;
      case "AddProject":
        loadAddProject();
        stage.setScene(projectInfoController.getScene());
        break;
      case "ViewProject":
        loadViewProject(project);
        stage.setScene(projectInfoController.getScene());
        break;
    }

    String title = "";

    if(stage.getScene().getRoot().getUserData() !=null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
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
        loader.setLocation(getClass().getResource("ProjectInfo.fxml"));
        Region root = loader.load();
        projectInfoController = loader.getController();
        projectInfoController.initAdd(this, new Scene(root), modelManager);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }

  private void loadViewProject(Project project) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProjectInfo.fxml"));
        Region root = loader.load();
        projectInfoController = loader.getController();
        projectInfoController.initView(this, new Scene(root), modelManager, project);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
  }
}
