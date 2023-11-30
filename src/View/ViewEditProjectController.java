package View;

import Exceptions.InvalidTitleException;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import parser.ParserException;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewEditProjectController
{
  private Scene window;
  private ViewHandler viewHandler;
  private ProjectModelManager modelManager;
  private Project project;

  @FXML private VBox expensesAndHoursPane;
  @FXML private TextField projectHours;
  @FXML private TextField projectExpenses;

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

  @FXML StackPane specificPane;

  @FXML Button backButton;
  @FXML Button actionButton;

//  RESIDENTIAL

  @FXML private VBox residentialFieldPane;
  @FXML private TextField residentialSize;
  @FXML private TextField residentialKitchen;
  @FXML private TextField residentialBathroom;
  @FXML private TextField residentialRWP;

  private ToggleGroup residentialToggleGroup;
  @FXML private RadioButton residentialNewBuild;
  @FXML private RadioButton residentialRenovation;

// COMMERCIAL

  @FXML private VBox commercialFieldPane;
  @FXML private TextField commercialSize;
  @FXML private TextField commercialFloors;
  @FXML private TextField commercialUse;

//  INDUSTRIAL

  @FXML private VBox industrialFieldPane;
  @FXML private TextField industrialSize;
  @FXML private TextField industrialUse;

//  ROAD

  @FXML private VBox roadFieldPane;
  @FXML private TextField roadLength;
  @FXML private TextField roadWidth;
  @FXML private TextField roadBort;
  @FXML private TextArea roadChallenges;

  public void initialize(ViewHandler viewHandler, Scene window, ProjectModelManager modelManager, Project project){
    this.window = window;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.project = project;

    if(project instanceof Residential){
      specificPane.getChildren().get(0).setVisible(true);
      specificPane.getChildren().get(1).setVisible(false);
      specificPane.getChildren().get(2).setVisible(false);
      specificPane.getChildren().get(3).setVisible(false);
    }

    else if(project instanceof Commercial){
      specificPane.getChildren().get(0).setVisible(false);
      specificPane.getChildren().get(1).setVisible(true);
      specificPane.getChildren().get(2).setVisible(false);
      specificPane.getChildren().get(3).setVisible(false);
    }

    else if(project instanceof Industrial){
      specificPane.getChildren().get(0).setVisible(false);
      specificPane.getChildren().get(1).setVisible(false);
      specificPane.getChildren().get(2).setVisible(true);
      specificPane.getChildren().get(3).setVisible(false);
    }

    else if(project instanceof Road){
      specificPane.getChildren().get(0).setVisible(false);
      specificPane.getChildren().get(1).setVisible(false);
      specificPane.getChildren().get(2).setVisible(false);
      specificPane.getChildren().get(3).setVisible(true);
    }

    updateFieldsViewing(project);
  }

  public void handleActions(ActionEvent e) {

    if (e.getSource() == backButton) {
      viewHandler.openView("main", null);
    }

    else if(e.getSource() == actionButton && actionButton.getText().equals("Edit")){
      actionButton.setText("Update");

      customerInformationFieldPane.setDisable(false);
      projectGeneralFieldPane.setDisable(false);
      enableSpecificPane();
      expensesAndHoursPane.setDisable(false);

      if(project instanceof Residential){
        residentialNewBuild.setDisable(false);
        residentialRenovation.setDisable(false);
      }
    }

    else if(e.getSource() == actionButton && actionButton.getText().equals("Update")){
      String oldTitle = project.getTitle();

      try{
        String title = projectTitle.getText();
        String address = projectAddress.getText();
        double budgetMin = Double.parseDouble(projectBudgetMin.getText());
        double budgetMax = Double.parseDouble(projectBudgetMax.getText());
        int timeline = Integer.parseInt(projectTimeline.getText());
        String custName = customerName.getText();
        String custPhone = customerPhone.getText();
        String custEmail = customerEmail.getText();
        double expenses = Double.parseDouble(projectExpenses.getText());
        int hours = Integer.parseInt(projectHours.getText());

        if(project instanceof Residential){
          modelManager.editProject(oldTitle, title, address, budgetMin, budgetMax, timeline,
              new Customer(custName, custPhone, custEmail), expenses, hours,
              Double.parseDouble(residentialSize.getText()), Integer.parseInt(residentialKitchen.getText()),
              Integer.parseInt(residentialBathroom.getText()), Integer.parseInt(residentialRWP.getText()),
              residentialRenovation.isSelected());

          residentialNewBuild.setDisable(true);
          residentialRenovation.setDisable(true);
        }

        else if(project instanceof Commercial){
          modelManager.editProject(oldTitle, title, address, budgetMin, budgetMax, timeline,
              new Customer(custName, custPhone, custEmail), expenses, hours,
              Double.parseDouble(commercialSize.getText()), Integer.parseInt(commercialFloors.getText()),
              commercialUse.getText());
        }

        else if(project instanceof Industrial){
          modelManager.editProject(oldTitle, title, address, budgetMin, budgetMax, timeline,
              new Customer(custName, custPhone, custEmail), expenses, hours,
              Double.parseDouble(industrialSize.getText()), industrialUse.getText());
        }

        else if(project instanceof Road){
          ArrayList<String> challenges = new ArrayList<>(Arrays.asList(
              roadChallenges.getText().split(", ", 0)));

          modelManager.editProject(oldTitle, title, address, budgetMin, budgetMax, timeline,
              new Customer(custName, custPhone, custEmail), expenses, hours,
              Double.parseDouble(roadLength.getText()), Double.parseDouble(roadWidth.getText()),
              Integer.parseInt(roadBort.getText()), challenges);
        }

        customerInformationFieldPane.setDisable(true);
        projectGeneralFieldPane.setDisable(true);
        disableSpecificPane();
        expensesAndHoursPane.setDisable(true);
        actionButton.setText("Edit");

        Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");
      }
      catch(InvalidTitleException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(exception.getMessage());
        alert.showAndWait();
      }
      catch(java.lang.NumberFormatException exception){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You left one of the necessary fields empty or entered a letter/letters in a numbers-only field.");
        alert.showAndWait();
      }
      catch (ParserException exception){
        exception.printStackTrace();
      }
    }
  }

  public Scene getScene(){
    return window;
  }

  public void updateFieldsViewing(Project project){

    projectExpenses.setText(project.getExpectedExpenses() + "");
    projectHours.setText(project.getExpectedHours() + "");

    projectTitle.setText(project.getTitle());
    projectAddress.setText(project.getAddress());
    projectBudgetMin.setText(project.getBudgetMin() + "");
    projectBudgetMax.setText(project.getBudgetMax() + "");
    projectTimeline.setText(project.getTimeline() + "");
    customerName.setText(project.getCustomer().getName());
    customerPhone.setText(project.getCustomer().getPhoneNumber());
    customerEmail.setText(project.getCustomer().getEmailAddress());

    if(project instanceof Residential){
      residentialSize.setText(((Residential) project).getBuildingSize() + "");
      residentialKitchen.setText(((Residential) project).getKitchens() + "");
      residentialBathroom.setText(((Residential) project).getBathrooms() + "");
      residentialRWP.setText(((Residential) project).getOtherRoomsWithPlumbing() + "");

      if (((Residential) project).isRenovation()) residentialRenovation.setSelected(true);
      else residentialNewBuild.setSelected(true);

      residentialToggleGroup = new ToggleGroup();
      residentialRenovation.setToggleGroup(residentialToggleGroup);
      residentialNewBuild.setToggleGroup(residentialToggleGroup);

      residentialRenovation.setDisable(true);
      residentialNewBuild.setDisable(true);
    }

    else if(project instanceof Commercial){
      commercialSize.setText(((Commercial) project).getBuildingSize() + "");
      commercialFloors.setText(((Commercial) project).getFloors() + "");
      commercialUse.setText(((Commercial) project).getIntendedUse());
    }

    else if(project instanceof Industrial){
      industrialSize.setText(((Industrial) project).getFacilitySize() + "");
      industrialUse.setText(((Industrial) project).getFacilityType());
    }

    else if(project instanceof Road){
      roadLength.setText(((Road) project).getLength() + "");
      roadWidth.setText(((Road) project).getWidth() + "");
      roadBort.setText(((Road) project).getBridgesOrTunnels() + "");

      if(!roadChallenges.getText().isEmpty())
      {
        String challenges = "";
        for (String temp : ((Road) project).getChallenges())
        {
          challenges += temp + ",\n";
        }
        challenges = challenges.substring(0, challenges.length() - 2);

        roadChallenges.setText(challenges);
      }
    }

    customerInformationFieldPane.setDisable(true);
    projectGeneralFieldPane.setDisable(true);
    disableSpecificPane();
    expensesAndHoursPane.setDisable(true);
  }

  public void disableSpecificPane(){
    if(project instanceof Residential){
      residentialFieldPane.setDisable(true);
    }
    else if(project instanceof Commercial){
      commercialFieldPane.setDisable(true);
    }
    else if(project instanceof Industrial){
      industrialFieldPane.setDisable(true);
    }
    else if(project instanceof Road){
      roadFieldPane.setDisable(true);
      roadChallenges.setDisable(true);
    }
  }

  public void enableSpecificPane(){
    if(project instanceof Residential){
      residentialFieldPane.setDisable(false);
    }
    else if(project instanceof Commercial){
      commercialFieldPane.setDisable(false);
    }
    else if(project instanceof Industrial){
      industrialFieldPane.setDisable(false);
    }
    else if(project instanceof Road){
      roadFieldPane.setDisable(false);
      roadChallenges.setDisable(false);
    }
  }
}
