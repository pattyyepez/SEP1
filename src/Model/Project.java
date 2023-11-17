package Model;

import java.io.Serializable;

public abstract class Project implements Serializable {
  private String title, address;
  private double budgetMin, budgetMax, expectedExpenses;
  private int timeline, totalHours;
  private boolean completed;
  private Customer customer;

  public Project(String t, String a, double bMin, double bMax, int tl, Customer cust){
    title = t;
    address = a;
    budgetMin = bMin;
    budgetMax = bMax;
    timeline = tl;
    customer = cust;
    completed = false;
    totalHours = 0;
    expectedExpenses = 0;
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

  public double getExpectedExpenses() {
    return expectedExpenses;
  }

  public void setExpectedExpenses(double expectedExpenses) {
    this.expectedExpenses = expectedExpenses;
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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String toString(){
    return "title: " + title + "\naddress: " + address + "\nbudget range: " + budgetMin + " - " + budgetMax +
        "\ntimeline: " + timeline + " months\n" +
        (completed ? "COMPLETED -> total hours: " + totalHours + " | expected expenses: " + expectedExpenses: "NOT COMPLETED") +
        "\n" + customer.toString();
  }
}
