package Model;

import Exceptions.InvalidTitleException;
import Utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectModelManager {
  private String fileName;

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
}
