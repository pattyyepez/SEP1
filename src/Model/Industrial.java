package Model;

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
}
