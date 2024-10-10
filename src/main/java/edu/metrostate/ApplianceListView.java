package edu.metrostate.views;  // Only one correct package declaration

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import edu.metrostate.models.Appliance;  // Corrected import path for the Appliance class

import java.util.List;// Ensure this import is correct based on your project structure


public class ApplianceListView {
    private VBox layout;
    private Label applianceListLabel;

    public ApplianceListView() {
        layout = new VBox(10);
        applianceListLabel = new Label("Appliance List:");
        layout.getChildren().add(applianceListLabel);
    }

    public void displayAppliances(List<Appliance> appliances) {
        layout.getChildren().clear();
        layout.getChildren().add(applianceListLabel);
        for (Appliance appliance : appliances) {
            Label label = new Label(appliance.getModel() + " - Next service due: " + appliance.getNextServiceDueDate());
            layout.getChildren().add(label);
        }
    }

    public void displayError(String message) {
        Label errorLabel = new Label("Error: " + message);
        layout.getChildren().add(errorLabel);
    }

    public void showApplianceDetails(Appliance appliance) {
        Label detailsLabel = new Label("Details for " + appliance.getModel() + ": " +
                "Last service on " + appliance.getLastServiceDate());
        layout.getChildren().add(detailsLabel);
    }
}
