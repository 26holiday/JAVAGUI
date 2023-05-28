package com.example.javagui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import java.io.*;

public class AddEmployeeGUI extends Application implements Serializable {

    ArrayList<Employee> employees = new ArrayList<>();
    String employeeType;

    public static void main(String[] args) {
        launch(args);
    }

    public void addEmployee(String name, String grade) {
        Employee employee = new Employee(name, grade);
        employees.add(employee);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Employee");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add drop down menu for employee type
        Label employeeTypeLabel = new Label("Employee Type:");
        ComboBox<String> employeeTypeComboBox = new ComboBox<>();
        employeeTypeComboBox.getItems().addAll("HOD", "Lab Incharge", "Director", "Lab Staff");
        employeeTypeComboBox.setValue("HOD");
        gridPane.add(employeeTypeLabel, 0, 0);
        gridPane.add(employeeTypeComboBox, 1, 0);

        employeeTypeComboBox.setOnAction(e -> {
            employeeType = employeeTypeComboBox.getValue().toString();
            System.out.println("Employee type selected: " + employeeType);
        });

        // Add name label and text field
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);

        // Add grade label and text field
        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();
        gridPane.add(gradeLabel, 0, 2);
        gridPane.add(gradeField, 1, 2);

        // Add button to add employee
        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String grade = gradeField.getText();

            if (employeeType.equals("HOD")) {
                // Create an HOD object
                HOD hod = new HOD(name, grade);
                employees.add(hod);
                System.out.println("HOD added: " + name + " (" + grade + ")");
            } else if (employeeType.equals("Lab Incharge")) {
                // Create a LabIncharge object
                LabStaff labIncharge = new LabStaff(name, grade);
                employees.add(labIncharge);
                System.out.println("Lab Incharge added: " + name + " (" + grade + ")");
            } else if (employeeType.equals("Director")) {
                // Create a Director object
                Director director = new Director(name, grade);
                employees.add(director);
                System.out.println("Director added: " + name + " (" + grade + ")");
            } else if (employeeType.equals("Lab Staff")) {
                // Create a LabStaff object
                LabStaff labStaff = new LabStaff(name, grade);
                employees.add(labStaff);
                System.out.println("Lab Staff added: " + name + " (" + grade + ")");
            }
            employees.addAll(deserializeEmployees());
            serializeEmployees(employees);

            nameField.clear();
            gradeField.clear();
        });

        gridPane.add(addButton, 0, 3);

        Scene scene = new Scene(gridPane, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void serializeEmployees(ArrayList<Employee> employees) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Employees.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employees);
            out.close();
            fileOut.close();
            System.out.println("Employees serialized and saved to Employees.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Employee> deserializeEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("Employees.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employees = (ArrayList<Employee>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Employees deserialized from Employees.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
