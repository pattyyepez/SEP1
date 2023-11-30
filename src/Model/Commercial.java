package Model;

import java.util.ArrayList;

public class Commercial extends Project{
  private double buildingSize;
  private int floors;
  private String intendedUse;

  public Commercial(String t, String a, double bMin, double bMax, int tl, Customer cust, double buildS, int f, String iu){
    super(t, a, bMin, bMax, tl, cust);
    buildingSize = buildS;
    floors = f;
    intendedUse = iu;
  }

  public Commercial(String t){
    super(t);
  }

  public double getBuildingSize() {
    return buildingSize;
  }

  public void setBuildingSize(double buildingSize) {
    this.buildingSize = buildingSize;
  }

  public int getFloors() {
    return floors;
  }

  public void setFloors(int floors) {
    this.floors = floors;
  }

  public String getIntendedUse() {
    return intendedUse;
  }

  public void setIntendedUse(String intendedUse) {
    this.intendedUse = intendedUse;
  }

  public String toString(){
    return "COMMERCIAL\n" + super.toString() + "\nbuilding size: " + buildingSize +
        "\nfloors: " + floors + "\nintended use: " + intendedUse;
  }

  public double calculateExpenses(ProjectList temp)
  {
    ArrayList<Project> projects = temp.getProjects();
    double count = 0;
    double expensesPerMSum = 0;
    double expectedExpenses = 0;

    for (Project project : projects)
    {
      if (project instanceof Commercial && project.isCompleted())
      {
        expensesPerMSum += project.getTotalExpenses()/((Commercial) project).getBuildingSize();
        count++;
      }
    }

    expectedExpenses = (expensesPerMSum/count) * getBuildingSize();

    for (int i = 0; i < getFloors(); i++)
    {
      expectedExpenses += 100;
    }

    expectedExpenses = Math.round(expectedExpenses) * 100.0 / 100.0;
    return expectedExpenses;
  }

  public double calculateHours(ProjectList temp)
  {
    ArrayList<Project> projects = temp.getProjects();
    double count = 0;
    double hoursPerMSum = 0;
    double expectedHours = 0;

    for (Project project : projects)
    {
      if (project instanceof Commercial && project.isCompleted())
      {
        double hours = project.getTotalHours();
        hoursPerMSum += hours/((Commercial) project).getBuildingSize();
        count++;
      }
    }

    expectedHours = (hoursPerMSum/count) * getBuildingSize();

    for (int i = 0; i < getFloors(); i++)
    {
      expectedHours += 100;
    }

    expectedHours = Math.round(expectedHours) * 100.0 / 100.0;
    return expectedHours;
  }
}
