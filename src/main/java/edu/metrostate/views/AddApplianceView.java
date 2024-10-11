package edu.metrostate.views;  // Correct package declaration

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddApplianceView {
    private VBox layout;
    private TextField applianceDataField;
    private Button addButton;

    public AddApplianceView() {
        layout = new VBox(10);
        applianceDataField = new TextField();
        addButton = new Button("Add Appliance");

        layout.getChildren().addAll(applianceDataField, addButton);
    }

    public String getApplianceData() {
        return applianceDataField.getText();
    }

    public void displaySuccess(String message) {
        System.out.println("Success: " + message);
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}
