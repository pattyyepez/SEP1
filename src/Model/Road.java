package Model;

import java.util.ArrayList;

public class Road extends Project{
  private double length, width;
  private int bridgesOrTunnels;
  private ArrayList<String> challenges;

  public Road(String t, String a, double bMin, double bMax, int tl, Customer cust, double l, double w, int bort){
    super(t, a, bMin, bMax, tl, cust);
    length = l;
    width = w;
    bridgesOrTunnels = bort;
    challenges = new ArrayList<>();
  }

  public Road(String t){
    super(t);
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public int getBridgesOrTunnels() {
    return bridgesOrTunnels;
  }

  public void setBridgesOrTunnels(int bridgesOrTunnels) {
    this.bridgesOrTunnels = bridgesOrTunnels;
  }

  public ArrayList<String> getChallenges(){
    return challenges;
  }

  public void setChallenges(ArrayList<String> challenges){
    this.challenges = challenges;
  }

  public void addChallenge(String challenge){
    challenges.add(challenge);
  }

  public void removeChallenge(String challenge){
    challenges.remove(challenge);
  }

  public double getSize()
  {
    return width*length;
  }

  public String toString(){
    String challenge = "";
    for(int i = 0; i < challenges.size(); i++){
      challenge += (i+1) + ". " + challenges.get(i) + "\n";
    }
    challenge = challenge.substring(0, challenge.length()-1);
    return "ROAD\n" + super.toString() + "\nlength: " + length + "\nwidth: " + width +
        "\nnumber of bridges or tunnels: " + bridgesOrTunnels + "\ngeographical or environmental challenges: \n" + challenge;
  }

  public double calculateExpenses(ProjectList temp)
  {
    ArrayList<Project> projects = temp.getProjects();
    double count = 0;
    double expensesPerMSum = 0;
    double expectedExpenses = 0;

    for (Project project : projects)
    {
      if (project instanceof Road && project.isCompleted())
      {
        expensesPerMSum += project.getTotalExpenses()/((Road) project).getSize();
        count++;
      }
    }

    expectedExpenses = (expensesPerMSum/count) * getSize();

    for (int i = 0; i < bridgesOrTunnels; i++)
    {
      expectedExpenses += 100;
    }

    for (int i = 0; i < challenges.size(); i++)
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
      if (project instanceof Road && project.isCompleted())
      {
        double hours = project.getTotalHours();
        hoursPerMSum += hours/((Road) project).getSize();
        count++;
      }
    }

    expectedHours = (hoursPerMSum/count) * getSize();

    for (int i = 0; i < bridgesOrTunnels; i++)
    {
      expectedHours += 10;
    }

    for (int i = 0; i < challenges.size(); i++)
    {
      expectedHours += 10;
    }

    expectedHours = Math.round(expectedHours) * 100.0 / 100.0;
    return expectedHours;
  }
}
