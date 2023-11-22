package View;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ProjectInfoController {
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Project project;

  @FXML private ComboBox<String> typeCombo;

  @FXML private VBox customerInformationFieldPane;
  @FXML private TextField customerName;
  @FXML private TextField customerPhone;
  @FXML private TextField customerEmail;

  @FXML private VBox projectGeneralFieldPane;
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

  private TextField residentialSizeField;
  private TextField residentialKitchenField;
  private TextField residentialBathroomField;
  private TextField residentialRoomsWPField;

  private HBox residentialTogglePane;
  private ToggleGroup residentialToggleGroup;
  private ToggleButton residentialNewBuild;
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

  public void initAdd(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager) {
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;

    typeCombo.setVisible(true);
    typeCombo.getItems().addAll("Residential", "Commercial", "Industrial", "Road");

    actionButton.setText("Create");
    typeCombo.getSelectionModel().selectFirst();
    updateFieldsAdding();
  }

  public void initView(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager, Project project){
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.project = project;

    typeCombo.setVisible(false);
    actionButton.setText("Edit");
    updateFieldsViewing(project);
  }

  public void handleActions(ActionEvent e) {

    if (e.getSource() == backButton) {
      viewHandler.openView("MainView", null);
    }

    else if(e.getSource() == actionButton && actionButton.getText().equals("Create")){
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
                residentialNewBuild.isSelected()
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
                (projectBudgetMin.getText().isEmpty() ? 1000000:
                    Double.parseDouble(projectBudgetMin.getText())),
                (projectBudgetMax.getText().isEmpty() ? 5000000:
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
        viewHandler.openView("MainView", null);
      }
    }

    else if(e.getSource() == actionButton && actionButton.getText().equals("Edit")){
      actionButton.setText("Update");

      customerInformationFieldPane.setDisable(false);
      projectGeneralFieldPane.setDisable(false);
      projectSpecificFieldPane.setDisable(false);

      if(project instanceof Residential){
        residentialNewBuild.setDisable(false);
        residentialRenovation.setDisable(false);
      }
    }
  }

  public Scene getScene(){
    return window;
  }

  public void updateFieldsAdding(){
    projectSpecificLabelPane.setAlignment(Pos.CENTER_RIGHT);
    specificPane.getChildren().remove(residentialTogglePane);
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

        residentialTogglePane = new HBox(10);
        residentialToggleGroup = new ToggleGroup();
        residentialNewBuild = new ToggleButton("New build");
        residentialRenovation = new ToggleButton("Renovation");

        residentialNewBuild.setToggleGroup(residentialToggleGroup);
        residentialNewBuild.setSelected(true);
        residentialRenovation.setToggleGroup(residentialToggleGroup);

        residentialTogglePane.setAlignment(Pos.BASELINE_CENTER);
        residentialTogglePane.getChildren().setAll(residentialNewBuild, residentialRenovation);

        projectSpecificFieldPane.getChildren().setAll(residentialSizeField, residentialKitchenField,
            residentialBathroomField, residentialRoomsWPField);
        projectSpecificFieldPane.setSpacing(30);

        specificPane.getChildren().add(residentialTogglePane);
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

  public void updateFieldsViewing(Project project){
    projectSpecificLabelPane.setAlignment(Pos.CENTER_RIGHT);
    specificPane.getChildren().remove(residentialRenovation);

    projectTitle.setText(project.getTitle());
    projectAddress.setText(project.getAddress());
    projectBudgetMin.setText(project.getBudgetMin() + "");
    projectBudgetMax.setText(project.getBudgetMax() + "");
    projectTimeline.setText(project.getTimeline() + "");
    customerName.setText(project.getCustomer().getName());
    customerPhone.setText(project.getCustomer().getPhoneNumber());
    customerEmail.setText(project.getCustomer().getEmailAddress());

    if(project instanceof Residential){
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
      residentialBathroomField = new TextField();
      residentialRoomsWPField = new TextField();

      residentialSizeField.setText(((Residential) project).getBuildingSize() + "");
      residentialKitchenField.setText(((Residential) project).getKitchens() + "");
      residentialBathroomField.setText(((Residential) project).getBathrooms() + "");
      residentialRoomsWPField.setText(((Residential) project).getOtherRoomsWithPlumbing() + "");

      residentialTogglePane = new HBox(10);
      residentialToggleGroup = new ToggleGroup();
      residentialNewBuild = new ToggleButton("New build");
      residentialRenovation = new ToggleButton("Renovation");

      residentialNewBuild.setToggleGroup(residentialToggleGroup);
      residentialRenovation.setToggleGroup(residentialToggleGroup);
      if (((Residential) project).isRenovation()) residentialNewBuild.setSelected(true);
      else residentialRenovation.setSelected(true);

      residentialTogglePane.setAlignment(Pos.BASELINE_CENTER);
      residentialTogglePane.getChildren().setAll(residentialNewBuild, residentialRenovation);

      projectSpecificFieldPane.getChildren().setAll(residentialSizeField, residentialKitchenField,
          residentialBathroomField, residentialRoomsWPField);
      projectSpecificFieldPane.setSpacing(30);

      residentialRenovation.setDisable(true);
      residentialNewBuild.setDisable(true);

      specificPane.getChildren().add(residentialTogglePane);
    }

    else if(project instanceof Commercial){
      commercialSizeLabel = new Label("Building size*");
      commercialFloorsLabel = new Label("Number of floors");
      commercialIntendedUseLabel = new Label("Intended use*");

      projectSpecificLabelPane.getChildren().setAll(commercialSizeLabel, commercialFloorsLabel,
          commercialIntendedUseLabel);
      projectSpecificLabelPane.setSpacing(21);

      commercialSizeField = new TextField();
      commercialFloorsField = new TextField();
      commercialIntendedUseField = new TextField();

      commercialSizeField.setText(((Commercial) project).getBuildingSize() + "");
      commercialFloorsField.setText(((Commercial) project).getFloors() + "");
      commercialIntendedUseField.setText(((Commercial) project).getIntendedUse());

      projectSpecificFieldPane.getChildren().setAll(commercialSizeField, commercialFloorsField,
          commercialIntendedUseField);
      projectSpecificFieldPane.setSpacing(15);
    }

    else if(project instanceof Industrial){
      industrialSizeLabel = new Label("Facility size*");
      industrialIntendedUseLabel = new Label("Intended use*");

      projectSpecificLabelPane.getChildren().setAll(industrialSizeLabel, industrialIntendedUseLabel);
      projectSpecificLabelPane.setSpacing(24);

      industrialSizeField = new TextField();
      industrialIntendedUseField = new TextField();

      industrialSizeField.setText(((Industrial) project).getFacilitySize() + "");
      industrialIntendedUseField.setText(((Industrial) project).getFacilityType());

      projectSpecificFieldPane.getChildren().setAll(industrialSizeField, industrialIntendedUseField);
      projectSpecificFieldPane.setSpacing(17);
    }

    else if(project instanceof Road){
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
      roadChallengesArea = new TextArea();

      roadLengthField.setText(((Road) project).getLength() + "");
      roadWidthField.setText(((Road) project).getWidth() + "");
      roadBortField.setText(((Road) project).getBridgesOrTunnels() + "");

      String challenges = "";
      for(String temp : ((Road) project).getChallenges()){
        challenges += temp + ",\n";
      }
      challenges = challenges.substring(0, challenges.length()-2);

      roadChallengesArea.setText(challenges);

      projectSpecificFieldPane.getChildren().setAll(roadLengthField, roadWidthField, roadBortField, roadChallengesArea);
      projectSpecificFieldPane.setSpacing(20);
    }

    customerInformationFieldPane.setDisable(true);
    projectGeneralFieldPane.setDisable(true);
    projectSpecificFieldPane.setDisable(true);
  }
}
