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

    Road road1 = new Road("ACR", "Kamchancha", 2000, 4000, 10, new Customer("Angela Cebrian", "738 192 253", "angela@gmail.com"), 2000, 30, 2);

    road1.setCompleted(true);
    road1.setTotalExpenses(4000);

    Residential residential4 = new Residential("House", "123 Main St", 100000, 200000, 12,
        new Customer("John Doe", "123-456-7890", "john.doe@email.com"), 1500, 2, 2, 3, true);

    Residential residential5 = new Residential("Apartment", "456 Oak St", 80000, 150000, 10,
        new Customer("Jane Smith", "987-654-3210", "jane.smith@email.com"), 1000, 1, 1, 2, false);

    Industrial industrial1 = new Industrial("Factory", "789 Industrial Blvd", 500000, 1000000, 18,
        new Customer("Michael Johnson", "555-1234", "michael.johnson@email.com"), 2500, "Manufacturing");

    Industrial industrial2 = new Industrial("Warehouse", "101 Logistics St", 300000, 800000, 15,
        new Customer("Emily Davis", "555-5678", "emily.davis@email.com"), 1200, "Storage");

    projects.addProject(residential4);
    projects.addProject(residential5);
    projects.addProject(industrial1);
    projects.addProject(industrial2);
    projects.addProject(road1);

    MyFileHandler.writeToBinaryFile("projects.bin", projects);
  }
}
