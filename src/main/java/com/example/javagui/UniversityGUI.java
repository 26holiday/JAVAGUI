package com.example.javagui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.*;
import java.util.ArrayList;

public class UniversityGUI {

    private String name;
    private ArrayList<Campus> campuses= new ArrayList<>();

    private University university;

    private CampusGUI campusGUI = new CampusGUI();

    public void display(Stage stage) throws Exception {
        stage.setTitle("University GUI");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add column constraints to adjust column width
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPrefWidth(100);
        gridPane.getColumnConstraints().add(col1Constraints);

        // Add a label and textbox for the university name
        Label nameLabel = new Label("University Name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        // Add a button to add a campus
        Button addCampusButton = new Button("Add Campus");
        gridPane.add(addCampusButton, 0, 1);


        addCampusButton.setOnAction(e -> {
            // Create a new campus object

            // Add the campus to the university
            campusGUI.display();
            addCampus(campusGUI.getCampus());
            // Print the campuses
            printCampuses();
        });

        // Add a button to submit the university
        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 0, 2);

        submitButton.setOnAction(e -> {
            // Create a new university object
            name = nameField.getText();
            university = new University(name, new ArrayList<>(campuses));
            System.out.println("University object: " + university.getName());
            // Save the university object
            saveUniversity(university);
            // Serialize the university object
            serializeUniversity(university);

        });

        Scene scene = new Scene(gridPane, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void addCampus(Campus campus) {
        campuses.add(campus);
    }


    public void printCampuses() {
        if(campuses!=null) {
            for (Campus campus : campuses) {
                if(campus!=null) {
                    System.out.println(campus.getName());
                }
            }
        }
    }

    public void saveUniversity(University university) {
        System.out.println("Saving university object: " + university);
        // Code for saving the university object
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Campus> getCampuses() {
        return campuses;
    }

    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }

    public static void serializeUniversity(University university) {
        try {
            FileOutputStream fileOut = new FileOutputStream("University.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(university);
            out.close();
            fileOut.close();
            System.out.println("University is serialized and saved to University.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static University deserializeUniversity() {
        University university =null;
        try {
            FileInputStream fileIn = new FileInputStream("University.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            university = (University) in.readObject();
            System.out.println(university);
            in.close();
            fileIn.close();
            System.out.println("University is deserialized from University.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return university;
    }


}
