package com.example.javagui;

import java.util.ArrayList;


public class addEmployeeGUI{

    ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(String name, String grade) {
        Employee employee = new Employee(name, grade);
        employees.add(employee);
    }
}
