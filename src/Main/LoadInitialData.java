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

    Road road1 = new Road("Project AX", "Horsens", 2000, 4000, 10, new Customer("Angela Cebrian", "738 192 253", "angela@gmail.com"), 2000, 30, 2);
    road1.setTotalHours(30);
    road1.setTotalExpenses(400000);
    road1.setCompleted(true);

    Residential residential1 = new Residential("Project TR", "kamtjatka 13", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, true);
    residential1.setTotalHours(30);
    residential1.setTotalExpenses(400000);

    Road road2 = new Road("Project LP", "Silkeborg", 5000, 20000, 12, new Customer("Joan Hageneier", "420 692 243", "joan@gmail.com"), 100, 3, 1);
    road1.setTotalHours(33);
    road1.setTotalExpenses(450000);

    Residential residential2 = new Residential("Project PS", "kamtjatka 14", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);

    Residential residential3 = new Residential("Project IL", "horsens", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);
    residential3.setTotalExpenses(250000);
    residential3.setTotalHours(2000);

    residential3.setCompleted(true);
    projects.addProject(residential1);
    projects.addProject(residential2);
    projects.addProject(residential3);

    Commercial commercial1 = new Commercial("Project BI", "aarhus", 1, 5, 3, new Customer("bilka", "-450987654", "bilka@bilka.com"), 2000, 2, "big ahh store");
    commercial1.setTotalHours(300);
    commercial1.setExpectedExpenses(540000);

    projects.addProject(commercial1);

    Residential residential4 = new Residential("Project HO", "123 Main St", 100000, 200000, 12,
        new Customer("John Doe", "123-456-7890", "john.doe@email.com"), 1500, 2, 2, 3, true);
    residential4.setTotalHours(254);
    residential4.setTotalExpenses(12000);

    Residential residential5 = new Residential("Project AP", "456 Oak St", 80000, 150000, 10,
        new Customer("Jane Smith", "987-654-3210", "jane.smith@email.com"), 1000, 1, 1, 2, false);
    residential5.setTotalExpenses(12400);
    residential5.setTotalHours(67);

    Industrial industrial1 = new Industrial("Project SL(udge)", "789 Industrial Blvd", 500000, 1000000, 18,
        new Customer("Michael Johnson", "555-1234", "michael.johnson@email.com"), 2500, "Manufacturing");
    industrial1.setTotalHours(53);
    industrial1.setExpectedExpenses(98000);

    Industrial industrial2 = new Industrial("Project ST", "101 Logistics St", 300000, 800000, 15,
        new Customer("Emily Davis", "555-5678", "emily.davis@email.com"), 1200, "Storage");
    industrial2.setTotalHours(120);
    industrial2.setExpectedExpenses(10000);

    projects.addProject(residential4);
    projects.addProject(residential5);
    projects.addProject(industrial1);
    projects.addProject(industrial2);
    projects.addProject(road1);
    projects.addProject(road2);

    MyFileHandler.writeToBinaryFile("projects.bin", projects);
  }
}
