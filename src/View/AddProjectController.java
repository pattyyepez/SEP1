package View;

import Exceptions.InvalidTitleException;
import Model.*;
import View.Start;
import View.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import parser.ParserException;

/**
 * The controller for the FXML file called "AddProject", it is part of the GUI and, as the name suggests,
 * it is responsible for adding new projects to the system.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class AddProjectController {
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Project project;
  private int page;

  @FXML private StackPane stackPane;
  @FXML private Button actionButton;
  @FXML private Button cancelButton;
  @FXML private Button backButton;

  // general project information

  @FXML private ComboBox<String> projectType;
  @FXML private TextField projectTitle;
  @FXML private TextField projectAddress;
  @FXML private TextField projectBudgetMin;
  @FXML private TextField projectBudgetMax;
  @FXML private TextField projectTimeline;

  // customer information

  @FXML private TextField customerName;
  @FXML private TextField customerPhone;
  @FXML private TextField customerEmail;

  // ~~ specific project information ~~

  @FXML private StackPane specificPane;

  //  RESIDENTIAL

  @FXML private TextField residentialSize;
  @FXML private TextField residentialKitchen;
  @FXML private TextField residentialBathroom;
  @FXML private TextField residentialRWP;

  private ToggleGroup residentialToggleGroup;
  @FXML private RadioButton residentialNewBuild;
  @FXML private RadioButton residentialRenovation;

  // COMMERCIAL

  @FXML private TextField commercialSize;
  @FXML private TextField commercialFloors;
  @FXML private TextField commercialUse;

  //  INDUSTRIAL

  @FXML private TextField industrialSize;
  @FXML private TextField industrialUse;

  //  ROAD

  @FXML private TextField roadLength;
  @FXML private TextField roadWidth;
  @FXML private TextField roadBort;
  @FXML private TextArea roadChallenges;

  /**
   * Initializes the AddProject window by hiding the panes that should be hidden in the beginning.
   * It also gives the ComboBox items, so that the user can choose the appropriate project type.
   *
   * @param viewHandler     Used for loading the MainView window after the user finishes or cancels adding projects.
   * @param window          Used for GUI, tying this window together with the MainView, used for displaying this window by the ViewHandler
   * @param modelManager    Used for accessing the binary file with the main ProjectList containing all projects, for adding new ones ofc
   */
  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager){
    this.viewHandler = viewHandler;
    this.window = window;
    this.modelManager = modelManager;

    stackPane.getChildren().get(1).setVisible(false);
    stackPane.getChildren().get(2).setVisible(false);

    projectType.getItems().addAll("Residential", "Commercial", "Industrial", "Road");
    projectType.getSelectionModel().selectFirst();

    updateFields();

    backButton.setDisable(true);
    actionButton.setText("Next");
    page = 0;
  }

  /**
   * This method makes the buttons on this window functional and is responsible for adding new projects.
   *
   * @param e   Used to tell the different buttons apart from each other.
   */
  public void handleActions(ActionEvent e){

    if(e.getSource() == cancelButton){
      viewHandler.openView("main", null);
    }

    else if(e.getSource() == actionButton){
      switch(page){

        case 0 -> {
          page ++;
          stackPane.getChildren().get(0).setVisible(false);
          stackPane.getChildren().get(1).setVisible(true);
          backButton.setDisable(false);
        }

        case 1 -> {
          page ++;
          stackPane.getChildren().get(1).setVisible(false);
          stackPane.getChildren().get(2).setVisible(true);
          actionButton.setText("Create");
        }

        case 2 -> {
          try{
            project = null;
            switch (projectType.getSelectionModel().getSelectedItem()) {
              case "Residential" ->
                  project = new Residential(projectTitle.getText(), projectAddress.getText(),
                      (projectBudgetMin.getText().isEmpty() ? 100000 : Double.parseDouble(projectBudgetMin.getText())),
                      (projectBudgetMax.getText().isEmpty() ? 500000 : Double.parseDouble(projectBudgetMax.getText())),
                      (projectTimeline.getText().isEmpty() ? 9 : Integer.parseInt(projectTimeline.getText())),
                      new Customer(customerName.getText(), customerPhone.getText(),
                          customerEmail.getText()), Double.parseDouble(residentialSize.getText()),
                      (residentialKitchen.getText().isEmpty() ? 1 : Integer.parseInt(residentialKitchen.getText())),
                      (residentialBathroom.getText().isEmpty() ? 1 : Integer.parseInt(residentialBathroom.getText())),
                      (residentialRWP.getText().isEmpty() ? 1 : Integer.parseInt(residentialRWP.getText())),
                      residentialRenovation.isSelected());
              case "Commercial" ->
                  project = new Commercial(projectTitle.getText(), projectAddress.getText(),
                      (projectBudgetMin.getText().isEmpty() ? 500000 : Double.parseDouble(projectBudgetMin.getText())),
                      (projectBudgetMax.getText().isEmpty() ? 2000000 : Double.parseDouble(projectBudgetMax.getText())),
                      (projectTimeline.getText().isEmpty() ? 18 : Integer.parseInt(projectTimeline.getText())),
                      new Customer(customerName.getText(), customerPhone.getText(),
                          customerEmail.getText()), Double.parseDouble(commercialSize.getText()),
                      (commercialFloors.getText().isEmpty() ? 1 : Integer.parseInt(commercialFloors.getText())),
                      commercialUse.getText());
              case "Industrial" ->
                  project = new Industrial(projectTitle.getText(), projectAddress.getText(),
                      (projectBudgetMin.getText().isEmpty() ? 2000000 : Double.parseDouble(projectBudgetMin.getText())),
                      (projectBudgetMax.getText().isEmpty() ? 10000000 : Double.parseDouble(projectBudgetMax.getText())),
                      (projectTimeline.getText().isEmpty() ? 30 : Integer.parseInt(projectTimeline.getText())),
                      new Customer(customerName.getText(), customerPhone.getText(),
                          customerEmail.getText()), Double.parseDouble(industrialSize.getText()),
                      industrialUse.getText());
              case "Road" -> {
                project = new Road(projectTitle.getText(), projectAddress.getText(),
                    (projectBudgetMin.getText().isEmpty() ? 1000000 :
                        Double.parseDouble(projectBudgetMin.getText())),
                    (projectBudgetMax.getText().isEmpty() ? 5000000 :
                        Double.parseDouble(projectBudgetMax.getText())),
                    (projectTimeline.getText().isEmpty() ? 18 :
                        Integer.parseInt(projectTimeline.getText())),
                    new Customer(customerName.getText(), customerPhone.getText(),
                        customerEmail.getText()),
                    Double.parseDouble(roadLength.getText()),
                    Double.parseDouble(roadWidth.getText()),
                    (roadBort.getText()).isEmpty() ? 0 : Integer.parseInt(roadBort.getText())) ;
                if (!roadChallenges.getText().isEmpty()) {
                  for (String temp : roadChallenges.getText().split(", ", 0)) {
                    ((Road) project).addChallenge(temp);
                  }
                }
              }
            }
            if(project!=null) {
              modelManager.addProject(project);
              viewHandler.openView("main", null);
              Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");
            }
          }
          catch(java.lang.NumberFormatException exception){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");
            alert.showAndWait();
          }
          catch(InvalidTitleException exception){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
          }
          catch(ParserException exception){
            exception.printStackTrace();
          }

        }

      }
    }

    else if(e.getSource() == backButton){
      switch(page){
        case 1 -> {
          page --;
          stackPane.getChildren().get(0).setVisible(true);
          stackPane.getChildren().get(1).setVisible(false);
          backButton.setDisable(true);
        }

        case 2 -> {
          page --;
          stackPane.getChildren().get(1).setVisible(true);
          stackPane.getChildren().get(2).setVisible(false);
          actionButton.setText("Next");
        }
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

  /**
   * Method that updates all the TextFields with appropriate prompt text depending on which project type has been selected.
   */
  public void updateFields(){
    switch (projectType.getSelectionModel().getSelectedItem()){

      case "Residential" -> {
        specificPane.getChildren().get(0).setVisible(true);
        specificPane.getChildren().get(1).setVisible(false);
        specificPane.getChildren().get(2).setVisible(false);
        specificPane.getChildren().get(3).setVisible(false);

        projectBudgetMin.setPromptText("$100,000");
        projectBudgetMax.setPromptText("$500,000");
        projectTimeline.setPromptText("9 months");

        residentialToggleGroup = new ToggleGroup();
        residentialNewBuild.setToggleGroup(residentialToggleGroup);
        residentialRenovation.setToggleGroup(residentialToggleGroup);
      }

      case "Commercial" -> {
        specificPane.getChildren().get(0).setVisible(false);
        specificPane.getChildren().get(1).setVisible(true);
        specificPane.getChildren().get(2).setVisible(false);
        specificPane.getChildren().get(3).setVisible(false);

        projectBudgetMin.setPromptText("$500,000");
        projectBudgetMax.setPromptText("$2,000,000");
        projectTimeline.setPromptText("18 months");
      }

      case "Industrial" -> {
        specificPane.getChildren().get(0).setVisible(false);
        specificPane.getChildren().get(1).setVisible(false);
        specificPane.getChildren().get(2).setVisible(true);
        specificPane.getChildren().get(3).setVisible(false);

        projectBudgetMin.setPromptText("$2,000,000");
        projectBudgetMax.setPromptText("$10,000,000");
        projectTimeline.setPromptText("30 months");
      }

      case "Road" -> {
        specificPane.getChildren().get(0).setVisible(false);
        specificPane.getChildren().get(1).setVisible(false);
        specificPane.getChildren().get(2).setVisible(false);
        specificPane.getChildren().get(3).setVisible(true);

        projectBudgetMin.setPromptText("$1,000,000");
        projectBudgetMax.setPromptText("$5,000,000");
        projectTimeline.setPromptText("18 months");
      }

    }
  }
}
