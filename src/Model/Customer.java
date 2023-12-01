package Model;

import java.io.Serializable;

public class Customer implements Serializable {
  private String name, phoneNumber, emailAddress;

  public Customer(String name, String phoneNumber, String emailAddress){
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String toString(){
    return "CUSTOMER INFORMATION -> name: " + name + " | phone number: " + phoneNumber + " | email address: " + emailAddress;
  }

  public boolean equals(Object obj){
    if(obj == null || obj.getClass() != getClass()) return false;
    Customer temp = (Customer) obj;
    return temp.name.equals(name) &&
        temp.phoneNumber.equals(phoneNumber) &&
        temp.emailAddress.equals(emailAddress);
  }
}
