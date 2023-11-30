package Model;

import java.util.ArrayList;

public class Industrial extends Project{
  public double facilitySize;
  public String facilityType;

  public Industrial(String t, String a, double bMin, double bMax, int tl, Customer cust, double fSize, String fType){
    super(t, a, bMin, bMax, tl, cust);
    facilitySize = fSize;
    facilityType = fType;
  }

  public Industrial(String t){
    super(t);
  }

  public double getFacilitySize() {
    return facilitySize;
  }

  public void setFacilitySize(double facilitySize) {
    this.facilitySize = facilitySize;
  }

  public String getFacilityType() {
    return facilityType;
  }

  public void setFacilityType(String facilityType) {
    this.facilityType = facilityType;
  }

  public String toString(){
    return "INDUSTRIAL\n" + super.toString() + "\nfacility size: " + facilitySize + "\nfacility type: " + facilityType;
  }

  public double calculateExpenses(ProjectList temp)
  {
    ArrayList<Project> projects = temp.getProjects();
    double count = 0;
    double expensesPerMSum = 0;
    double expectedExpenses = 0;

    for (Project project : projects)
    {
      if (project instanceof Industrial && project.isCompleted())
      {
        expensesPerMSum += project.getTotalExpenses()/((Industrial) project).getFacilitySize();
        count++;
      }
    }

    expectedExpenses = (expensesPerMSum/count) * getFacilitySize();

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
      if (project instanceof Industrial && project.isCompleted())
      {
        double hours = project.getTotalHours();
        hoursPerMSum += hours/((Industrial) project).getFacilitySize();
        count++;
      }
    }

    expectedHours = (hoursPerMSum/count) * getFacilitySize();

    expectedHours = Math.round(expectedHours) * 100.0 / 100.0;
    return expectedHours;
  }
}
