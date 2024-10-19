package com.casestudy.data.organisation;

import com.casestudy.data.organisation.csvreader.EmployeeCsvReader;
import com.casestudy.data.organisation.exception.CsvReaderException;
import com.casestudy.data.organisation.exception.DataOrganisationException;
import com.casestudy.data.organisation.model.Employee;
import com.casestudy.data.organisation.tree.EmployeeResultService;
import com.casestudy.data.organisation.tree.EmployeeTreeService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
  public static void main(String[] args) throws CsvReaderException, DataOrganisationException {

    EmployeeCsvReader employeeCsvReader = new EmployeeCsvReader();
    List<Employee> employees = employeeCsvReader.readCsv("employeeData.csv", ",");

    EmployeeTreeService tree = new EmployeeTreeService();

    Employee rootNode = tree.buildEmployeeTree(employees);

    EmployeeResultService resultService = new EmployeeResultService();

    resultService.printResults(rootNode, 0);
  }
}