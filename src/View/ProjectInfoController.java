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

  @FXML VBox projectSpecificLabelPane;
  @FXML VBox projectSpecificFieldPane;

  @FXML Button backButton;
  @FXML Button actionButton;

//  RESIDENTIAL
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

// COMMERCIAL

  private Label commercialSizeLabel;
  private Label commercialFloorsLabel;
  private Label commercialIntendedUseLabel;

  private TextField commercialSizeField;
  private TextField commercialFloorsField;
  private TextField commercialIntendedUseField;

//  INDUSTRIAL

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
      Project project = null;
      try{
        switch (typeCombo.getSelectionModel().getSelectedItem()){
          case "Residential":
             project = new Residential(
                projectTitle.getText(),
                projectAddress.getText(),
                (projectBudgetMin.getText().isEmpty() ? 100000:
                    Double.parseDouble(projectBudgetMin.getText())),
                (projectBudgetMax.getText().isEmpty() ? 500000:
                    Double.parseDouble(projectBudgetMax.getText())),
                (projectTimeline.getText().isEmpty() ? 9:
                    Integer.parseInt(projectTimeline.getText())),
                new Customer(
                    customerName.getText(),
                    customerPhone.getText(),
                    customerEmail.getText()
                ),
                Double.parseDouble(residentialsizeField.getText()),
                (residentialkitchenField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialkitchenField.getText())),
                (residentialbathroomField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialbathroomField.getText())),
                (residentialroomsWPField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialroomsWPField.getText())),
                residentialnewBuild.isSelected()
             );
          break;
          case "Commercial":
            project = new Commercial(
                projectTitle.getText(),
                projectAddress.getText(),
                (projectBudgetMin.getText().isEmpty() ? 100000:
                    Double.parseDouble(projectBudgetMin.getText())),
                (projectBudgetMax.getText().isEmpty() ? 500000:
                    Double.parseDouble(projectBudgetMax.getText())),
                (projectTimeline.getText().isEmpty() ? 9:
                    Integer.parseInt(projectTimeline.getText())),
                new Customer(
                    customerName.getText(),
                    customerPhone.getText(),
                    customerEmail.getText()
                ),
                Double.parseDouble(commercialSizeField.getText()),
                (commercialFloorsField.getText().isEmpty() ? 1:
                    Integer.parseInt(commercialFloorsField.getText())),
                commercialIntendedUseField.getText()
            );
          break;
        }
      }
      catch(java.lang.NumberFormatException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You left one of the necessary fields empty.");
        alert.showAndWait();
      }
      if(project!=null) {
        modelManager.addProject(project);
        viewHandler.openView("MainView");
      }
    }

  }

  public Scene getScene(){
    return window;
  }

  public void updateFields(){
    switch (typeCombo.getSelectionModel().getSelectedItem()){
      case "Residential":

        projectBudgetMin.setPromptText("100000");
        projectBudgetMax.setPromptText("500000");
        projectTimeline.setPromptText("9");

        residentialsizeLabel = new Label("Building size*");
        residentialkitchenLabel = new Label("Number of\nkitchens");
        residentialkitchenLabel.setTextAlignment(TextAlignment.CENTER);
        residentialbathroomLabel = new Label("Number of\nbathrooms");
        residentialbathroomLabel.setTextAlignment(TextAlignment.CENTER);
        residentialroomsWPLabel = new Label("Number of\nother rooms\nwith plumbing");
        residentialroomsWPLabel.setTextAlignment(TextAlignment.CENTER);
        residentialnewBuildLabel = new Label("New build?");

        projectSpecificLabelPane.getChildren().setAll(residentialsizeLabel, residentialkitchenLabel,
            residentialbathroomLabel, residentialroomsWPLabel, residentialnewBuildLabel);
        projectSpecificLabelPane.setSpacing(20);

        residentialsizeField = new TextField();
        residentialkitchenField = new TextField();
        residentialkitchenField.setPromptText("1");
        residentialbathroomField = new TextField();
        residentialbathroomField.setPromptText("1");
        residentialroomsWPField = new TextField();
        residentialroomsWPField.setPromptText("1");

        residentialnewBuild = new ToggleButton("True");
        residentialnewBuild.setSelected(true);

        projectSpecificFieldPane.getChildren().setAll(residentialsizeField, residentialkitchenField,
            residentialbathroomField, residentialroomsWPField, residentialnewBuild);
        projectSpecificFieldPane.setSpacing(30);
        break;
      case "Commercial":
        projectBudgetMin.setPromptText("500000");
        projectBudgetMax.setPromptText("2000000");
        projectTimeline.setPromptText("18");

        commercialSizeLabel = new Label("Building size*");
        commercialFloorsLabel = new Label("Number of floors");
        commercialIntendedUseLabel = new Label("Intended use*");

        projectSpecificLabelPane.getChildren().setAll(commercialSizeLabel, commercialFloorsLabel,
            commercialIntendedUseLabel);
        projectSpecificLabelPane.setSpacing(21);

        commercialSizeField = new TextField();
        commercialFloorsField = new TextField();
        commercialFloorsField.setPromptText("1");
        commercialIntendedUseField = new TextField();

        projectSpecificFieldPane.getChildren().setAll(commercialSizeField, commercialFloorsField,
            commercialIntendedUseField);
        projectSpecificFieldPane.setSpacing(15);
        break;
      case "Industrial":
        projectSpecificLabelPane.getChildren().setAll();
        projectSpecificFieldPane.getChildren().setAll();
        break;
      case "Road":
        projectSpecificLabelPane.getChildren().setAll();
        projectSpecificFieldPane.getChildren().setAll();
        break;
    }
  }

}
