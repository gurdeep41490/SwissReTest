package com.casestudy.data.organisation.tree;

import com.casestudy.data.organisation.exception.DataOrganisationException;
import com.casestudy.data.organisation.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTreeServiceTest {

  EmployeeTreeService employeeTreeService;

  @BeforeEach
  void setUp() {
    employeeTreeService = new EmployeeTreeService();
  }

  @Test
  void test_tree_made_successfully() throws DataOrganisationException {

    List<Employee> employees = new ArrayList<>();
    Employee employee = new Employee(123, "Joe", "Dane", 10000, -1, new ArrayList<>());
    employees.add(employee);
    for (int i = 0; i < 4; i++){
      Employee employeeTemp = new Employee(124 + i, "Joe" + i, "Dane", 10000 + i, 123 + i, new ArrayList<>());
      employees.add(employeeTemp);
    }
    Employee root = employeeTreeService.buildEmployeeTree(employees);

    assertEquals(-1, root.getManagerId());
    assertEquals(1, root.getReportees().size());
  }

  @Test
  void test_with_empty_employees() {
    List<Employee> employees = new ArrayList<>();

    DataOrganisationException exception = assertThrows(DataOrganisationException.class,
        () -> employeeTreeService.buildEmployeeTree(employees));

    assertTrue(exception.getMessage().contains("1003"));
  }
}