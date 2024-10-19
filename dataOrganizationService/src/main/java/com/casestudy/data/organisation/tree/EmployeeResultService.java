package com.casestudy.data.organisation.tree;

import com.casestudy.data.organisation.model.Employee;

public class EmployeeResultService {

  /**
   * Prints out the results from the Employee tree
   * @param root
   * @param level
   */
  public void printResults(Employee root, int level) {
    if (root == null) return;

    if (level > 4) {
      System.out.println(root.getFirstName() +  " "
          + root.getLastName() + " (ID: " + root.getId() + ")"
          + "Number of Managers between employee and CEO " + (level -1));
    }

    if (root.getManagerId() != -1 && root.getReportees().size() >= 1) {
      int salaryManager = root.getSalary();
      int salarySubordinates = 0;
      for (Employee subordinate : root.getReportees()){
        salarySubordinates += subordinate.getSalary();
      }
      int averageSubordinateSalary = salarySubordinates / root.getReportees().size();

      int lowerBound = averageSubordinateSalary + averageSubordinateSalary * 20 / 100;
      int upperBound = lowerBound + averageSubordinateSalary * 50 / 100;

      if (salaryManager < lowerBound ){
        System.out.println("Manager has lower salary than rules " + root.toString()
            + " by " + (lowerBound - salaryManager));
      } else if (salaryManager > upperBound) {
        System.out.println("Manager has more salary than rules" + root.toString() + " by " + (upperBound - salaryManager));
      }
    }

    for (Employee subordinate : root.getReportees()) {
      printResults(subordinate, level + 1);
    }
  }
}
