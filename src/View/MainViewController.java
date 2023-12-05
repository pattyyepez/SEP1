package View;

import Model.ProjectList;
import Model.ProjectModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainViewController {
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Scene window;

  @FXML private OnGoingProjectsController onGoingProjectsController;
  @FXML private CompletedProjectsController completedProjectsController;
  @FXML private FrontPageController frontPageController;

  @FXML private MenuBar menuBar;
  @FXML private MenuItem menuTxt;
  @FXML private MenuItem menuExcel;
  @FXML private MenuItem menuThemeLight;
  @FXML private MenuItem menuThemeDark;
  @FXML private MenuItem menuExit;
  @FXML private MenuItem menuUserManual;
  @FXML private MenuItem menuContact;


  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager){
    this.viewHandler = viewHandler;
    this.window = window;
    this.modelManager = modelManager;

    changeThemeToLight();

    frontPageController.initialize(viewHandler, modelManager);
    onGoingProjectsController.initialize(viewHandler, modelManager);
    completedProjectsController.initialize(viewHandler, modelManager);
  }

  public void handleActions(ActionEvent e){

    if(e.getSource() == menuTxt){
      try{
        saveAsTxt();
      }
      catch(IOException exception){
        exception.printStackTrace();
      }
    }

    else if(e.getSource() == menuExcel){



    }

    else if(e.getSource() == menuExit){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);
      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES) {
        System.exit(0);
      }
    }

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

  public FrontPageController getFrontPageController(){
    return frontPageController;
  }

  public void saveAsTxt() throws IOException
  {
    String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
    File textFile = new File(desktopPath, "projects.txt");
    ProjectList allProjects = modelManager.getAllProjects();
    FileWriter writer = null;

    try{
      writer = new FileWriter(textFile);
      for(int x = 0; x < allProjects.getSize(); x++){
        writer.write(allProjects.getProject(x).toString());
        writer.write("\n~~~~~~~~~~~~~~~~~~~~\n");
      }
    }
    catch (IOException exception){
      exception.printStackTrace();
    }
    finally {
      if(writer != null){
        System.out.println(textFile.getAbsolutePath());
        writer.close();
      }
    }
  }

  public void changeThemeToLight() {
    window.getStylesheets().setAll(getClass().getResource("css/themeLight.css").toExternalForm());
    frontPageController.changeImageLight();

    menuThemeLight.setDisable(true);
    menuThemeDark.setDisable(false);
  }

  public void changeThemeToDark() {
    window.getStylesheets().setAll(getClass().getResource("css/themeDark.css").toExternalForm());
    frontPageController.changeImageDark();

    menuThemeLight.setDisable(false);
    menuThemeDark.setDisable(true);
  }

}
