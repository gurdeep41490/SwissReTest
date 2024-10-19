package com.casestudy.data.organisation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  private int id;

  private String firstName;

  private String lastName;

  private int salary;

  private int managerId;

  List<Employee> reportees;

  public void addSubordinate(Employee reportee) {
    reportees.add(reportee);
  }

  @Override
  public String toString() {
    return "Employee{id=" + id + ", fisrtName='" + firstName +
        ", lastName='" + lastName + '\'' +
        '\'' + ", managerId=" + managerId + '}';
  }
}
