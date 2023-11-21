package Model;

import Utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

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
  }

  public void removeProject(String title){
    ProjectList allProjects = getAllProjects();
    allProjects.removeProject(title);
    saveProjects(allProjects);
  }

  public void completeProject(String title, Double expenses, int hours){
    ProjectList allProjects = getAllProjects();
    allProjects.getProject(title).setCompleted(true);
    allProjects.getProject(title).setExpectedExpenses(expenses);
    allProjects.getProject(title).setTotalHours(hours);
    saveProjects(allProjects);
  }
}
