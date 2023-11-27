package View;

import Model.Project;
import Model.ProjectModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateProjectController {
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Project project;

  @FXML private TextField hoursField;
  @FXML private TextField expensesField;

  @FXML private Button cancelButton;
  @FXML private Button applyButton;

  public void initialize(Scene window, ViewHandler viewHandler, ProjectModelManager modelManager, Project project){
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.project = project;


  }

  public void handleActions(ActionEvent e){

    if(e.getSource() == cancelButton){
      viewHandler.openView("MainView", null);
    }

    else if(e.getSource() == applyButton){
      try{
        modelManager.removeProject(project.getTitle());

        if(Double.parseDouble(expensesField.getText()) >= 0 && Integer.parseInt(hoursField.getText()) >= 0){
          project.setExpectedExpenses(project.getExpectedExpenses() + Double.parseDouble(expensesField.getText()));
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
        viewHandler.openView("MainView", null);
      }
      catch(java.lang.NumberFormatException exception)  {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");
        alert.showAndWait();
      }

    }

  }

  public Scene getScene(){
    return window;
  }
}
