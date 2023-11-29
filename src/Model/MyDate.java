package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class MyDate implements Serializable {
  private int day, month, year;

  public MyDate(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
    update();
  }

  public MyDate(){
    day = today().getDay();
    month = today().getMonth();
    year = today().getYear();
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public MyDate getEndYear(int months){
    return new MyDate(today().getDay(), today().getMonth() + months,
        today().getYear());
  }

  public MyDate today(){
    LocalDate current = LocalDate.now();
    return new MyDate(current.getDayOfMonth(), current.getMonthValue(), current.getYear());
  }

  public boolean isLeapYear(){
    return ((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0);
  }

  public int daysInMonth(){
    return switch(month){
      case 1, 3, 5, 7, 8, 10, 12 -> 31;
      case 2 -> this.isLeapYear() ? 29 : 28;
      default -> 30;
    };
  }

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

  public String toString(){
    String day = String.format("%02d", this.day);
    return day + " " + getMonthName() + " " + year;
  }
}
