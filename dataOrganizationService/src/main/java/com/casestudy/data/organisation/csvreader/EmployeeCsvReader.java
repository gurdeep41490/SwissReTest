package com.casestudy.data.organisation.csvreader;

import com.casestudy.data.organisation.constants.ErrorCodes;
import com.casestudy.data.organisation.exception.CsvReaderException;
import com.casestudy.data.organisation.exception.DataOrganisationException;
import com.casestudy.data.organisation.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCsvReader {

  /**
   * Method to read csv file using the separator passed to function
   * @param fileName
   * @param splitBy
   * @return
   * @throws CsvReaderException
   */
  public List<Employee> readCsv(String fileName, String splitBy) throws CsvReaderException, DataOrganisationException {
    List<Employee> employees = new ArrayList<>();

    try {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classloader.getResourceAsStream(fileName);
    InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    BufferedReader bufferedReader = new BufferedReader(streamReader);
    String line;
    int counter = 0;

    while ((line = bufferedReader.readLine()) != null)   //returns a Boolean value
    {
      if (counter == 0){
        counter = 1;
        continue;
      }

      String[] employeeData = line.split(splitBy);
      int managerId = -1;
      if (employeeData.length == 5) {
         managerId = Integer.parseInt(employeeData[4]);
      }
      Employee employee = new Employee(
          Integer.parseInt(employeeData[0]), employeeData[1],
          employeeData[2], Integer.parseInt(employeeData[3]),
          managerId, new ArrayList<>());
      employees.add(employee);
    }  } catch (IOException e) {
      throw new CsvReaderException(ErrorCodes.CSVREADER_FILE_NOT_FOUND + fileName, e);
    } catch (Exception e) {
      throw new DataOrganisationException(ErrorCodes.INTERNAL_SERVER_ERROR, e);
    }

    return employees;

    // We could use csvParser from jackson , it isn't used as it was requirement to use only java
    /*return new CsvToBeanBuilder(new FileReader(filePath))
        .withType(Employee.class)
        .build()
        .parse();*/
  }
}
