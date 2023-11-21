package View;

import Model.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

public class OnGoingProjectsController
{
  private ProjectModelManager modelManager;
  private ViewHandler viewHandler;
  private Scene window;

  private TreeItem<Project> selectedIndex;

  @FXML private Button completeButton;
  @FXML private Button addProjectButton;
  @FXML private Button removeButton;
  @FXML private Button refreshButton;

  @FXML private TreeTableView<Project> treeTable;

  private TreeTableColumn<Project, String> titleColumn;
  private TreeTableColumn<Project, String> addressColumn;
  private TreeTableColumn<Project, String> budgetColumn;
  private TreeTableColumn<Project, String> timelineColumn;
  private TreeTableColumn<Project, String> otherColumn;

  private TreeItem<Project> parentNode;
  private TreeItem<Project> residentialNode;
  private TreeItem<Project> commercialNode;
  private TreeItem<Project> industrialNode;
  private TreeItem<Project> roadNode;

  public void initialize(ViewHandler viewHandler, ProjectModelManager modelManager, Scene window){
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.window = window;

    removeButton.setDisable(true);
    completeButton.setDisable(true);

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

    otherColumn = new TreeTableColumn<>("Other");
    otherColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> p) ->
        new ReadOnlyStringWrapper(
            (p.getValue().getValue().getBudgetMax() == 0 ? ""
                : ((p.getValue().getValue() instanceof Residential) ? (((Residential) p.getValue().getValue()).isRenovation() ? "New build" : "Renovation")
                : ((p.getValue().getValue() instanceof Commercial) ? ((Commercial) p.getValue().getValue()).getIntendedUse()
                : ((p.getValue().getValue() instanceof Industrial) ? ((Industrial) p.getValue().getValue()).getFacilityType()
                : (((Road) p.getValue().getValue()).getLength() + "km, " + ((Road) p.getValue().getValue()).getWidth() + " lanes"
            )))))
        ));
    otherColumn.setReorderable(false);
    otherColumn.setSortable(false);

    updateProjects();

    treeTable.getColumns().addAll(titleColumn, addressColumn, budgetColumn, timelineColumn, otherColumn);

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
        }
        else{
          removeButton.setDisable(true);
          completeButton.setDisable(true);
        }
      }
    });

  }

  public void handleActions(ActionEvent e) {

    if(e.getSource() == refreshButton){
      updateProjects();
    }

    else if(e.getSource() == addProjectButton){
      viewHandler.openView("AddProject");
    }

    else if (e.getSource() == completeButton) {
      double totalExpenses;
      int totalHours;

      TextInputDialog hours = new TextInputDialog();
      hours.setHeaderText("Enter the amount of man hours used on this project");
      hours.showAndWait();

      if(!hours.getEditor().getText().isEmpty()){
        totalHours = Integer.parseInt(hours.getEditor().getText());
      }
      else return;

      TextInputDialog expenses = new TextInputDialog();
      expenses.setHeaderText("Enter the expenses used on this project");
      expenses.showAndWait();

      if(!expenses.getEditor().getText().isEmpty()){
        totalExpenses = Double.parseDouble(expenses.getEditor().getText());
      }
      else return;

      modelManager.completeProject(selectedIndex.getValue().getTitle(), totalExpenses, totalHours);
      updateProjects();
    }

    else if(e.getSource() == removeButton){
      modelManager.removeProject(selectedIndex.getValue().getTitle());
      updateProjects();
    }

  }

  public void updateProjects(){
    removeButton.setDisable(true);
    completeButton.setDisable(true);

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