package Model;

/**
 * The Residential class represents a residential construction project, inheriting from the Project class.
 * It includes additional attributes that weren't present in the Project class,
 * such as building size, number of kitchens, bathrooms, and rooms with plumbing,
 * and a boolean indicating whether it is a renovation or a new construction.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class Residential extends Project{
  private double buildingSize;
  private int kitchens, bathrooms, otherRoomsWithPlumbing;
  private boolean renovation;

  /**
   * Constructs a Residential project with the details given in the parameters.
   *
   * @param t                     The title of the project.
   * @param a                     The address of the project.
   * @param bMin                  The minimum budget for the project.
   * @param bMax                  The maximum budget for the project.
   * @param tl                    The timeline of the project in months.
   * @param cust                  The customer associated with the project.
   * @param buildS                The size of the building.
   * @param k                     The number of kitchens.
   * @param bath                  The number of bathrooms.
   * @param roomsWp               The number of other rooms with plumbing.
   * @param r                     A flag indicating whether it is a renovation (true) or a new construction (false).
   */
  public Residential(String t, String a, double bMin, double bMax, int tl, Customer cust, double buildS, int k, int bath, int roomsWp, boolean r){
    super(t, a, bMin, bMax, tl, cust);
    buildingSize = buildS;
    kitchens = k;
    bathrooms = bath;
    otherRoomsWithPlumbing = roomsWp;
    renovation = r;
  }

  /**
   * Constructs a Residential project with only the title specified.
   *
   * @param t The title of the project.
   */
  public Residential(String t){
    super(t);
  }

  /**
   * Returns the size of the building.
   *
   * @return The size of the building.
   */
  public double getBuildingSize() {
    return buildingSize;
  }

  /**
   * Sets the size of the building.
   *
   * @param buildingSize The new size of the building.
   */
  public void setBuildingSize(double buildingSize) {
    this.buildingSize = buildingSize;
  }

  /**
   * Returns the number of kitchens and/or kitchenettes.
   *
   * @return The number of kitchens.
   */
  public int getKitchens() {
    return kitchens;
  }

  /**
   * Sets the number of kitchens and/or kitchenitos.
   *
   * @param kitchens The new number of kitchens.
   */
  public void setKitchens(int kitchens) {
    this.kitchens = kitchens;
  }

  /**
   * Returns the number of bathrooms and/or badevaerelse.
   *
   * @return The number of bathrooms.
   */
  public int getBathrooms() {
    return bathrooms;
  }

  /**
   * Sets the number of bathrooms.
   *
   * @param bathrooms The new number of bathrooms (not directly related to the number of baths).
   */
  public void setBathrooms(int bathrooms) {
    this.bathrooms = bathrooms;
  }

  /**
   * Returns the number of other rooms with plumbing.
   *
   * @return The number of other rooms with plumbing.
   */
  public int getOtherRoomsWithPlumbing() {
    return otherRoomsWithPlumbing;
  }

  /**
   * Sets the number of other rooms with plumbing.
   *
   * @param otherRoomsWithPlumbing The new number of other rooms with plumbing.
   */
  public void setOtherRoomsWithPlumbing(int otherRoomsWithPlumbing) {
    this.otherRoomsWithPlumbing = otherRoomsWithPlumbing;
  }

  /**
   * Checks if the residential construction project is a renovation or a new construction.
   *
   * @return True if it is a renovation, false if it is a new construction.
   */
  public boolean isRenovation() {
    return renovation;
  }

  /**
   * Sets whether the residential construction project is a renovation or a new construction.
   *
   * @param renovation True for a renovation, false for a new construction.
   */
  public void setRenovation(boolean renovation) {
    this.renovation = renovation;
  }

  /**
   * Returns a string containing a Residential project's information.
   *
   * @return A string containing details about the project, including building size, number of kitchens,
   * number of bathrooms, number of other rooms with plumbing and whether it is a renovation or a new build.
   */
  public String toString(){
    return "RESIDENTIAL\n" +super.toString() + "\nBUILDING SIZE -> " + buildingSize + "\nROOMS WITH PLUMBING -> kitchens: " + kitchens +
        " | bathrooms: " + bathrooms + " | other rooms with plumbing: " + otherRoomsWithPlumbing + "\n" +
        (renovation ? "RENOVATION" : "NEW BUILD");
  }
}
