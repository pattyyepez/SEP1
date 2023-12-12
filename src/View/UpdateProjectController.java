package View;

import Model.Project;
import Model.ProjectModelManager;
import View.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The controller for the FXML file called "UpdateProject", it is part of the GUI and is displayed by the OnGoingProjectsController.
 * As the name suggests, it is responsible for updating projects, that is, allowing the user to add new values of hours and expenses spent
 * towards a project's completion in a single day. These values will be added to the total hours and total expenses counters respectively.
 */
public class UpdateProjectController {
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Project project;

  @FXML private TextField hoursField;
  @FXML private TextField expensesField;

  @FXML private Button cancelButton;
  @FXML private Button applyButton;

  /**
   * Initializes the controller class by assigning all the parameters to appropriate fields.
   *
   * @param viewHandler     Loads in the mainView when the user cancels or finishes updating a project.
   * @param window          Used for GUI, tying this window together with the MainView, used for displaying this window by the ViewHandler.
   * @param modelManager    Allows the controller to add data/append data of a given project and save it to the binary file.
   * @param project         A specific project, the total hours and total expenses of which will have a value added to it.
   */
  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager, Project project){
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.project = project;
  }

  /**
   * Adds functionality to the 2 available buttons.
   *
   * @param e     Used by the program to distinguish between the two buttons when they are pressed.
   */
  public void handleActions(ActionEvent e){

    if(e.getSource() == cancelButton){
      viewHandler.openView("main", null);
    }

    else if(e.getSource() == applyButton){
      try{
        modelManager.removeProject(project.getTitle());

        if(Double.parseDouble(expensesField.getText()) >= 0 && Integer.parseInt(hoursField.getText()) >= 0){
          project.setTotalExpenses(project.getTotalExpenses() + Double.parseDouble(expensesField.getText()));
          project.setTotalHours(project.getTotalHours() + Integer.parseInt(hoursField.getText()));
        }

        else if(Double.parseDouble(expensesField.getText()) < 0 && Integer.parseInt(hoursField.getText()) >= 0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("You put a negative value in the 'Expenses' field. Please only enter a value equal to or greater than 0.");
          alert.showAndWait();
        }

        else if(Double.parseDouble(expensesField.getText()) >= 0 && Integer.parseInt(hoursField.getText()) < 0){
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("You put a negative value in the 'Hours' field. Please only enter a value equal to or greater than 0.");
          alert.showAndWait();
        }

        else{
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setHeaderText(null);
          alert.setContentText("You put a negative value in both the 'Hours' and 'Expenses' fields. Please only enter a value equal to or greater than 0.");
          alert.showAndWait();
        }

        modelManager.addProject(project);
        viewHandler.openView("main", null);
      }
      catch(java.lang.NumberFormatException exception)  {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");
        alert.showAndWait();
      }

    }

  }

  /**
   * Returns the Scene of this page.
   *
   * @return  The scene of this page.
   */
  public Scene getScene(){
    return window;
  }
}
