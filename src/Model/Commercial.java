package Model;

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
    return "COMMERCIAL\n" + super.toString() + "\nBUILDING SIZE -> " + buildingSize +
        " meters squared\nFLOORS -> " + floors + "\nINTENDED USE -> " + intendedUse;
  }
}
