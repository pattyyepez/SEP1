package Model;

import java.io.Serializable;

/**
 * The abstract class Project represents a generic project with common attributes
 * such as title, address, budget, timeline, and customer information.
 * This class is later inherited by Residential, Commercial, Industrial, and Road construction projects.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public abstract class Project implements Serializable {
  private String title, address, type;
  private double budgetMin, budgetMax, totalExpenses, expectedExpenses;
  private int timeline, totalHours, expectedHours;
  private boolean completed;
  private Customer customer;
  private MyDate startDate, endDate;

  /**
   * Constructs a Project with the details given in the parameters.
   *
   * @param t      The title of the project.
   * @param a      The address of the project.
   * @param bMin   The minimum budget for the project.
   * @param bMax   The maximum budget for the project.
   * @param tl     The timeline of the project in months.
   * @param cust   The customer associated with the project.
   */
  public Project(String t, String a, double bMin, double bMax, int tl, Customer cust){
    title = t;
    address = a;
    budgetMin = bMin;
    budgetMax = bMax;
    timeline = tl;
    customer = cust;
    completed = false;
    totalHours = 0;
    totalExpenses = 0;
    startDate = new MyDate();
    endDate = startDate.getEndYear(timeline);
    if(this instanceof Residential) type = "Residential building";
    else if(this instanceof Commercial) type = "Commercial building";
    else if(this instanceof Industrial) type = "Industrial facility";
    else if(this instanceof Road) type = "Road construction";
    else type = "null";
  }

  /**
   * Constructs a Project with only a title.
   *
   * @param t      The title of the project.
   */
  public Project(String t){
    title = t;
  }

  /**
   * Returns the title of the project.
   *
   * @return The title of the project.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets a new title for the project.
   *
   * @param title New title for the project.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the address of the project.
   *
   * @return The address of the project.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets a new address for the project.
   *
   * @param address New address for the project.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the budget minimum of the project.
   *
   * @return The budget minimum of the project.
   */
  public double getBudgetMin() {
    return budgetMin;
  }

  /**
   * Sets a new budget minimum for the project.
   *
   * @param budgetMin New budget minimum for the project.
   */
  public void setBudgetMin(double budgetMin) {
    this.budgetMin = budgetMin;
  }

  /**
   * Returns the budget maximum of the project.
   *
   * @return The budget maximum of the project.
   */
  public double getBudgetMax() {
    return budgetMax;
  }

  /**
   * Sets a new budget maximum for the project.
   *
   * @param budgetMax New budget maximum for the project.
   */
  public void setBudgetMax(double budgetMax) {
    this.budgetMax = budgetMax;
  }

  /**
   * Returns the total expenses of the project.
   *
   * @return The total expenses of the project.
   */
  public double getTotalExpenses() {
    return totalExpenses;
  }

  /**
   * Returns the expected expenses of the project.
   *
   * @return The expected expenses of the project.
   */
  public double getExpectedExpenses() {
    return expectedExpenses;
  }

  /**
   * Sets a new amount of expected expenses of the project.
   *
   * @param expectedExpenses New expected expenses of the project.
   */
  public void setExpectedExpenses(double expectedExpenses) {
    this.expectedExpenses = expectedExpenses;
  }

  /**
   * Returns the expected hours of the project.
   *
   * @return The expected hours of the project.
   */
  public int getExpectedHours() {
    return expectedHours;
  }

  /**
   * Sets a new amount of expected hours of the project.
   *
   * @param expectedHours New expected hours of the project.
   */
  public void setExpectedHours(int expectedHours) {
    this.expectedHours = expectedHours;
  }

  /**
   * Sets a new amount of total expenses of the project.
   *
   * @param totalExpenses New total expenses of the project.
   */
  public void setTotalExpenses(double totalExpenses) {
    this.totalExpenses = totalExpenses;
  }

  /**
   * Returns the timeline of the project.
   *
   * @return The timeline of the project.
   */
  public int getTimeline() {
    return timeline;
  }

  /**
   * Sets a timeline of the project.
   *
   * @param timeline New timeline of the project.
   */
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  /**
   * Returns the total hours of the project.
   *
   * @return The total hours of the project.
   */
  public int getTotalHours() {
    return totalHours;
  }

  /**
   * Sets a new amount of total hours of the project.
   *
   * @param totalHours New total hours of the project.
   */
  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }

  /**
   * Returns the current status of the project.
   *
   * @return True if the project is completed, false otherwise.
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * Sets whether the project is completed or not.
   *
   * @param completed True for a completed project, false for an on-going project.
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * Returns the end date of the project.
   *
   * @return The end date of the project.
   */
  public MyDate getEndDate() {
    return endDate;
  }

  /**
   * Returns the customer of the project.
   *
   * @return The customer of the project.
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Sets a new customer for the project.
   *
   * @param customer New customer for the project.
   */
  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  /**
   * Ends the project today by changing the end date of the project to the current date.
   */
  public void endToday(){
    endDate = new MyDate();
  }

  /**
   * Returns a string representation of the Project object.
   *
   * @return A string containing the project's details.
   */
  public String toString(){
    return "TITLE -> " + title + "\nADDRESS -> " + address + "\nBUDGET RANGE -> " + budgetMin + " - " + budgetMax +
        "\nTIME -> timeline: " + timeline + " months | start date: " + startDate.toStringLong() + " | end date: " + endDate.toStringLong() + "\n" +
        (completed ? "COMPLETED -> total hours: " + totalHours + " | total expenses: " + totalExpenses:
            "NOT COMPLETED: expected hours: " + expectedHours + " | expected expenses : " + expectedExpenses) +
        "\n" + customer.toString();
  }
}
