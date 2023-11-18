package View;

import Model.*;
import Utils.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ProjectInfoController {
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;

  @FXML ComboBox<String> typeCombo;

  @FXML private TextField customerName;
  @FXML private TextField customerPhone;
  @FXML private TextField customerEmail;

  @FXML private TextField projectTitle;
  @FXML private TextField projectAddress;
  @FXML private TextField projectBudgetMin;
  @FXML private TextField projectBudgetMax;
  @FXML private TextField projectTimeline;

  @FXML VBox specificPane;
  @FXML VBox projectSpecificLabelPane;
  @FXML VBox projectSpecificFieldPane;

  @FXML Button backButton;
  @FXML Button actionButton;

  private Label residentialsizeLabel;
  private Label residentialkitchenLabel;
  private Label residentialbathroomLabel;
  private Label residentialroomsWPLabel;
  private Label residentialnewBuildLabel;

  private TextField residentialsizeField;
  private TextField residentialkitchenField;
  private TextField residentialbathroomField;
  private TextField residentialroomsWPField;
  private ToggleButton residentialnewBuild;

  public void initialize(ViewHandler viewHandler, Scene window, String action, ProjectModelManager modelManager){
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;

    switch (action){
      case "ADD":

        typeCombo.getItems().addAll(
            "Residential",
            "Commercial",
            "Industrial",
            "Road"
        );

        actionButton.setText("Create");
        typeCombo.getSelectionModel().selectFirst();
        updateFields();

        break;
      case "VIEW":

        break;
      case "EDIT":

        break;
    }
  }

  public void handleActions(ActionEvent e) {
    if (e.getSource() == backButton) {
      viewHandler.openView("MainView");
    }
    else if(e.getSource() == actionButton){
      if(typeCombo.getSelectionModel().getSelectedItem().equals("Residential")){
        Residential project = new Residential(
            projectTitle.getText(),
            projectAddress.getText(),
            Double.parseDouble(projectBudgetMin.getText()),
            Double.parseDouble(projectBudgetMax.getText()),
            Integer.parseInt(projectTimeline.getText()),
            new Customer(
                customerName.getText(),
                customerPhone.getText(),
                customerEmail.getText()
            ),
            Double.parseDouble(residentialsizeField.getText()),
            Integer.parseInt(residentialkitchenField.getText()),
            Integer.parseInt(residentialbathroomField.getText()),
            Integer.parseInt(residentialroomsWPField.getText()),
            residentialnewBuild.isSelected()
        );
        modelManager.addProject(project);
        viewHandler.openView("MainView");
      }
    }

  }

  public Scene getScene(){
    return window;
  }

  public void updateFields(){

    projectSpecificFieldPane.getChildren().setAll();
    projectSpecificLabelPane.getChildren().setAll();

    switch (typeCombo.getSelectionModel().getSelectedItem()){
      case "Residential":

        residentialsizeLabel = new Label("Building size");
        residentialkitchenLabel = new Label("Number of\nkitchens");
        residentialkitchenLabel.setTextAlignment(TextAlignment.CENTER);
        residentialbathroomLabel = new Label("Number of\nbathrooms");
        residentialbathroomLabel.setTextAlignment(TextAlignment.CENTER);
        residentialroomsWPLabel = new Label("Number of\nother rooms\nwith plumbing");
        residentialroomsWPLabel.setTextAlignment(TextAlignment.CENTER);
        residentialnewBuildLabel = new Label("New build?");

        projectSpecificLabelPane.getChildren().addAll(residentialsizeLabel, residentialkitchenLabel,
            residentialbathroomLabel, residentialroomsWPLabel, residentialnewBuildLabel);
        projectSpecificLabelPane.setSpacing(20);

        residentialsizeField = new TextField();
        residentialkitchenField = new TextField();
        residentialbathroomField = new TextField();
        residentialroomsWPField = new TextField();

        residentialnewBuild = new ToggleButton("True");
        residentialnewBuild.setSelected(true);

        projectSpecificFieldPane.getChildren().addAll(residentialsizeField, residentialkitchenField,
            residentialbathroomField, residentialroomsWPField, residentialnewBuild);
        projectSpecificFieldPane.setSpacing(30);
        break;
      case "Commercial":
        projectSpecificLabelPane.getChildren().setAll();
        projectSpecificFieldPane.getChildren().setAll();
        break;
    }
  }

}
