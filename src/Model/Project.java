package Model;

import java.io.Serializable;

public abstract class Project implements Serializable {
  private String title, address, type;
  private double budgetMin, budgetMax, totalExpenses, expectedExpenses;
  private int timeline, totalHours, expectedHours;
  private boolean completed;
  private Customer customer;
  private MyDate startDate, endDate;

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

  public Project(String t){
    title = t;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getBudgetMin() {
    return budgetMin;
  }

  public void setBudgetMin(double budgetMin) {
    this.budgetMin = budgetMin;
  }

  public double getBudgetMax() {
    return budgetMax;
  }

  public void setBudgetMax(double budgetMax) {
    this.budgetMax = budgetMax;
  }

  public double getTotalExpenses() {
    return totalExpenses;
  }

  public double getExpectedExpenses() {
    return expectedExpenses;
  }

  public void setExpectedExpenses(double expectedExpenses) {
    this.expectedExpenses = expectedExpenses;
  }

  public int getExpectedHours() {
    return expectedHours;
  }

  public void setExpectedHours(int expectedHours) {
    this.expectedHours = expectedHours;
  }

  public void setTotalExpenses(double totalExpenses) {
    this.totalExpenses = totalExpenses;
  }

  public int getTimeline() {
    return timeline;
  }

  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  public int getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(int totalHours) {
    this.totalHours = totalHours;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public MyDate getEndDate() {
    return endDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  public void endToday(){
    endDate = new MyDate();
  }

  public String toString(){
    return "TITLE -> " + title + "\nADDRESS -> " + address + "\nBUDGET RANGE -> " + budgetMin + " - " + budgetMax +
        "\nTIME -> timeline: " + timeline + " months | start date: " + startDate.toStringLong() + " | end date: " + endDate.toStringLong() + "\n" +
        (completed ? "COMPLETED -> total hours: " + totalHours + " | total expenses: " + totalExpenses:
            "NOT COMPLETED: expected hours: " + expectedHours + " | expected expenses : " + expectedExpenses) +
        "\n" + customer.toString();
  }
}
