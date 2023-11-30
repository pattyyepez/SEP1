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

    Road road1 = new Road("ACR", "Kamchancha", 2000, 4000, 10, new Customer("Angela Cebrian", "738 192 253", "angela@gmail.com"), 2000, 30, 2);

    road1.setCompleted(true);
    road1.setTotalExpenses(4000);

    Residential residential1 = new Residential("House", "123 Main St", 100000, 200000, 12,
        new Customer("John Doe", "123-456-7890", "john.doe@email.com"), 1500, 2, 2, 3, true);

    Residential residential2 = new Residential("Apartment", "456 Oak St", 80000, 150000, 10,
        new Customer("Jane Smith", "987-654-3210", "jane.smith@email.com"), 1000, 1, 1, 2, false);

    Industrial industrial1 = new Industrial("Factory", "789 Industrial Blvd", 500000, 1000000, 18,
        new Customer("Michael Johnson", "555-1234", "michael.johnson@email.com"), 2500, "Manufacturing");

    Industrial industrial2 = new Industrial("Warehouse", "101 Logistics St", 300000, 800000, 15,
        new Customer("Emily Davis", "555-5678", "emily.davis@email.com"), 1200, "Storage");

    projects.addProject(road1);

    MyFileHandler.writeToBinaryFile("projects.bin", projects);
  }
}
