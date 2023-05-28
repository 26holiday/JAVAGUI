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

public class DepartmentGUI extends Application {
    private HOD hod;
    private ArrayList<Lab> labs;

    public HOD getHod() {
        return hod;
    }

    public void setHod(HOD hod) {
        this.hod = hod;
    }

    public ArrayList<Lab> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Lab> labs) {
        this.labs = labs;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Department GUI");

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

        // Add label and text field for HOD's name and grade
        Label hodNameLabel = new Label("HOD Name:");
        TextField hodNameField = new TextField();
        gridPane.add(hodNameLabel, 0, 0);
        gridPane.add(hodNameField, 1, 0);

        Label hodGradeLabel = new Label("HOD Grade:");
        TextField hodGradeField = new TextField();
        gridPane.add(hodGradeLabel, 0, 1);
        gridPane.add(hodGradeField, 1, 1);

        // Add button to add labs
        Button addLabsButton = new Button("Add Labs");
        addLabsButton.setOnAction(e -> {
            // Create an instance of AddLabGUI and retrieve the labs ArrayList
            AddLabGUI.display();

            // Retrieve the labs ArrayList from AddLabGUI
            labs = AddLabGUI.getLabs();
        });

        gridPane.add(addLabsButton, 0, 2, 2, 1); // Span the button across two columns

        // Add button to submit the form
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Get the values from the text
            String hodName = hodNameField.getText();
            String hodGrade = hodGradeField.getText();

            // Create an instance of HOD with the retrieved values
            hod = new HOD(hodName, hodGrade);
            setHod(hod);

            // Display the values
            System.out.println("HOD: " + hod);
            System.out.println("Labs: " + labs);
        });

        gridPane.add(submitButton, 0, 3, 2, 1); // Span the button across two columns

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

