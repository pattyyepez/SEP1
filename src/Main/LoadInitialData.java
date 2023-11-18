package Main;

import Model.*;
import Utils.MyFileHandler;

import java.io.IOException;

public class LoadInitialData {
  public static void main(String[] args)
      throws IOException, ClassNotFoundException
  {

    ProjectModelManager manager = new ProjectModelManager("projects.bin");
    ProjectList projects = new ProjectList();
    Residential residential1 = new Residential("house1", "kamtjatka 13", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);
    Residential residential2 = new Residential("house2", "kamtjatka 14", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);
    Residential residential3 = new Residential("parkway", "horsens", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);
    residential3.setCompleted(true);
    projects.addProject(residential1);
    projects.addProject(residential2);
    projects.addProject(residential3);

    Commercial commercial1 = new Commercial("new bilka", "aarhus", 1, 5, 3, new Customer("bilka", "-450987654", "bilka@bilka.com"), 2000, 2, "big ahh store");
    projects.addProject(commercial1);

    MyFileHandler.writeToBinaryFile("projects.bin", projects);
  }
}
