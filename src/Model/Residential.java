package Model;

public class Residential extends Project{
  private double buildingSize;
  private int kitchens, bathrooms, otherRoomsWithPlumbing;
  private boolean renovation;

  public Residential(String t, String a, double bMin, double bMax, int tl, Customer cust, double buildS, int k, int bath, int roomsWp, boolean r){
    super(t, a, bMin, bMax, tl, cust);
    buildingSize = buildS;
    kitchens = k;
    bathrooms = bath;
    otherRoomsWithPlumbing = roomsWp;
    renovation = r;
  }

  public Residential(String t){
    super(t);
  }

  public double getBuildingSize() {
    return buildingSize;
  }

  public void setBuildingSize(double buildingSize) {
    this.buildingSize = buildingSize;
  }

  public int getKitchens() {
    return kitchens;
  }

  public void setKitchens(int kitchens) {
    this.kitchens = kitchens;
  }

  public int getBathrooms() {
    return bathrooms;
  }

  public void setBathrooms(int bathrooms) {
    this.bathrooms = bathrooms;
  }

  public int getOtherRoomsWithPlumbing() {
    return otherRoomsWithPlumbing;
  }

  public void setOtherRoomsWithPlumbing(int otherRoomsWithPlumbing) {
    this.otherRoomsWithPlumbing = otherRoomsWithPlumbing;
  }

  public boolean isRenovation() {
    return renovation;
  }

  public void setRenovation(boolean renovation) {
    this.renovation = renovation;
  }

  public String toString(){
    return "RESIDENTIAL\n" +super.toString() + "\nbuilding size: " + buildingSize + "\nkitchens: " + kitchens +
        "\nbathrooms: " + bathrooms + "\nother rooms with plumbing: " + otherRoomsWithPlumbing + "\n" +
        (renovation ? "RENOVATION" : "NEW BUILD");
  }
}
