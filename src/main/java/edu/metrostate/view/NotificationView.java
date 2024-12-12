package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;
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
    private final JFrame parentFrame;

    public NotificationView(NotificationController notificationController, JFrame parentFrame) {
        // Initialize the notification controller
        this.notificationController = notificationController;
        this.databaseManager = new DatabaseManager();
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout()); // Ensure proper layout is used

        // Add Title at the Top
        JLabel titleLabel = new JLabel("<html><h1>Notifications</h1></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH); // Add the title to the top

        // Create the notification list
        notificationList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(notificationList);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center

        // Display the notifications
        displayNotifications();

        // Bottom Panel with Back Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new HomeScreenView(
                    new ApplianceController(), notificationController, new TutorialController(), parentFrame));
            parentFrame.revalidate();
        });
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH); // Add the bottom panel with the back button
    }
    

    private void displayNotifications() {
        // Load appliances from the database
        List<Appliance> appliances = databaseManager.loadAppliances();

        // Get notifications from the controller
        List<Notification> notifications = notificationController.getNotifications(appliances);

        // Create a list model to display notifications
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Add notifications to the list model with formatting
        for (Notification notification : notifications) {
            String formattedNotification = String.format(
                    "<html><div style='padding: 10px; line-height: 1.5;'><b>Appliance:</b> %s<br>%s</div></html>",
                    notification.getApplianceId(), notification.getMessage()
            );
            listModel.addElement(formattedNotification);
        }

        // Set the list model to the JList
        notificationList.setModel(listModel);
    }
}




