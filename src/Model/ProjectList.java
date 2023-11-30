package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectList implements Serializable {
  private ArrayList<Project> projects;
  private ArrayList<String> titles;

  public ProjectList(){
    projects = new ArrayList<>();
    titles = new ArrayList<>();
  }

  public int getSize(){
    return projects.size();
  }

  public boolean containsTitle(String title){
    return titles.contains(title);
  }

  public Project getProject(int index){
    return projects.get(index);
  }

  public Project getProject(String title){
    for(Project temp : projects){
      if(temp.getTitle().equals(title)) return temp;
    }
    return null;
  }

  public void addProject(Project project){
    projects.add(project);
    titles.add(project.getTitle());
  }

  public void editTitle(int index, String newTitle){
    titles.set(index, newTitle);
  }

  public int getProjectIndex(String title){
    for(int x = 0; x < projects.size(); x++){
      if(projects.get(x).getTitle().equals(title)) return x;
    }
    return -1;
  }

  public void removeProject(String title){
    for(int x = 0; x < projects.size(); x++){
      if(projects.get(x).getTitle().equals(title)){
        projects.remove(x);
        titles.remove(x);
        break;
      }
    }
  }

  public int getTypeHours(String type){
    int totalHours = 0;
    for(Project temp : projects){
      if(temp instanceof Residential && type.equalsIgnoreCase("residential")) totalHours += temp.getTotalHours();
      else if(temp instanceof Commercial && type.equalsIgnoreCase("commercial")) totalHours += temp.getTotalHours();
      else if(temp instanceof Industrial && type.equalsIgnoreCase("industrial")) totalHours += temp.getTotalHours();
      else if(temp instanceof Road && type.equalsIgnoreCase("road")) totalHours += temp.getTotalHours();
    }
    return totalHours;
  }

  public double getTypeExpenses(String type){
    type = type.toLowerCase();
    double totalExpenses = 0;
    for(Project temp : projects){
      if(temp instanceof Residential && type.equals("residential")) totalExpenses += temp.getExpectedExpenses();
      else if(temp instanceof Commercial && type.equals("commercial")) totalExpenses += temp.getExpectedExpenses();
      else if(temp instanceof Industrial && type.equals("industrial")) totalExpenses += temp.getExpectedExpenses();
      else if(temp instanceof Road && type.equals("road")) totalExpenses += temp.getExpectedExpenses();
    }
    return totalExpenses;
  }

  public int getProjectHours(String title){
    for(Project temp : projects){
      if(temp.getTitle().equals(title)) return temp.getTotalHours();
    }
    return  -1;
  }

  public double getProjectExpenses(String title){
    for(Project temp : projects){
      if(temp.getTitle().equals(title)) return temp.getExpectedExpenses();
    }
    return  -1;
  }

  public ArrayList<Project> getProjects()
  {
    return projects;
  }
}
