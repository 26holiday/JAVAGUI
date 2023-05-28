package com.example.javagui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import java.io.*;
import java.util.ArrayList;

public class UniversityGUI extends Application {

    private String name;
    private ArrayList<Campus> campuses;





    public UniversityGUI(String name, ArrayList<Campus> campuses) {
        this.name = name;
        this.campuses = campuses;
    }

    public UniversityGUI() {
        this.name = "";
        this.campuses = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("University GUI");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
    }


    public void addCampus(Campus campus) {
        campuses.add(campus);
    }

    public void removeCampus(Campus campus) {
        campuses.remove(campus);
    }

    public void printCampuses() {
        for (Campus campus : campuses) {
            System.out.println(campus.getName());
        }
    }



    public void saveUniversity() {
        try {
            FileOutputStream fileOut = new FileOutputStream("university.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in university.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
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



    public static void main(String[] args) {
        launch(args);
    }

}
