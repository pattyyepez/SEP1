package Model;

import Exceptions.InvalidTitleException;
import Utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectModelManager {
  final private String fileName;

  public ProjectModelManager(String fileName){
    this.fileName = fileName;
  }

  public ProjectList getAllProjects(){
    ProjectList allProjects = new ProjectList();

    try{
      allProjects = (ProjectList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    catch (IOException e) {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
    }

    return allProjects;
  }

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

  public void saveProjects(ProjectList projects){
    try{
      MyFileHandler.writeToBinaryFile(fileName, projects);
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    catch (IOException e) {
      System.out.println("IO Error writing to file");
    }
  }

  public void addProject(Project project){
    ProjectList allProjects = getAllProjects();
    if(!allProjects.containsTitle(project.getTitle())){
      allProjects.addProject(project);
      calculateExpected(project);
      saveProjects(allProjects);
    }
    else throw new InvalidTitleException(project.getTitle());
  }

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
      project.setExpectedExpenses(e);
      project.setBuildingSize(buildS);
      project.setKitchens(k);
      project.setBathrooms(bath);
      project.setOtherRoomsWithPlumbing(roomsWp);
      project.setRenovation(r);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

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
      project.setExpectedExpenses(e);
      project.setBuildingSize(buildS);
      project.setFloors(f);
      project.setIntendedUse(iu);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

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
      project.setExpectedExpenses(e);
      project.setFacilitySize(fSize);
      project.setFacilityType(fType);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

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
      project.setExpectedExpenses(e);
      project.setLength(l);
      project.setWidth(w);
      project.setBridgesOrTunnels(bort);
      project.setChallenges(challenges);
      calculateExpected(project);
      saveProjects(allProjects);
    }
  }

  public void removeProject(String title){
    ProjectList allProjects = getAllProjects();
    allProjects.removeProject(title);
    saveProjects(allProjects);
  }

  public void completeProject(String title){
    ProjectList allProjects = getAllProjects();
    allProjects.getProject(title).setCompleted(true);
    saveProjects(allProjects);
  }

  public void calculateExpected(Project project)
  {
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
