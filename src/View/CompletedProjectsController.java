package View;

import Model.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import javax.swing.text.View;

public class CompletedProjectsController
{
  private ProjectModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button refreshButton;
  @FXML private Button addProjectButton;

  @FXML private TreeTableView<Project> TreeTable;

  private TreeTableColumn<Project, String> titleColumn;
  private TreeTableColumn<Project, String> addressColumn;
  private TreeTableColumn<Project, String> budgetColumn;
  private TreeTableColumn<Project, String> timelineColumn;

  private TreeItem<Project> parentNode;
  private TreeItem<Project> residentialNode;
  private TreeItem<Project> commercialNode;
  private TreeItem<Project> industrialNode;
  private TreeItem<Project> roadNode;



  public void initialize(ViewHandler viewHandler, ProjectModelManager modelManager){
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;

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

    budgetColumn = new TreeTableColumn<>("Budget range");
    budgetColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getBudgetMax() == 0 ? "" :
            p.getValue().getValue().getBudgetMin() + " - " + p.getValue().getValue().getBudgetMax())));
    budgetColumn.setReorderable(false);
    budgetColumn.setSortable(false);

    timelineColumn = new TreeTableColumn<>("Timeline");
    timelineColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper((p.getValue().getValue().getTimeline() == 0 ? "" :
            p.getValue().getValue().getTimeline() + " months")));
    timelineColumn.setReorderable(false);
    timelineColumn.setSortable(false);

    updateProjects();

    TreeTable.getColumns().addAll(titleColumn, addressColumn, budgetColumn, timelineColumn);

    parentNode = new TreeItem<>(new GUINode("Parent"));
    parentNode.getChildren().addAll(residentialNode, commercialNode, industrialNode, roadNode);

    TreeTable.setShowRoot(false);
    TreeTable.setRoot(parentNode);
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == refreshButton) {
      updateProjects();
    }
    else if(e.getSource() == addProjectButton){

    }
  }

  public void updateProjects(){
    ProjectList projects = modelManager.getAllProjects();
    residentialNode.getChildren().setAll();
    commercialNode.getChildren().setAll();
    industrialNode.getChildren().setAll();
    roadNode.getChildren().setAll();

    for (int i = 0; i < projects.getSize(); i++) {
      Project temp = projects.getProject(i);
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
