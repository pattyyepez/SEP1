package View;

import Model.ProjectModelManager;
import View.CompletedProjectsController;
import View.MainViewController;
import View.OnGoingProjectsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private MainViewController mainViewController;
  private OnGoingProjectsController onGoingProjectsController;
  private CompletedProjectsController completedProjectsController;
  private ProjectInfoController projectInfoController;

  private ProjectModelManager modelManager;

  public ViewHandler(Stage stage, ProjectModelManager modelManager)
  {
    this.stage = stage;
    this.modelManager = modelManager;

    stage.setResizable(false);
  }

  public void start()
  {
    loadViewMain();
    loadAddProject();
    openView("MainView");
  }

  public void openView(String id)
  {
    switch (id)
    {
      case "MainView":
        stage.setScene(mainViewController.getScene());
        mainViewController.getOnGoingProjectsController().updateProjects();
        break;
      case "AddProject":
        stage.setScene(projectInfoController.getScene());
        break;
      case "ChangeCountryView":
//        stage.setScene(changeCountryViewController.getScene());
//        changeCountryViewController.reset();
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
      catch (IOException e)
      {
        e.printStackTrace();
      }
  }

  private void loadAddProject()
  {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProjectInfo.fxml"));
        Region root = loader.load();
        projectInfoController = loader.getController();
        projectInfoController.initialize(this, new Scene(root), "ADD", modelManager);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
  }
//
//  private void loadViewChangeCountry()
//  {
//      try
//      {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("ChangeCountryView.fxml"));
//        Region root = loader.load();
//        changeCountryViewController = loader.getController();
//        changeCountryViewController.init(this, new Scene(root), modelManager);
//      }
//      catch (IOException e)
//      {
//        e.printStackTrace();
//      }
//  }
}
