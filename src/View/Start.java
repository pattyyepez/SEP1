package View;

import Model.ProjectModelManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;

public class Start extends Application
{
  public void start(Stage window) {
    ProjectModelManager modelManager = new ProjectModelManager("projects.bin");
    ViewHandler viewHandler = new ViewHandler(window, modelManager);
    viewHandler.start();
  }
}
