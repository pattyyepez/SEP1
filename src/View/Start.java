package View;

import Model.ProjectModelManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import parser.ParserException;
import parser.XmlJsonParser;

import javax.swing.text.View;
import java.io.File;

/**
 * Used by the BobsCoGUITest class to launch the application. Also is responsible for
 * saving all the project information in an XML file, as well as creating the ProjectModelManager
 * and ViewHandler that are passed down to every page in the application through method parameters.
 */
public class Start extends Application
{
  public static File file;
  public static XmlJsonParser parser;

  /**
   * The method that creates the ProjectModelManager and ViewHandler, it is required for the proper functioning
   * of JavaFX and for the application being launched by the BobsCoGUITest class.
   *
   * @param window The primary stage for this application, onto which
   * the application scene can be set.
   * @throws ParserException     If any exceptions parsing, transforming, writing or reading.
   */
  public void start(Stage window) throws ParserException
  {
    parser = new XmlJsonParser();
    ProjectModelManager modelManager = new ProjectModelManager("projects.bin");
    file = parser.toXml(modelManager.getAllProjects(), "projects.xml");
    ViewHandler viewHandler = new ViewHandler(window, modelManager);
    viewHandler.start();
  }
}
