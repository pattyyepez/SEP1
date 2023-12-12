package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * The MyDate class is used to create objects that represent dates with a day, month and year.
 *
 * @author Group SiediemSyvSiete
 * @version 1.0
 */
public class MyDate implements Serializable {
  private int day, month, year;


  /**
   * Constructs a MyDate object with the specified day, month, and year.
   *
   * @param day   The day of the date.
   * @param month The month of the date.
   * @param year  The year of the date.
   */
  public MyDate(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
    update();
  }

  /**
   * No-argument constructor for a MyDate object representing the current date, uses the today() method.
   */
  public MyDate(){
    day = today().getDay();
    month = today().getMonth();
    year = today().getYear();
  }

  /**
   * Returns the value of the day variable of this MyDate object.
   *
   * @return The value of the day variable of this MyDate object.
   */
  public int getDay() {
    return day;
  }

  /**
   * Sets the new value for the day variable of this MyDate object.
   *
   * @param day The new day value for this MyDate object.
   */
  public void setDay(int day) {
    this.day = day;
  }

  /**
   * Returns the value of the month variable of this MyDate object.
   *
   * @return The value of the month variable of this MyDate object.
   */
  public int getMonth() {
    return month;
  }

  /**
   * Sets the new value for the month variable of this MyDate object.
   *
   * @param month The new month value for this MyDate object.
   */
  public void setMonth(int month) {
    this.month = month;
  }

  /**
   * Returns the value of the year variable of this MyDate object.
   *
   * @return The value of the year variable of this MyDate object.
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets the new value for the year variable of this MyDate object.
   *
   * @param year The new year value for this MyDate object.
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Returns a MyDate object representing the end date after a certain number of months.
   *
   * @param months The number of months to add to the current date.
   * @return A MyDate object representing the end date after a certain number of months.
   */
  public MyDate getEndYear(int months){
    return new MyDate(today().getDay(), today().getMonth() + months,
        today().getYear());
  }

  /**
   * Returns a MyDate object representing the current date.
   *
   * @return A MyDate object representing today's date.
   */
  public MyDate today(){
    LocalDate current = LocalDate.now();
    return new MyDate(current.getDayOfMonth(), current.getMonthValue(), current.getYear());
  }

  /**
   * Checks if the year variable of this MyDate object is a leap year.
   *
   * @return true if the year is a leap year, false otherwise.
   */
  public boolean isLeapYear(){
    return ((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0);
  }

  /**
   * Returns the number of days in the current month variable of this MyDate object.
   *
   * @return The number of days in the current month.
   */
  public int daysInMonth(){
    return switch(month){
      case 1, 3, 5, 7, 8, 10, 12 -> 31;
      case 2 -> this.isLeapYear() ? 29 : 28;
      default -> 30;
    };
  }

  /**
   * Returns the name of the month for the current month variable of this MyDate object..
   *
   * @return The name of the month.
   */
  public String getMonthName(){
    return switch (month) {
      case 1 -> "January";
      case 2 -> "February";
      case 3 -> "March";
      case 4 -> "April";
      case 5 -> "May";
      case 6 -> "June";
      case 7 -> "July";
      case 8 -> "August";
      case 9 -> "September";
      case 10 -> "October";
      case 11 -> "November";
      case 12 -> "December";
      default -> "Error in the matrix";
    };
  }

  /**
   * Updates the date, so that if the day variable is larger than the possible amount
   * of days in the current month it is adjusted appropriately. Same for months, so if the month
   * variable is over 12, the method will subtract 12 from the month variable. Method is recursive.
   */
  public void update(){
    boolean changed = false;

    if(day > daysInMonth()) {
      day -= daysInMonth();
      month++;
      changed = true;
    }

    if (month > 12){
      month -= 12;
      year++;
      changed = true;
    }

    if(changed) update();
  }

  /**
   * Returns a long-formatted string representation of the date that shows the actual name of the month.
   *
   * @return A string in the format "dd MonthName yyyy".
   */
  public String toStringLong(){
    String day = String.format("%02d", this.day);
    return day + " " + getMonthName() + " " + year;
  }

  /**
   * Returns a short-formatted string representation of the date that shows the month as a number.
   *
   * @return A string in the format "dd/mm/yyyy".
   */
  public String toStringShort(){
    String day = String.format("%02d", this.day);
    String month = String.format("%02d", this.month);
    return day + "/" + month + "/" + year;
  }
}
