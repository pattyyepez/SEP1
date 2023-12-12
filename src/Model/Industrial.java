package Model;

/**
 * The Industrial class represents an Industrial construction project, inheriting from the Project class.
 * It includes additional attributes such as facility size and the intended use of this facility.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class Industrial extends Project{
  public double facilitySize;
  public String facilityType;

  /**
   * Constructs an Industrial facility with the details given in the parameters.
   *
   * @param t                     The title of the project.
   * @param a                     The address of the project.
   * @param bMin                  The minimum budget for the project.
   * @param bMax                  The maximum budget for the project.
   * @param tl                    The timeline of the project in months.
   * @param cust                  The customer associated with the project.
   * @param fSize                 The size of the facility.
   * @param fType                 The intended use of the facility.
   */
  public Industrial(String t, String a, double bMin, double bMax, int tl, Customer cust, double fSize, String fType){
    super(t, a, bMin, bMax, tl, cust);
    facilitySize = fSize;
    facilityType = fType;
  }

  /**
   * Constructs an Industrial project with only the title.
   *
   * @param t                     The title of the project.
   */
  public Industrial(String t){
    super(t);
  }

  /**
   * Returns the size of this Industrial facility.
   *
   * @return The size of this Industrial facility.
   */
  public double getFacilitySize() {
    return facilitySize;
  }

  /**
   * Sets the new size of this Industrial facility.
   *
   * @param facilitySize The new size of this Industrial facility.
   */
  public void setFacilitySize(double facilitySize) {
    this.facilitySize = facilitySize;
  }

  /**
   * Returns the intended use of this Industrial facility.
   *
   * @return The intended use of the Industrial facility.
   */
  public String getFacilityType() {
    return facilityType;
  }

  /**
   * Sets the new intended use of this Industrial facility.
   *
   * @param facilityType The new size of this Industrial facility.
   */
  public void setFacilityType(String facilityType) {
    this.facilityType = facilityType;
  }

  /**
   * Returns a string containing an Industrial facility's information.
   *
   * @return A string containing details about the facility, including facility size and the facility's intended use.
   */
  public String toString(){
    return "INDUSTRIAL\n" + super.toString() + "\nFACILITY SIZE -> " + facilitySize + "\nFACILITY TYPE -> " + facilityType;
  }
}
