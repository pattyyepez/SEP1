package View;

import Model.*;
import View.Start;
import View.ViewHandler;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseButton;
import parser.ParserException;

import java.io.File;

/**
 * Controller class responsible for displaying all completed projects saved in the system, as well
 * as providing the user with the functionality to refresh the view, remove a selected project,
 * and view and/or edit a selected project.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class CompletedProjectsController
{
  private ProjectModelManager modelManager;
  private ViewHandler viewHandler;

  private TreeItem<Project> selectedIndex;

  @FXML private Button refreshButton;
  @FXML private Button removeButton;
  @FXML private Button viewButton;

  @FXML private TreeTableView<Project> treeTable;

  private TreeTableColumn<Project, String> titleColumn;
  private TreeTableColumn<Project, String> addressColumn;
  private TreeTableColumn<Project, String> totalHoursColumn;
  private TreeTableColumn<Project, String> totalExpensesColumn;
  private TreeTableColumn<Project, String> endDateColumn;

  private TreeItem<Project> parentNode;
  private TreeItem<Project> residentialNode;
  private TreeItem<Project> commercialNode;
  private TreeItem<Project> industrialNode;
  private TreeItem<Project> roadNode;

  /**
   * Creates the columns in the table that displays the information about completed projects.
   * Also creates the collapsable cells that are used to organize projects based on their type.
   * Loads in all completed projects into the table using a different method.
   *
   * @param viewHandler   Used for loading the view and/or edit window
   * @param modelManager  Used for accessing the binary file with the main ProjectList that contains
   *                      all projects to display them, as well as accessing the information of
   *                      specific projects when the user wants to view more specific information or edit it.
   */
  public void initialize(ViewHandler viewHandler, ProjectModelManager modelManager){
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;

    removeButton.setDisable(true);
    viewButton.setDisable(true);

    residentialNode = new TreeItem<>(new Residential("Residential"));
    residentialNode.setExpanded(true);

    commercialNode = new TreeItem<>(new Commercial("Commercial"));
    commercialNode.setExpanded(true);

    industrialNode = new TreeItem<>(new Industrial("Industrial"));
    industrialNode.setExpanded(true);

    roadNode = new TreeItem<>(new Road("Road"));
    roadNode.setExpanded(true);

    titleColumn = new TreeTableColumn<>("Title");
    titleColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper(p.getValue().getValue().getTitle()));
    titleColumn.setReorderable(false);
    titleColumn.setSortable(false);
    titleColumn.setMinWidth(150);

    addressColumn = new TreeTableColumn<>("Address");
    addressColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper(p.getValue().getValue().getAddress()));
    addressColumn.setReorderable(false);
    addressColumn.setSortable(false);

    totalHoursColumn = new TreeTableColumn<>("Total\nhours");
    totalHoursColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getTotalHours() + "")));
    totalHoursColumn.setMinWidth(40);
    totalHoursColumn.setReorderable(false);
    totalHoursColumn.setSortable(false);

    totalExpensesColumn = new TreeTableColumn<>("Total\nexpenses");
    totalExpensesColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getTotalExpenses() + "")));
    totalExpensesColumn.setPrefWidth(100);
    totalExpensesColumn.setReorderable(false);
    totalExpensesColumn.setSortable(false);

    endDateColumn = new TreeTableColumn<>("End date");
    endDateColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getEndDate().toStringShort())));
    endDateColumn.setPrefWidth(80);
    endDateColumn.setReorderable(false);
    endDateColumn.setSortable(false);

    updateProjects();

    treeTable.getColumns().addAll(titleColumn, addressColumn, totalHoursColumn, totalExpensesColumn, endDateColumn);

    parentNode = new TreeItem<>(new GUINode("Parent"));
    parentNode.getChildren().addAll(residentialNode, commercialNode, industrialNode, roadNode);

    treeTable.setShowRoot(false);
    treeTable.setRoot(parentNode);

    treeTable.setOnMouseClicked(e -> {
      if (e.getButton().equals(MouseButton.PRIMARY)) {
        selectedIndex = treeTable.getSelectionModel().getSelectedItem();
        if(selectedIndex != null && !selectedIndex.equals(roadNode) && !selectedIndex.equals(industrialNode) && !selectedIndex.equals(commercialNode) && !selectedIndex.equals(residentialNode)){
          removeButton.setDisable(false);
          viewButton.setDisable(false);
        }
        else{
          removeButton.setDisable(true);
          viewButton.setDisable(true);
        }
      }
    });
  }

  /**
   * Used to give functionalities to the 3 buttons available to the user, that is the refresh button,
   * the view button (also used for editing) and the remove button.
   *
   * @param e   Used to tell the different buttons apart from each other.
   */
  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == refreshButton) {
      updateProjects();
    }

    else if(e.getSource() == viewButton){
      viewHandler.openView("view", selectedIndex.getValue());
    }

    else if(e.getSource() == removeButton){
      modelManager.removeProject(selectedIndex.getValue().getTitle());
      updateProjects();
      try{
        Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");
      }
      catch (ParserException exception){
        exception.printStackTrace();
      }
    }
  }

  /**
   * Loads in information into the TreeTableView about all completed projects and organizes
   * them based on the project type.
   */
  public void updateProjects(){
    removeButton.setDisable(true);
    viewButton.setDisable(true);

    ProjectList projects = modelManager.getAllProjects();
    residentialNode.getChildren().setAll();
    commercialNode.getChildren().setAll();
    industrialNode.getChildren().setAll();
    roadNode.getChildren().setAll();

    for (int i = 0; i < projects.getSize(); i++) {
      Project temp = projects.getProject(i);
      modelManager.calculateExpected(temp);
      if(temp.isCompleted()){
        if(temp instanceof Residential) {
          TreeItem<Project> tempNode = new TreeItem<>(temp);
          residentialNode.getChildren().add(tempNode);
        }
        if(temp instanceof Commercial) {
          TreeItem<Project> tempNode = new TreeItem<>(temp);
          commercialNode.getChildren().add(tempNode);
        }
        if(temp instanceof Industrial) {
          TreeItem<Project> tempNode = new TreeItem<>(temp);
          industrialNode.getChildren().add(tempNode);
        }
        if(temp instanceof Road) {
          TreeItem<Project> tempNode = new TreeItem<>(temp);
          roadNode.getChildren().add(tempNode);
        }
      }
    }
  }
}
