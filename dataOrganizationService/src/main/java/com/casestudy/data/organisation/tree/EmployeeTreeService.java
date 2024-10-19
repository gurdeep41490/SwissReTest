package com.casestudy.data.organisation.tree;

import com.casestudy.data.organisation.constants.ErrorCodes;
import com.casestudy.data.organisation.exception.DataOrganisationException;
import com.casestudy.data.organisation.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeTreeService {

  /**
   * Create Employee tree from the Employee List
   * @param employees
   * @return
   */
  public Employee buildEmployeeTree(List<Employee> employees) throws DataOrganisationException {

    if (employees == null || employees.isEmpty()) {
      throw new DataOrganisationException(ErrorCodes.EMPLOYEE_LIST_EMPTY_OR_NULL);
    }

    // create a map to lookup employees quickly
    Map<Integer, Employee> employeeMap = new HashMap<>();
    Employee ceo = null;

    for (Employee employee : employees) {
      employeeMap.put(employee.getId(), employee);
    }

    // Now, construct the tree by linking employees to their managers
    for (Employee employee : employees) {
      if (employee.getManagerId() == -1) {
        // This employee is the CEO (has no manager)
        ceo = employee;
      } else {
        // Find the manager and add this employee as a subordinate
        Employee manager = employeeMap.get(employee.getManagerId());
        if (manager != null) {
          manager.addSubordinate(employee);
        }
      }
    }

    return ceo;
  }

}
