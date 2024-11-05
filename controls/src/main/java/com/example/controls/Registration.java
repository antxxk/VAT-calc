package com.example.controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registration extends Application {
    @Override
    public void start(Stage stage) {
        // Label and TextField for name
        Text nameLabel = new Text("Name");
        TextField nameText = new TextField();

        // Label and DatePicker for date of birth
        Text dobLabel = new Text("Date of birth");
        DatePicker datePicker = new DatePicker();

        // Label and ToggleGroup for gender
        Text genderLabel = new Text("Gender");
        ToggleGroup groupGender = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(groupGender);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(groupGender);

        // Label and ToggleGroup for reservation
        Text reservationLabel = new Text("Reservation");
        ToggleButton yes = new ToggleButton("Yes");
        ToggleButton no = new ToggleButton("No");
        ToggleGroup groupReservation = new ToggleGroup();
        yes.setToggleGroup(groupReservation);
        no.setToggleGroup(groupReservation);

        // Label and CheckBoxes for technologies known
        Text technologiesLabel = new Text("Technologies Known");
        CheckBox javaCheckBox = new CheckBox("Java");
        CheckBox dotnetCheckBox = new CheckBox("DotNet");

        // Label and ListView for education
        Text educationLabel = new Text("Educational qualification");
        ObservableList<String> names = FXCollections.observableArrayList(
                "Engineering", "MCA", "MBA", "Graduation", "MTECH", "MPhil", "PhD");
        ListView<String> educationListView = new ListView<>(names);

        // Label and ChoiceBox for location
        Text locationLabel = new Text("Location");
        ChoiceBox<String> locationChoiceBox = new ChoiceBox<>();
        locationChoiceBox.getItems().addAll("Hyderabad", "Chennai", "Delhi", "Mumbai", "Visakhapatnam");

        // Register button
        Button buttonRegister = new Button("Register");
        buttonRegister.setOnAction(e -> {
            String name = nameText.getText();
            String dob = datePicker.getValue() != null ? datePicker.getValue().toString() : "Not specified";
            String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "Not specified";
            String reservation = yes.isSelected() ? "Yes" : no.isSelected() ? "No" : "Not specified";
            String technologies = (javaCheckBox.isSelected() ? "Java " : "") + (dotnetCheckBox.isSelected() ? "DotNet " : "");
            String education = educationListView.getSelectionModel().getSelectedItem() != null ? educationListView.getSelectionModel().getSelectedItem() : "Not specified";
            String location = locationChoiceBox.getValue() != null ? locationChoiceBox.getValue() : "Not specified";

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Name: " + name + "\n" +
                    "Date of Birth: " + dob + "\n" +
                    "Gender: " + gender + "\n" +
                    "Reservation: " + reservation + "\n" +
                    "Technologies: " + technologies + "\n" +
                    "Education: " + education + "\n" +
                    "Location: " + location);
            alert.showAndWait();
        });

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameText, 1, 0);
        gridPane.add(dobLabel, 0, 1);
        gridPane.add(datePicker, 1, 1);
        gridPane.add(genderLabel, 0, 2);
        gridPane.add(maleRadio, 1, 2);
        gridPane.add(femaleRadio, 2, 2);
        gridPane.add(reservationLabel, 0, 3);
        gridPane.add(yes, 1, 3);
        gridPane.add(no, 2, 3);
        gridPane.add(technologiesLabel, 0, 4);
        gridPane.add(javaCheckBox, 1, 4);
        gridPane.add(dotnetCheckBox, 2, 4);
        gridPane.add(educationLabel, 0, 5);
        gridPane.add(educationListView, 1, 5);
        gridPane.add(locationLabel, 0, 6);
        gridPane.add(locationChoiceBox, 1, 6);
        gridPane.add(buttonRegister, 2, 8);

        // Styling nodes
        buttonRegister.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        nameLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        dobLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        genderLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        reservationLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        technologiesLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        educationLabel.setStyle("-fx-font: normal bold 15px 'serif'");
        locationLabel.setStyle("-fx-font: normal bold 15px 'serif'");

        // Setting the background color
        gridPane.setStyle("-fx-background-color: BEIGE;");

        // Creating a scene object
        Scene scene = new Scene(gridPane);
        stage.setTitle("Registration Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
