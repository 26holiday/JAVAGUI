package com.example.javagui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CampusGUI extends Application {
    private Director director;
    private ArrayList<Department> departments=new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Campus GUI");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(100);
        gridPane.getColumnConstraints().add(col1);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(300);
        gridPane.getColumnConstraints().add(col2);

        // Add label and text field for Director's name and grade
        Label directorNameLabel = new Label("Director Name:");
        TextField directorNameField = new TextField();
        gridPane.add(directorNameLabel, 0, 0);
        gridPane.add(directorNameField, 1, 0);

        Label directorGradeLabel = new Label("Director Grade:");
        TextField directorGradeField = new TextField();
        gridPane.add(directorGradeLabel, 0, 1);
        gridPane.add(directorGradeField, 1, 1);

        // Add button to add departments
        Button addDepartmentButton = new Button("Add Department");
        addDepartmentButton.setOnAction(e -> {
            // Create an instance of AddDepartmentGUI and retrieve the Department object
            DepartmentGUI addDepartmentGUI = new DepartmentGUI();
            addDepartmentGUI.display();
            Department department = new Department(addDepartmentGUI.getHod(), addDepartmentGUI.getLabs());

            if (department != null) {
                departments.add(department);
            }
        });

        gridPane.add(addDepartmentButton, 0, 2, 2, 1); // Span the button across two columns

        Scene scene = new Scene(gridPane, 400, 200);
        // Add button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Get the values from the text fields
            String directorName = directorNameField.getText();
            String directorGrade = directorGradeField.getText();

            // Create an instance of Director with the retrieved values
            director = new Director(directorName, directorGrade);

            // Display the values
            System.out.println("Director: " + director);
            System.out.println("Departments: " + departments);
        });

        gridPane.add(submitButton, 0, 3, 2, 1); // Span the button across two columns


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
