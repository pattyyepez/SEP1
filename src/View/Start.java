package View;

import Model.ProjectModelManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parser.ParserException;
import parser.XmlJsonParser;

import javax.swing.text.View;
import java.io.File;

public class Start extends Application
{
  static File file;
  static XmlJsonParser parser;
  public void start(Stage window) throws ParserException
  {
    parser = new XmlJsonParser();
    ProjectModelManager modelManager = new ProjectModelManager("projects.bin");
    file = parser.toXml(modelManager.getAllProjects(), "projects.xml");
    ViewHandler viewHandler = new ViewHandler(window, modelManager);
    viewHandler.start();
  }
}
