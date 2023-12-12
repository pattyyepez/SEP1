package Model;

import Exceptions.InvalidTitleException;
import Utils.MyFileHandler;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class responsible for managing the ProjectList and projects within.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class ProjectModelManager {
  final private String fileName;

  /**
   * A 1-argument constructor that creates a new ProjectModelManager with a given fileName.
   *
   * @param fileName  the fileName, could contain the path as well, of the binary file from which data will be read.
   */
  public ProjectModelManager(String fileName){
    this.fileName = fileName;
  }

  /**
   * Returns the ProjectList containing all projects.
   *
   * @return  The ProjectList containing all projects.
   */
  public ProjectList getAllProjects(){
    ProjectList allProjects = new ProjectList();

    try{
      allProjects = (ProjectList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "File not found.");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "IO Error reading file");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    catch (ClassNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Class Not Found");
      alert.setHeaderText(null);
      alert.showAndWait();
    }

    return allProjects;
  }

  /**
   * Returns a ProjectList only containing projects of a given type.
   *
   * @param type  Type of projects that will be returned.
   * @return      ProjectList containing projects only of a given type.
   */
  public ProjectList getProjectsOfType(String type){
    ProjectList allProjects = getAllProjects();
    ProjectList projectsType = new ProjectList();

    for(int i = 0; i < allProjects.getSize(); i++) {
      Project temp = allProjects.getProject(i);
      if(temp instanceof Residential && type.equalsIgnoreCase("residential")){
        projectsType.addProject(temp);
      }
      else if(temp instanceof Commercial && type.equalsIgnoreCase("commercial")){
        projectsType.addProject(temp);
      }
      else if(temp instanceof Industrial && type.equalsIgnoreCase("industrial")){
        projectsType.addProject(temp);
      }
      else if(temp instanceof Road && type.equalsIgnoreCase("road")) {
        projectsType.addProject(temp);
      }
    }

    return projectsType;
  }

  /**
   * Saves a given ProjectList to a binary file.
   *
   * @param projects  ProjectList to be saved to a binary file.
   */
  public void saveProjects(ProjectList projects){
    try{
      MyFileHandler.writeToBinaryFile(fileName, projects);
    }
    catch (FileNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "File not found.");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "IO Error writing to file");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
  }

  /**
   * Adds a project to a ProjectList and saves this change to the original binary file.
   *
   * @param project                   Project to be added to the ProjectList and the binary file.
   * @throws InvalidTitleException    When the title of the new project matches the title of an already existing
   *                                  project.
   */
  public void addProject(Project project){
    ProjectList allProjects = getAllProjects();
    if(!allProjects.containsTitle(project.getTitle())){
      allProjects.addProject(project);
      calculateExpected(project);
      saveProjects(allProjects);
    }
    else throw new InvalidTitleException(project.getTitle());
  }

  /**
   * Edits the information of a Residential project.
   *
   * @param tOld      Used to find the right project in case its title is also going to be changed.
   * @param tNew      New title.
   * @param a         New address.
   * @param bMin      New budget minimum.
   * @param bMax      New budget maximum.
   * @param tl        New timeline value.
   * @param cust      New customer.
   * @param e         New total expenses value.
   * @param h         New total hours value.
   * @param buildS    New building size value.
   * @param k         New number of kitchens.
   * @param bath      New number of bathrooms.
   * @param roomsWp   New number of other rooms with plumbing.
   * @param r         Changing whether the project is a (true) renovation or a (false) new build.
   *
   * @throws InvalidTitleException    When the new title matches the title of an already existing project.
   */
  public void editProject(String tOld, String tNew, String a, double bMin, double bMax, int tl, Customer cust, double e, int h, double buildS, int k, int bath, int roomsWp, boolean r){
    ProjectList allProjects = getAllProjects();
    Residential project = (Residential) allProjects.getProject(tOld);
    if(!tOld.equals(tNew) && allProjects.containsTitle(tNew)) throw new InvalidTitleException(project.getTitle());
    else{
      allProjects.editTitle(allProjects.getProjectIndex(tOld), tNew);
      project.setTitle(tNew);
      project.setAddress(a);
      project.setBudgetMin(bMin);
      project.setBudgetMax(bMax);
      project.setTimeline(tl);
      project.setCustomer(cust);
      project.setTotalHours(h);
      project.setTotalExpenses(e);
      project.setBuildingSize(buildS);
      project.setKitchens(k);
      project.setBathrooms(bath);
      project.setOtherRoomsWithPlumbing(roomsWp);
      project.setRenovation(r);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

  /**
   * Edits the information of a Commercial project.
   *
   * @param tOld      Used to find the right project in case its title is also going to be changed.
   * @param tNew      New title.
   * @param a         New address.
   * @param bMin      New budget minimum.
   * @param bMax      New budget maximum.
   * @param tl        New timeline value.
   * @param cust      New customer.
   * @param e         New total expenses value.
   * @param h         New total hours value.
   * @param buildS    New building size value.
   * @param f         New number of floors.
   * @param iu        New intended use for the Commercial building.
   *
   * @throws InvalidTitleException    When the new title matches the title of an already existing project.
   */
  public void editProject(String tOld, String tNew, String a, double bMin, double bMax, int tl, Customer cust, double e, int h, double buildS, int f, String iu){
    ProjectList allProjects = getAllProjects();
    Commercial project = (Commercial) allProjects.getProject(tOld);
    if(!tOld.equals(tNew) && allProjects.containsTitle(tNew)) throw new InvalidTitleException(project.getTitle());
    else{
      allProjects.editTitle(allProjects.getProjectIndex(tOld), tNew);
      project.setTitle(tNew);
      project.setAddress(a);
      project.setBudgetMin(bMin);
      project.setBudgetMax(bMax);
      project.setTimeline(tl);
      project.setCustomer(cust);
      project.setTotalHours(h);
      project.setTotalExpenses(e);
      project.setBuildingSize(buildS);
      project.setFloors(f);
      project.setIntendedUse(iu);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

  /**
   * Edits the information of an Industrial facility.
   *
   * @param tOld      Used to find the right project in case its title is also going to be changed.
   * @param tNew      New title.
   * @param a         New address.
   * @param bMin      New budget minimum.
   * @param bMax      New budget maximum.
   * @param tl        New timeline value.
   * @param cust      New customer.
   * @param e         New total expenses value.
   * @param h         New total hours value.
   * @param fSize     New facility size value.
   * @param fType     New intended use of the facility.
   *
   * @throws InvalidTitleException    When the new title matches the title of an already existing project.
   */
  public void editProject(String tOld, String tNew, String a, double bMin, double bMax, int tl, Customer cust, double e, int h, double fSize, String fType){
    ProjectList allProjects = getAllProjects();
    Industrial project = (Industrial) allProjects.getProject(tOld);
    if(!tOld.equals(tNew) && allProjects.containsTitle(tNew)) throw new InvalidTitleException(project.getTitle());
    else{
      allProjects.editTitle(allProjects.getProjectIndex(tOld), tNew);
      project.setTitle(tNew);
      project.setAddress(a);
      project.setBudgetMin(bMin);
      project.setBudgetMax(bMax);
      project.setTimeline(tl);
      project.setCustomer(cust);
      project.setTotalHours(h);
      project.setTotalExpenses(e);
      project.setFacilitySize(fSize);
      project.setFacilityType(fType);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

  /**
   * Edits the information of a given Road project.
   *
   * @param tOld        Used to find the right project in case its title is also going to be changed.
   * @param tNew        New title.
   * @param a           New address.
   * @param bMin        New budget minimum.
   * @param bMax        New budget maximum.
   * @param tl          New timeline value.
   * @param cust        New customer.
   * @param e           New total expenses value.
   * @param h           New total hours value.
   * @param l           New road length value.
   * @param w           New road width value.
   * @param bort        New number of bridges and/or tunnels
   * @param challenges  New ArrayList of any environmental and/or geographical challenges.
   *
   * @throws InvalidTitleException    When the new title matches the title of an already existing project.
   */
  public void editProject(String tOld, String tNew, String a, double bMin, double bMax, int tl, Customer cust, double e, int h, double l, double w, int bort, ArrayList<String> challenges){
    ProjectList allProjects = getAllProjects();
    Road project = (Road) allProjects.getProject(tOld);
    if(!tOld.equals(tNew) && allProjects.containsTitle(tNew)) throw new InvalidTitleException(project.getTitle());
    else{
      allProjects.editTitle(allProjects.getProjectIndex(tOld), tNew);
      project.setTitle(tNew);
      project.setAddress(a);
      project.setBudgetMin(bMin);
      project.setBudgetMax(bMax);
      project.setTimeline(tl);
      project.setCustomer(cust);
      project.setTotalHours(h);
      project.setTotalExpenses(e);
      project.setLength(l);
      project.setWidth(w);
      project.setBridgesOrTunnels(bort);
      project.setChallenges(challenges);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

  /**
   * Removes the given project from the ProjectList and binary file.
   *
   * @param title   The title of the project that is going to be removed.
   */
  public void removeProject(String title){
    ProjectList allProjects = getAllProjects();
    allProjects.removeProject(title);
    saveProjects(allProjects);
  }

  /**
   * Marks a project as completed and changes its status accordingly. The end date is also set as the current date.
   *
   * @param title   The title of the project that is going to be completed.
   */
  public void completeProject(String title){
    ProjectList allProjects = getAllProjects();
    Project temp = allProjects.getProject(title);
    temp.setCompleted(true);
    temp.endToday();
    saveProjects(allProjects);
  }

  /**
   * Calculates the expected expenses and expected hours of a given project.
   *
   * @param project   Project that is going to have its expected expenses and expected hours calculated.
   */
  public void calculateExpected(Project project) {
    ProjectList allProjects;
    double count = 0;
    double expensesPerMSum = 0;
    double expectedExpenses = 0;
    double hoursPerMSum = 0;
    double expectedHours = 0;

    if(project instanceof Residential){
      allProjects = getProjectsOfType("residential");
      for (int x = 0; x < allProjects.getSize(); x++) {
        Residential temp = (Residential) allProjects.getProject(x);
        if (temp.isCompleted()) {
          expensesPerMSum += temp.getTotalExpenses() / temp.getBuildingSize();
          hoursPerMSum += temp.getTotalHours() / temp.getBuildingSize();
          count++;
        }
      }

      expectedExpenses = (expensesPerMSum/count) * ((Residential) project).getBuildingSize() +
          100 * (
              ((Residential) project).getKitchens() +
                  ((Residential) project).getBathrooms() +
                  ((Residential) project).getOtherRoomsWithPlumbing()
          );
      expectedHours = (hoursPerMSum/count) * ((Residential) project).getBuildingSize() +
          10 * (
              ((Residential) project).getKitchens() +
                  ((Residential) project).getBathrooms() +
                  ((Residential) project).getOtherRoomsWithPlumbing()
          );

      if (((Residential) project).isRenovation()) {
        expectedExpenses += 100;
        expectedHours += 10;
      }
    }

    else if(project instanceof Commercial){
      allProjects = getProjectsOfType("commercial");
      for (int x = 0; x < allProjects.getSize(); x++) {
        Commercial temp = (Commercial) allProjects.getProject(x);
        if (temp.isCompleted()) {
          expensesPerMSum += temp.getTotalExpenses() / temp.getBuildingSize();
          hoursPerMSum += temp.getTotalHours() / temp.getBuildingSize();
          count++;
        }
      }

      expectedExpenses = (expensesPerMSum/count) * ((Commercial) project).getBuildingSize()
          + 100 * ((Commercial) project).getFloors();
      expectedHours = (hoursPerMSum/count) * ((Commercial) project).getBuildingSize()
          + 10 * ((Commercial) project).getFloors();
    }

    else if(project instanceof Industrial){
      allProjects = getProjectsOfType("industrial");
      for (int x = 0; x < allProjects.getSize(); x++) {
        Industrial temp = (Industrial) allProjects.getProject(x);
        if (temp.isCompleted()) {
          expensesPerMSum += temp.getTotalExpenses() / temp.getFacilitySize();
          hoursPerMSum += temp.getTotalHours() / temp.getFacilitySize();
          count++;
        }
      }

      expectedExpenses = (expensesPerMSum/count) * ((Industrial) project).getFacilitySize();
      expectedHours = (hoursPerMSum/count) * ((Industrial) project).getFacilitySize();
    }

    else if(project instanceof Road){
      allProjects = getProjectsOfType("road");
      for (int x = 0; x < allProjects.getSize(); x++) {
        Road temp = (Road) allProjects.getProject(x);
        if (temp.isCompleted()) {
          expensesPerMSum += temp.getTotalExpenses() / (temp.getLength() * temp.getWidth());
          hoursPerMSum += temp.getTotalHours() / (temp.getLength() * temp.getWidth());
          count++;
        }
      }

      expectedExpenses = (expensesPerMSum/count) * (((Road) project).getLength() * ((Road) project).getWidth()) +
          100 * ((Road) project).getBridgesOrTunnels() + 100 * (((Road) project).getChallenges().size());

      expectedHours = (hoursPerMSum/count) * (((Road) project).getLength() * ((Road) project).getWidth()) +
          10 * ((Road) project).getBridgesOrTunnels() + 10 * (((Road) project).getChallenges().size());

    }

    project.setExpectedExpenses(Math.round(expectedExpenses));
    project.setExpectedHours((int) Math.round(expectedHours));
  }
}
