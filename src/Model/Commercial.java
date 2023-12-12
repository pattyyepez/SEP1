package Model;

/**
 * The Commercial class represents a commercial construction project, inheriting from the Project class.
 * It includes additional attributes such as building size, number of floors, and the intended use of the Commercial project.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class Commercial extends Project{
  private double buildingSize;
  private int floors;
  private String intendedUse;

  /**
   * Constructs a Commercial project with the details given in the parameters.
   *
   * @param t                     The title of the project.
   * @param a                     The address of the project.
   * @param bMin                  The minimum budget for the project.
   * @param bMax                  The maximum budget for the project.
   * @param tl                    The timeline of the project in months.
   * @param cust                  The customer associated with the project.
   * @param buildS                The size of the building.
   * @param f                     The number of floors in the project.
   * @param iu                    The intended use of this Commercial project.
   */
  public Commercial(String t, String a, double bMin, double bMax, int tl, Customer cust, double buildS, int f, String iu){
    super(t, a, bMin, bMax, tl, cust);
    buildingSize = buildS;
    floors = f;
    intendedUse = iu;
  }

  /**
   * Constructs a Commercial project with only the title.
   *
   * @param t                     The title of the project.
   */
  public Commercial(String t){
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
   * Returns the number of floors in the Commercial project.
   *
   * @return The number of floors in the Commercial project.
   */
  public int getFloors() {
    return floors;
  }

  /**
   * Sets the number of floors in the Commercial project.
   *
   * @param floors The new number of floors in the Commercial project.
   */
  public void setFloors(int floors) {
    this.floors = floors;
  }

  /**
   * Returns the intended use of this Commercial project.
   *
   * @return The intended use of this Commercial project.
   */
  public String getIntendedUse() {
    return intendedUse;
  }

  /**
   * Sets the new intended use of the Commercial project.
   *
   * @param intendedUse The new intended use of the Commercial project.
   */
  public void setIntendedUse(String intendedUse) {
    this.intendedUse = intendedUse;
  }

  /**
   * Returns a string containing a Commercial project's information.
   *
   * @return A string containing details about the project, including building size, number of floors
   * and the project's intended use.
   */
  public String toString(){
    return "COMMERCIAL\n" + super.toString() + "\nBUILDING SIZE -> " + buildingSize +
        " meters squared\nFLOORS -> " + floors + "\nINTENDED USE -> " + intendedUse;
  }
}
