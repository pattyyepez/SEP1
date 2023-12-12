package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing an ArrayList of Project objects and an ArrayList of Strings that represent the titles of those projects.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class ProjectList implements Serializable {
  private ArrayList<Project> projects;
  private ArrayList<String> titles;

  /**
   * No-argument constructor initializing the ProjectList.
   */
  public ProjectList(){
    projects = new ArrayList<>();
    titles = new ArrayList<>();
  }

  /**
   * Returns the size of the projects ArrayList.
   *
   * @return The size of the projects ArrayList.
   */
  public int getSize(){
    return projects.size();
  }

  /**
   * Checks if there is a project in the system with the given title.
   *
   * @param title   The title to be checked in the system.
   * @return        Returns true if there is a project with the given title, returns false otherwise.
   */
  public boolean containsTitle(String title){
    return titles.contains(title);
  }

  /**
   * Returns the project found at a given index, starting at 0.
   *
   * @param index   Index of the project that will be returned.
   * @return        Project from the given index.
   */
  public Project getProject(int index){
    return projects.get(index);
  }

  /**
   * Returns the project found using a given title. If no such title exists, returns null.
   *
   * @param title   Title of the project to be returned.
   * @return        Project with the given title, null if no such project is found.
   */
  public Project getProject(String title){
    for(Project temp : projects){
      if(temp.getTitle().equals(title)) return temp;
    }
    return null;
  }

  /**
   * Adds a new project to the ProjectList.
   *
   * @param project Project to be added.
   */
  public void addProject(Project project){
    projects.add(project);
    titles.add(project.getTitle());
  }

  /**
   * Edits the title of the project at a given index within the ProjectList.
   *
   * @param index     Index of the project, the title of which is going to be edited.
   * @param newTitle  The new title to replace the old one.
   */
  public void editTitle(int index, String newTitle){
    titles.set(index, newTitle);
  }

  /**
   * Returns the index of a project with a given title within the ProjectList.
   *
   * @param title   Title of the project, the index of which is going to be returned.
   * @return        The index of the project with the given title, if no such project is found, returns -1.
   */
  public int getProjectIndex(String title){
    for(int x = 0; x < projects.size(); x++){
      if(projects.get(x).getTitle().equals(title)) return x;
    }
    return -1;
  }

  /**
   * Removes a project with the given title from the ProjectList.
   *
   * @param title   Title of the project that will be removed.
   */
  public void removeProject(String title){
    for(int x = 0; x < projects.size(); x++){
      if(projects.get(x).getTitle().equals(title)){
        projects.remove(x);
        titles.remove(x);
        break;
      }
    }
  }
}
