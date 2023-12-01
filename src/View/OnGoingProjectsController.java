package View;

import Model.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import parser.ParserException;

import java.io.File;
import java.util.Arrays;

public class OnGoingProjectsController
{
  private ProjectModelManager modelManager;
  private ViewHandler viewHandler;

  private TreeItem<Project> selectedIndex;

  @FXML private Button completeButton;
  @FXML private Button addButton;
  @FXML private Button updateButton;
  @FXML private Button removeButton;
  @FXML private Button refreshButton;
  @FXML private Button viewButton;

  @FXML private TreeTableView<Project> treeTable;

  private TreeTableColumn<Project, String> titleColumn;
  private TreeTableColumn<Project, String> addressColumn;
  private TreeTableColumn<Project, String> expectedHoursColumn;
  private TreeTableColumn<Project, String> expectedExpensesColumn;
  private TreeTableColumn<Project, String> totalHoursColumn;
  private TreeTableColumn<Project, String> totalExpensesColumn;
  private TreeTableColumn<Project, String> otherColumn;

  private TreeItem<Project> parentNode;
  private TreeItem<Project> residentialNode;
  private TreeItem<Project> commercialNode;
  private TreeItem<Project> industrialNode;
  private TreeItem<Project> roadNode;

  public void initialize(ViewHandler viewHandler, ProjectModelManager modelManager){
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;

    removeButton.setDisable(true);
    completeButton.setDisable(true);
    viewButton.setDisable(true);
    updateButton.setDisable(true);

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

    addressColumn = new TreeTableColumn<>("Address");
    addressColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper(p.getValue().getValue().getAddress()));
    addressColumn.setReorderable(false);
    addressColumn.setSortable(false);

    expectedHoursColumn = new TreeTableColumn<>("Expected hours");
    expectedHoursColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getExpectedHours() + "")));
    expectedHoursColumn.setReorderable(false);
    expectedHoursColumn.setSortable(false);

    expectedExpensesColumn = new TreeTableColumn<>("Expected expenses");
    expectedExpensesColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getExpectedExpenses() + "")));
    expectedExpensesColumn.setReorderable(false);
    expectedExpensesColumn.setSortable(false);

    totalHoursColumn = new TreeTableColumn<>("Total hours");
    totalHoursColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getTotalHours() + "")));
    totalHoursColumn.setReorderable(false);
    totalHoursColumn.setSortable(false);

    totalExpensesColumn = new TreeTableColumn<>("Total expenses");
    totalExpensesColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getTotalExpenses() + "")));
    totalExpensesColumn.setReorderable(false);
    totalExpensesColumn.setSortable(false);

    otherColumn = new TreeTableColumn<>("Other");
    otherColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper(
            (p.getValue().getValue().getBudgetMax() == 0 ? ""
                : ((p.getValue().getValue() instanceof Residential) ? (((Residential) p.getValue().getValue()).isRenovation() ? "Renovation" : "New build")
                : ((p.getValue().getValue() instanceof Commercial) ? ((Commercial) p.getValue().getValue()).getIntendedUse()
                : ((p.getValue().getValue() instanceof Industrial) ? ((Industrial) p.getValue().getValue()).getFacilityType()
                : (((Road) p.getValue().getValue()).getLength() + " km, " + ((Road) p.getValue().getValue()).getWidth() + " m"
            )))))
        ));
    otherColumn.setReorderable(false);
    otherColumn.setSortable(false);

    updateProjects();

    treeTable.getColumns().addAll(titleColumn, addressColumn, expectedHoursColumn, expectedExpensesColumn, totalHoursColumn, totalExpensesColumn, otherColumn);

    parentNode = new TreeItem<>(new GUINode("Parent"));
    parentNode.getChildren().addAll(residentialNode, commercialNode, industrialNode, roadNode);

    treeTable.setShowRoot(false);
    treeTable.setRoot(parentNode);

    treeTable.setOnMouseClicked(e -> {
      if (e.getButton().equals(MouseButton.PRIMARY)) {
        selectedIndex = treeTable.getSelectionModel().getSelectedItem();
        if(selectedIndex != null && !selectedIndex.equals(roadNode) && !selectedIndex.equals(industrialNode) && !selectedIndex.equals(commercialNode) && !selectedIndex.equals(residentialNode)){
          removeButton.setDisable(false);
          completeButton.setDisable(false);
          viewButton.setDisable(false);
          updateButton.setDisable(false);
        }
        else{
          removeButton.setDisable(true);
          completeButton.setDisable(true);
          viewButton.setDisable(true);
          updateButton.setDisable(true);
        }
      }
    });

  }

  public void handleActions(ActionEvent e) {

    if(e.getSource() == refreshButton){
      updateProjects();
    }

    else if(e.getSource() == addButton){
      viewHandler.openView("add", null);
    }

    else if(e.getSource() == viewButton){
      viewHandler.openView("view", selectedIndex.getValue());
    }

    else if(e.getSource() == updateButton){
      viewHandler.openView("update", selectedIndex.getValue());
      updateProjects();
      try{
        Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");
      }
      catch (ParserException exception){
        exception.printStackTrace();
      }
    }


    else if (e.getSource() == completeButton) {
      modelManager.completeProject(selectedIndex.getValue().getTitle());
      updateProjects();
      try{
        Start.file = Start.parser.toXml(modelManager.getAllProjects(), "projects.xml");
      }
      catch (ParserException exception){
        exception.printStackTrace();
      }
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

  public void updateProjects(){
    removeButton.setDisable(true);
    completeButton.setDisable(true);
    viewButton.setDisable(true);
    updateButton.setDisable(true);

    ProjectList projects = modelManager.getAllProjects();
    residentialNode.getChildren().setAll();
    commercialNode.getChildren().setAll();
    industrialNode.getChildren().setAll();
    roadNode.getChildren().setAll();

    for (int i = 0; i < projects.getSize(); i++) {
      Project temp = projects.getProject(i);
      if(!temp.isCompleted()){
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