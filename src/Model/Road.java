package Model;

import java.util.ArrayList;

/**
 * The Road class represents a road construction project, inheriting from the Project class.
 * It includes additional attributes such as road length, road width, the number of bridges and/or tunnels,
 * and any environmental or geographical challenges affecting the project.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class Road extends Project{
  private double length, width;
  private int bridgesOrTunnels;
  private ArrayList<String> challenges;

  /**
   * Constructs a Road construction project with the details given in the parameters.
   *
   * @param t                     The title of the project.
   * @param a                     The address of the project.
   * @param bMin                  The minimum budget for the project.
   * @param bMax                  The maximum budget for the project.
   * @param tl                    The timeline of the project in months.
   * @param cust                  The customer associated with the project.
   * @param l                     The length of the road.
   * @param w                     The width of the road.
   * @param bort                  The number of bridges and/or tunnels needed for this road.
   */
  public Road(String t, String a, double bMin, double bMax, int tl, Customer cust, double l, double w, int bort){
    super(t, a, bMin, bMax, tl, cust);
    length = l;
    width = w;
    bridgesOrTunnels = bort;
    challenges = new ArrayList<>();
  }

  /**
   * Constructs a Road project with only the title.
   *
   * @param t                     The title of the project.
   */
  public Road(String t){
    super(t);
  }

  /**
   * Returns the length of the road.
   *
   * @return The length of the road.
   */
  public double getLength() {
    return length;
  }

  /**
   * Sets the new length of the road.
   *
   * @param length                The new length of the road.
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * Returns the width of the road.
   *
   * @return The width of the road.
   */
  public double getWidth() {
    return width;
  }

  /**
   * Sets the new width of the road.
   *
   * @param width                 The new width of the road.
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Returns the number of bridges and/or tunnels needed for the road.
   *
   * @return The number of bridges and/or tunnels needed for the road.
   */
  public int getBridgesOrTunnels() {
    return bridgesOrTunnels;
  }

  /**
   * Sets the new number of bridges and/or tunnels needed for the road.
   *
   * @param bridgesOrTunnels       The new number of bridges and/or tunnels needed for the road.
   */
  public void setBridgesOrTunnels(int bridgesOrTunnels) {
    this.bridgesOrTunnels = bridgesOrTunnels;
  }

  /**
   * Returns an ArrayList containing any environmental or geographical challenges affecting the road.
   *
   * @return An ArrayList containing any environmental or geographical challenges affecting the road.
   */
  public ArrayList<String> getChallenges(){
    return challenges;
  }

  /**
   * Sets a new ArrayList containing any environmental or geographical challenges affecting the road.
   *
   * @param challenges New ArrayList containing any environmental or geographical challenges affecting the road.
   */
  public void setChallenges(ArrayList<String> challenges){
    this.challenges = challenges;
  }

  /**
   * Adds a new environmental or geographical challenge affecting the road to the "challenges" ArrayList.
   *
   * @param challenge               New environmental or geographical challenge affecting the road.
   */
  public void addChallenge(String challenge){
    challenges.add(challenge);
  }

  /**
   * Returns a string containing a Road project's information.
   *
   * @return A string containing details about the road, including length, width, number of bridges and/or tunnels,
   * and any environmental and/or geographical challenges affecting the road.
   */
  public String toString(){
    String challenge = "";
    if(!challenges.isEmpty()){
      for(int i = 0; i < challenges.size(); i++){
        challenge += (i+1) + ". " + challenges.get(i) + "\n";
      }
      challenge = challenge.substring(0, challenge.length()-1);
    }

    return "ROAD\n" + super.toString() + "\nDIMENSIONS -> length: " + length + "km | width: " + width +
        " meters\nNUMBER OF BRIDGES AND TUNNELS -> " + bridgesOrTunnels + "\n"
        + (!challenges.isEmpty() ? "GEOGRAPHICAL / ENVIRONMENTAL CHALLENGES: \n" + challenge : "NO CHALLENGES");
  }
}
