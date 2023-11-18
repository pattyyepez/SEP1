package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application
{
  public void start(Stage window) throws Exception
  {
    window.setTitle("Bob's Building Contractor Company");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("MainView.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}
