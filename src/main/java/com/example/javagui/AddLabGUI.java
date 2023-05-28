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

public class AddLabGUI extends Application {

    ArrayList<Lab> labs = new ArrayList<>();
    LabStaff incharge;
    boolean hasProjector;
    ArrayList<Computer> computers = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void addLab(LabStaff incharge, boolean hasProjector, ArrayList<Computer> computers) {
        Lab lab = new Lab(incharge, hasProjector, computers);
        labs.add(lab);
        System.out.println("Lab added: " + lab.toString());
        serializeLabs(labs);
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
        System.out.println("Computer added: " + computer.getSystemDetails());
        serializeComputers(computers);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Lab");

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

        // Add label and text field for lab incharge
        Label inchargeLabel = new Label("Incharge:");
        TextField inchargeField = new TextField();
        gridPane.add(inchargeLabel, 0, 0);
        gridPane.add(inchargeField, 1, 0);

        // Add checkbox for projector
        Label projectorLabel = new Label("Has Projector:");
        CheckBox projectorCheckBox = new CheckBox();
        gridPane.add(projectorLabel, 0, 1);
        gridPane.add(projectorCheckBox, 1, 1);

        // Add computer details section
        Label computerLabel = new Label("Computers:");
        TextArea computerTextArea = new TextArea();
        computerTextArea.setEditable(false);
        computerTextArea.setPrefRowCount(5);
        gridPane.add(computerLabel, 0, 2);
        gridPane.add(computerTextArea, 1, 2);

        // Add button to add computer
        Button addComputerButton = new Button("Add Computer");
        addComputerButton.setOnAction(e -> {
            // Create a dialog to get computer details from the user
            Dialog<Computer> dialog = new Dialog<>();
            dialog.setTitle("Add Computer");
            dialog.setHeaderText("Enter computer details");

            // Set the button types
            ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Create the computer details form
            GridPane dialogGrid = new GridPane();
            dialogGrid.setHgap(10);
            dialogGrid.setVgap(10);
            dialogGrid.setPadding(new Insets(10));

            dialogGrid.getColumnConstraints().add(col1);
            dialogGrid.getColumnConstraints().add(col2);

            TextField systemNameField = new TextField();
            TextField systemSpeedField = new TextField();
            TextField ramSizeField = new TextField();
            TextField hardDiskSizeField = new TextField();
            TextField icdMakeModelField = new TextField();

            dialogGrid.addRow(0, new Label("System Name:"), systemNameField);
            dialogGrid.addRow(1, new Label("System Speed:"), systemSpeedField);
            dialogGrid.addRow(2, new Label("RAM Size:"), ramSizeField);
            dialogGrid.addRow(3, new Label("Hard Disk Size:"), hardDiskSizeField);
            dialogGrid.addRow(4, new Label("ICD Make/Model:"), icdMakeModelField);

            dialog.getDialogPane().setContent(dialogGrid);

            // Convert the result to a computer object when the add button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButton) {
                    String systemName = systemNameField.getText();
                    String systemSpeed = systemSpeedField.getText();
                    String ramSize = ramSizeField.getText();
                    String hardDiskSize = hardDiskSizeField.getText();
                    String icdMakeModel = icdMakeModelField.getText();

                    Computer computer = new Computer(systemName, systemSpeed, ramSize, hardDiskSize, icdMakeModel);
                    addComputer(computer);
                    computerTextArea.appendText(computer.getSystemName() + "\n"); // Append the computer name to the text area
                    return computer;
                }
                return null;
            });

            dialog.showAndWait();
        });

        // Add button to add lab
        Button addLabButton = new Button("Add Lab");
        addLabButton.setOnAction(e -> {
            String incharge = inchargeField.getText();
            boolean hasProjector = projectorCheckBox.isSelected();
            addLab(new LabStaff(incharge , "test"), hasProjector, computers);
            computers.clear(); // Clear the computers list
            computerTextArea.clear(); // Clear the computer details text area
        });

        gridPane.add(addComputerButton, 0, 3);
        gridPane.add(addLabButton, 1, 3); // Add the addLabButton to the grid pane

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void serializeLabs(ArrayList<Lab> labs) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Labs.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(labs);
            out.close();
            fileOut.close();
            System.out.println("Labs serialized and saved to Labs.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeComputers(ArrayList<Computer> computers) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Computers.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(computers);
            out.close();
            fileOut.close();
            System.out.println("Computers serialized and saved to Computers.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
