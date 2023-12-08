package View;

import Model.ProjectList;
import Model.ProjectModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainViewController {
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Scene window;

  @FXML private OnGoingProjectsController onGoingProjectsController;
  @FXML private CompletedProjectsController completedProjectsController;
  @FXML private FrontPageController frontPageController;

  @FXML private MenuItem menuTxt;
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

    onGoingProjectsController.initialize(viewHandler, modelManager);
    completedProjectsController.initialize(viewHandler, modelManager);
  }

  public void handleActions(ActionEvent e){

    if(e.getSource() == menuTxt){
      try{
        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        File textFile = new File(desktopPath, "projects.txt");
        ProjectList allProjects = modelManager.getAllProjects();
        FileWriter writer = new FileWriter(textFile);

        for(int x = 0; x < allProjects.getSize(); x++){
          writer.write(allProjects.getProject(x).toString());
          writer.write("\n~~~~~~~~~~~~~~~~~~~~\n");
        }

        writer.close();
      }
      catch(IOException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Program couldn't save project information as a text file.");
        alert.setTitle("Text error");
        alert.setHeaderText(null);
      }
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

    else if(e.getSource() == menuContact){
      try{
        Desktop.getDesktop().mail(new URI("mailto:344353@viauc.dk;343873@viauc.dk;345784@viauc.dk;344688@viauc.dk"));
      }
      catch(IOException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Program couldn't find a default e-mail application.");
        alert.setTitle("Email error");
        alert.setHeaderText(null);
      }
      catch (URISyntaxException exception)
      {
        exception.printStackTrace();
      }
    }

    else if(e.getSource() == menuUserManual){
      try{
        String path = getClass().getResource("documents/user-manual.pdf").toExternalForm();
        path = path.substring(6);
        Desktop.getDesktop().open(new File(path));
      }
      catch (IllegalArgumentException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Program couldn't find the user manual.");
        alert.setTitle("Program error");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      catch (IOException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Unidentified error");
        alert.setTitle("The program couldn't open the pdf file.");
        alert.setHeaderText(null);
        alert.showAndWait();
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

  public void minimize(){
    viewHandler.getStage().setIconified(true);
  }

  public void exit(){
    System.exit(0);
  }

}
