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

  public void addChallenge(String challenge){
    challenges.add(challenge);
  }

  public void removeChallenge(String challenge){
    challenges.remove(challenge);
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
}
