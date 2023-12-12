package Model;

import java.io.Serializable;

/**
 * The Customer class represents information about a customer, including their name,
 * phone number, and email address.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class Customer implements Serializable {
  private String name, phoneNumber, emailAddress;

  /**
   * Returns the Customer's name.
   *
   * @param name Customer's name.
   * @param phoneNumber Customer's phone number.
   * @param emailAddress Customer's email address.
   */
  public Customer(String name, String phoneNumber, String emailAddress){
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  /**
   * Returns the Customer's name.
   *
   * @return The name of the Customer
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the new name of the Customer.
   *
   * @param name The new name of the Customer
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the Customer's phone number.
   *
   * @return Customer's phone number.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets a new phone number for the Customer.
   *
   * @param phoneNumber New phone number for the Customer
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns the Customer's email address.
   *
   * @return Customer's email address.
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Sets a new email address for the Customer.
   *
   * @param emailAddress New email address for the Customer.
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * Returns a string representation of the Customer's information.
   *
   * @return Generated string containing the name, phone number, and email address of the customer.
   */
  public String toString(){
    return "CUSTOMER INFORMATION -> name: " + name + " | phone number: " + phoneNumber + " | email address: " + emailAddress;
  }

  /**
   * Checks if this Customer object is equal to another object.
   * Two Customer objects are considered equal if they have the same name, phone number, and email address.
   *
   * @param obj The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  public boolean equals(Object obj){
    if(obj == null || obj.getClass() != getClass()) return false;
    Customer temp = (Customer) obj;
    return temp.name.equals(name) &&
        temp.phoneNumber.equals(phoneNumber) &&
        temp.emailAddress.equals(emailAddress);
  }
}
