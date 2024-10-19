package com.casestudy.data.organisation.csvreader;

import com.casestudy.data.organisation.exception.CsvReaderException;
import com.casestudy.data.organisation.exception.DataOrganisationException;
import com.casestudy.data.organisation.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeCsvReaderTest {

  EmployeeCsvReader employeeCsvReader;

  @BeforeEach
  public void setUp() {
    employeeCsvReader = new EmployeeCsvReader();
  }

  @Test
  void readEmployeeCsv_Test() throws CsvReaderException,DataOrganisationException {
    List<Employee> employees = employeeCsvReader.readCsv("employeeData.csv", ",");
    assertEquals(9, employees.size());
  }

  @Test
  void readEmployeeCsv_Test_Exception() {
    DataOrganisationException exception = assertThrows(DataOrganisationException.class,
        () -> employeeCsvReader.readCsv("employeeData1.csv", ","));

    assertTrue(exception.getMessage().contains("1002"));
  }
}