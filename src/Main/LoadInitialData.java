package Main;

import Model.Customer;
import Model.ProjectList;
import Model.ProjectModelManager;
import Model.Residential;
import Utils.MyFileHandler;

import java.io.IOException;

public class LoadInitialData {
  public static void main(String[] args)
      throws IOException, ClassNotFoundException
  {

    ProjectModelManager manager = new ProjectModelManager("projects.bin");
    ProjectList projects = new ProjectList();
    Residential residential1 = new Residential("parkway", "horsens", 5000, 10000, 6,
        new Customer("bozo", "+4512345678", "bozo@gmail.com"), 24, 1, 2, 0, false);
    projects.addProject(residential1);
    MyFileHandler.writeToBinaryFile("projects.bin", projects);
    ProjectList temp = (ProjectList) MyFileHandler.readFromBinaryFile("projects.bin");
    temp.getProject(0).setCompleted(true);
    System.out.println(temp.getProject(0));
  }
}
