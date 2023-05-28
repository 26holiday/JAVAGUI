package com.example.javagui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import java.util.ArrayList;

public class UniversityGUI extends University{
    public UniversityGUI(String name, ArrayList<Campus> campuses) {
        super(name, campuses);
    }

    public static void display(Stage window){
        window.setTitle("University");
        window.setWidth(500);
        window.setHeight(500);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Label label = new Label("University: ");
        GridPane.setConstraints(label, 0, 0);




        Scene scene = new Scene(gridPane);
    }

}
