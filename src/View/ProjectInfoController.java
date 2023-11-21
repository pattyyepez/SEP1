package View;

import Model.*;
import Utils.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

//  RESIDENTIAL
  private Label residentialSizeLabel;
  private Label residentialKitchenLabel;
  private Label residentialBathroomLabel;
  private Label residentialRoomsWPLabel;
  private Label residentialRenovationLabel;

  private TextField residentialSizeField;
  private TextField residentialKitchenField;
  private TextField residentialBathroomField;
  private TextField residentialRoomsWPField;
  private ToggleButton residentialRenovation;

// COMMERCIAL

  private Label commercialSizeLabel;
  private Label commercialFloorsLabel;
  private Label commercialIntendedUseLabel;

  private TextField commercialSizeField;
  private TextField commercialFloorsField;
  private TextField commercialIntendedUseField;

//  INDUSTRIAL

  private Label industrialSizeLabel;
  private Label industrialIntendedUseLabel;

  private TextField industrialSizeField;
  private TextField industrialIntendedUseField;

//  ROAD

  private Label roadLengthLabel;
  private Label roadWidthLabel;
  private Label roadBortLabel;
  private Label roadChallengesLabel;

  private TextField roadLengthField;
  private TextField roadWidthField;
  private TextField roadBortField;
  private TextArea roadChallengesArea;

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
                Double.parseDouble(residentialSizeField.getText()),
                (residentialKitchenField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialKitchenField.getText())),
                (residentialBathroomField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialBathroomField.getText())),
                (residentialRoomsWPField.getText().isEmpty() ? 1:
                    Integer.parseInt(residentialRoomsWPField.getText())),
                !residentialRenovation.isSelected()
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
          case "Industrial":
            project = new Industrial(
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
                Double.parseDouble(industrialSizeField.getText()),
                industrialIntendedUseField.getText()
            );
            break;
          case "Road":
            project = new Road(
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
                Double.parseDouble(roadLengthField.getText()),
                Double.parseDouble(roadWidthField.getText()),
                Integer.parseInt(roadBortField.getText())
            );
            if(!roadChallengesArea.getText().isEmpty()){
              for(String temp : roadChallengesArea.getText().split(",", 0)){
                ((Road) project).addChallenge(temp);
              }
            }
            break;
        }
      }
      catch(java.lang.NumberFormatException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");
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
    projectSpecificLabelPane.setAlignment(Pos.CENTER_RIGHT);
    specificPane.getChildren().remove(residentialRenovation);
    switch (typeCombo.getSelectionModel().getSelectedItem()){
      case "Residential":

        projectBudgetMin.setPromptText("100000");
        projectBudgetMax.setPromptText("500000");
        projectTimeline.setPromptText("9");

        residentialSizeLabel = new Label("Building size*");
        residentialKitchenLabel = new Label("Number of\nkitchens");
        residentialKitchenLabel.setTextAlignment(TextAlignment.CENTER);
        residentialBathroomLabel = new Label("Number of\nbathrooms");
        residentialBathroomLabel.setTextAlignment(TextAlignment.CENTER);
        residentialRoomsWPLabel = new Label("Other rooms\nwith plumbing");
        residentialRoomsWPLabel.setTextAlignment(TextAlignment.CENTER);

        projectSpecificLabelPane.getChildren().setAll(residentialSizeLabel, residentialKitchenLabel,
            residentialBathroomLabel, residentialRoomsWPLabel);
        projectSpecificLabelPane.setSpacing(23);

        residentialSizeField = new TextField();
        residentialKitchenField = new TextField();
        residentialKitchenField.setPromptText("1");
        residentialBathroomField = new TextField();
        residentialBathroomField.setPromptText("1");
        residentialRoomsWPField = new TextField();
        residentialRoomsWPField.setPromptText("1");

        residentialRenovation = new ToggleButton("Renovation");

        projectSpecificFieldPane.getChildren().setAll(residentialSizeField, residentialKitchenField,
            residentialBathroomField, residentialRoomsWPField);
        projectSpecificFieldPane.setSpacing(30);

        specificPane.getChildren().add(residentialRenovation);
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
        projectBudgetMin.setPromptText("2000000");
        projectBudgetMax.setPromptText("10000000");
        projectTimeline.setPromptText("30");

        industrialSizeLabel = new Label("Facility size*");
        industrialIntendedUseLabel = new Label("Intended use*");

        projectSpecificLabelPane.getChildren().setAll(industrialSizeLabel, industrialIntendedUseLabel);
        projectSpecificLabelPane.setSpacing(24);

        industrialSizeField = new TextField();
        industrialIntendedUseField = new TextField();

        projectSpecificFieldPane.getChildren().setAll(industrialSizeField, industrialIntendedUseField);
        projectSpecificFieldPane.setSpacing(17);
        break;
      case "Road":
        projectBudgetMin.setPromptText("1000000");
        projectBudgetMax.setPromptText("5000000");
        projectTimeline.setPromptText("18");

        roadLengthLabel = new Label("Length*");
        roadWidthLabel = new Label("Width*");
        roadBortLabel = new Label("Number of\nbridges,\ntunnels");
        roadBortLabel.setTextAlignment(TextAlignment.CENTER);
        roadChallengesLabel = new Label("Geographical,\nenvironmental\nchallenges");
        roadChallengesLabel.setTextAlignment(TextAlignment.CENTER);

        projectSpecificLabelPane.getChildren().setAll(roadLengthLabel, roadWidthLabel, roadBortLabel, roadChallengesLabel);
        projectSpecificLabelPane.setAlignment(Pos.TOP_RIGHT);
        projectSpecificLabelPane.setSpacing(25);

        roadLengthField = new TextField();
        roadWidthField = new TextField();
        roadBortField = new TextField();
        roadBortField.setPromptText("0");
        roadChallengesArea = new TextArea();
        roadChallengesArea.setPromptText("Separate different challenges with a comma (,)");

        projectSpecificFieldPane.getChildren().setAll(roadLengthField, roadWidthField, roadBortField, roadChallengesArea);
        projectSpecificFieldPane.setSpacing(20);
        break;
    }
  }

}
