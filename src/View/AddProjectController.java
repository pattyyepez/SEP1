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

    if(e.getSource() == cancelButton){                                          // T = 2 = O(1)
      viewHandler.openView("main", null);                            // T = n = O(n)
    }

    else if(e.getSource() == actionButton){                                     // T = 2 = O(1)
      switch(page){                                                             // T = 1 = O(1)

        case 0 -> {
          page ++;                                                              // T = 1 = O(1)
          stackPane.getChildren().get(0).setVisible(false);                     // T = 3 = O(1)
          stackPane.getChildren().get(1).setVisible(true);                      // T = 3 = O(1)
          backButton.setDisable(false);                                         // T = 1 = O(1)
        }

        case 1 -> {
          page ++;                                                              // T = 1 = O(1)
          stackPane.getChildren().get(1).setVisible(false);                     // T = 3 = O(1)
          stackPane.getChildren().get(2).setVisible(true);                      // T = 3 = O(1)
          actionButton.setText("Create");                                       // T = 1 = O(1)
        }

        case 2 -> {
          try{                                                                  // T = 1 = O(1)
            project = null;                                                     // T = 1 = O(1)
            switch (projectType.getSelectionModel().getSelectedItem()) {        // T = 3 = O(1)
              case "Residential" ->
                  project = new Residential(projectTitle.getText(), projectAddress.getText(),                               // T = 4 = O(1)
                      (projectBudgetMin.getText().isEmpty() ? 100000 : Double.parseDouble(projectBudgetMin.getText())),     // T = 5 = O(1)
                      (projectBudgetMax.getText().isEmpty() ? 500000 : Double.parseDouble(projectBudgetMax.getText())),     // T = 5 = O(1)
                      (projectTimeline.getText().isEmpty() ? 9 : Integer.parseInt(projectTimeline.getText())),              // T = 5 = O(1)
                      new Customer(customerName.getText(), customerPhone.getText(),                                         // T = 3 = O(1)
                          customerEmail.getText()), Double.parseDouble(residentialSize.getText()),                          // T = 3 = O(1)
                      (residentialKitchen.getText().isEmpty() ? 1 : Integer.parseInt(residentialKitchen.getText())),        // T = 5 = O(1)
                      (residentialBathroom.getText().isEmpty() ? 1 : Integer.parseInt(residentialBathroom.getText())),      // T = 5 = O(1)
                      (residentialRWP.getText().isEmpty() ? 1 : Integer.parseInt(residentialRWP.getText())),                // T = 5 = O(1)
                      residentialRenovation.isSelected());                                                                  // T = 1 = O(1)
              case "Commercial" ->
                  project = new Commercial(projectTitle.getText(), projectAddress.getText(),                                // T = 4 = O(1)
                      (projectBudgetMin.getText().isEmpty() ? 500000 : Double.parseDouble(projectBudgetMin.getText())),     // T = 5 = O(1)
                      (projectBudgetMax.getText().isEmpty() ? 2000000 : Double.parseDouble(projectBudgetMax.getText())),    // T = 5 = O(1)
                      (projectTimeline.getText().isEmpty() ? 18 : Integer.parseInt(projectTimeline.getText())),             // T = 5 = O(1)
                      new Customer(customerName.getText(), customerPhone.getText(),                                         // T = 3 = O(1)
                          customerEmail.getText()), Double.parseDouble(commercialSize.getText()),                           // T = 3 = O(1)
                      (commercialFloors.getText().isEmpty() ? 1 : Integer.parseInt(commercialFloors.getText())),            // T = 5 = O(1)
                      commercialUse.getText());                                                                             // T = 1 = O(1)
              case "Industrial" ->
                  project = new Industrial(projectTitle.getText(), projectAddress.getText(),                                // T = 4 = O(1)
                      (projectBudgetMin.getText().isEmpty() ? 2000000 : Double.parseDouble(projectBudgetMin.getText())),    // T = 5 = O(1)
                      (projectBudgetMax.getText().isEmpty() ? 10000000 : Double.parseDouble(projectBudgetMax.getText())),   // T = 5 = O(1)
                      (projectTimeline.getText().isEmpty() ? 30 : Integer.parseInt(projectTimeline.getText())),             // T = 5 = O(1)
                      new Customer(customerName.getText(), customerPhone.getText(),                                         // T = 3 = O(1)
                          customerEmail.getText()), Double.parseDouble(industrialSize.getText()),                           // T = 3 = O(1)
                      industrialUse.getText());                                                                             // T = 1 = O(1)
              case "Road" -> {
                project = new Road(projectTitle.getText(), projectAddress.getText(),                                        // T = 4 = O(1)
                    (projectBudgetMin.getText().isEmpty() ? 1000000 : Double.parseDouble(projectBudgetMin.getText())),      // T = 5 = O(1)
                    (projectBudgetMax.getText().isEmpty() ? 5000000 : Double.parseDouble(projectBudgetMax.getText())),      // T = 5 = O(1)
                    (projectTimeline.getText().isEmpty() ? 18 : Integer.parseInt(projectTimeline.getText())),               // T = 5 = O(1)
                    new Customer(customerName.getText(), customerPhone.getText(),                                           // T = 3 = O(1)
                        customerEmail.getText()),                                                                           // T = 1 = O(1)
                    Double.parseDouble(roadLength.getText()),                                                               // T = 2 = O(1)
                    Double.parseDouble(roadWidth.getText()),                                                                // T = 2 = O(1)
                    (roadBort.getText().isEmpty() ? 0 : Integer.parseInt(roadBort.getText())));                             // T = 5 = O(1)
                if (!roadChallenges.getText().isEmpty()) {                                                                  // T = 3 = O(1)
                  for (String temp : roadChallenges.getText().split(", ", 0)) {                                 // T = 3 + n = O(n) as .split() depends on input size
                    ((Road) project).addChallenge(temp);                                                                    // T = 2 = O(1)
                  }
                }
              }
            }
            if(project!=null) {                                                                                 // T = 1 = O(1)
              modelManager.addProject(project);                                                                 // T = 1 = O(1)
              viewHandler.openView("main", null);                                                    // T = n = O(n)
              Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");           // T = 5 = O(1)
            }
          }
          catch(java.lang.NumberFormatException exception){                                                                             // T = 1 = O(1)
            Alert alert = new Alert(Alert.AlertType.WARNING);                                                                           // T = 3 = O(1)
            alert.setHeaderText(null);                                                                                                  // T = 1 = O(1)
            alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");    // T = 1 = O(1)
            alert.showAndWait();                                                                                                        // T = 1 = O(1)
          }
          catch(InvalidTitleException exception){                               // T = 1 = O(1)
            Alert alert = new Alert(Alert.AlertType.WARNING);                   // T = 3 = O(1)
            alert.setHeaderText(null);                                          // T = 1 = O(1)
            alert.setContentText(exception.getMessage());                       // T = 2 = O(1)
            alert.showAndWait();                                                // T = 1 = O(1)
          }
          catch(ParserException exception){                                     // T = 1 = O(1)
            exception.printStackTrace();                                        // T = 1 = O(1)
          }

        }

      }
    }

    else if(e.getSource() == backButton){                                       // T = 2 = O(1)
      switch(page){                                                             // T = 1 = O(1)
        case 1 -> {
          page --;                                                              // T = 1 = O(1)
          stackPane.getChildren().get(0).setVisible(true);                      // T = 3 = O(1)
          stackPane.getChildren().get(1).setVisible(false);                     // T = 3 = O(1)
          backButton.setDisable(true);                                          // T = 1 = O(1)
        }

        case 2 -> {
          page --;                                                              // T = 1 = O(1)
          stackPane.getChildren().get(1).setVisible(true);                      // T = 3 = O(1)
          stackPane.getChildren().get(2).setVisible(false);                     // T = 3 = O(1)
          actionButton.setText("Next");                                         // T = 1 = O(1)
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
