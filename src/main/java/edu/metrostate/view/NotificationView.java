package edu.metrostate.view;

import edu.metrostate.controller.NotificationController;
import edu.metrostate.model.Appliance;
import edu.metrostate.model.Notification;
import edu.metrostate.db.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationView extends JPanel {

    private NotificationController notificationController;
    private DatabaseManager databaseManager;
    private JList<String> notificationList;

    public NotificationView(NotificationController notificationController) {
        // Initialize the notification controller
        this.notificationController = notificationController;
        this.databaseManager = new DatabaseManager();

        // Set up the view layout
        this.setLayout(new BorderLayout());

        // Create the notification list
        notificationList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(notificationList);

        // Add the scroll pane to the panel
        this.add(scrollPane, BorderLayout.CENTER);

        // Display the notifications
        displayNotifications();
    }

    // Method to load appliances and display notifications
    private void displayNotifications() {
        // Load appliances from the database
        List<Appliance> appliances = databaseManager.loadAppliances();

        // Get notifications from the controller
        List<Notification> notifications = notificationController.getNotifications(appliances);

        // Create a list model to display notifications
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Add notifications to the list model
        for (Notification notification : notifications) {
            String applianceInfo = "Appliance: " + notification.getApplianceId();
            listModel.addElement(applianceInfo + " - " + notification.getMessage());  // Assuming getMessage() returns the notification message
        }

        // Set the list model to the JList
        notificationList.setModel(listModel);
    }
}
