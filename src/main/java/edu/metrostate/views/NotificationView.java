package edu.metrostate.views;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import edu.metrostate.models.Notification;

public class NotificationView {
    private VBox layout;  // Using VBox as the root layout container
    private Label notificationLabel;

    public NotificationView() {
        layout = new VBox(10);  // VBox with spacing of 10
        notificationLabel = new Label();
        layout.getChildren().add(notificationLabel);  // Add the label to the VBox
    }

    public VBox getRootPane() {
        return layout;  // Return the layout as the root pane
    }

    public void displayNotification(Notification notification) {
        // Make sure you correctly handle null values or missing data
        String message = notification.getMessage() == null ? "No message" : notification.getMessage();
        String date = notification.getDate() == null ? "No date provided" : notification.getDate();
        notificationLabel.setText("Notification: " + message + " Date: " + date);
    }
}
