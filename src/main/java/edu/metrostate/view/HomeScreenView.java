package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;

import javax.swing.*;
import java.awt.*;

public class HomeScreenView extends JPanel {
    private final JFrame parentFrame;
    private final NotificationController notificationController;
    private final TutorialController tutorialController;

    public HomeScreenView(
            ApplianceController controller,
            NotificationController notificationController,
            TutorialController tutorialController,
            JFrame parentFrame
    ) {
        this.parentFrame = parentFrame;
        this.notificationController = notificationController;
        this.tutorialController = tutorialController;
        setLayout(new BorderLayout());

        // Top Section: User and Notifications
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel("Welcome, User!", SwingConstants.LEFT); // User greeting label
        JButton notificationsButton = new JButton("🔔 Notifications"); // Notifications button
        topPanel.add(userLabel, BorderLayout.WEST); // Align label to the left
        topPanel.add(notificationsButton, BorderLayout.EAST); // Align button to the right
        add(topPanel, BorderLayout.NORTH); // Add top panel to the top of the screen

        // Center Section: Services Info
        JTextPane servicesPane = createHomeScreenTextPane(); // Home screen text pane
        JScrollPane scrollPane = new JScrollPane(servicesPane); // Add scrolling capability
        add(scrollPane, BorderLayout.CENTER); // Add to the center of the screen

        // Bottom Section: Navigation Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton tutorialsButton = new JButton("Tutorials"); // Tutorials navigation button
        JButton viewAppliancesButton = new JButton("View Appliances"); // Button for appliance management
        bottomPanel.add(tutorialsButton); // Add Tutorials button
        bottomPanel.add(viewAppliancesButton); // Add View Appliances button
        add(bottomPanel, BorderLayout.SOUTH); // Add to the bottom of the screen

        // Set up navigation listeners
        setupListeners(notificationsButton, tutorialsButton, viewAppliancesButton, controller);
    }

    private JTextPane createHomeScreenTextPane() {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html"); // Enable HTML rendering
        textPane.setText(getHomeScreenTxt()); // Set the HTML content
        textPane.setEditable(false); // Make the text non-editable
        textPane.setOpaque(false); // Blend with the background
        return textPane;
    }

    private void setupListeners(JButton notificationsButton, JButton tutorialsButton, JButton viewAppliancesButton, ApplianceController controller) {
        // Notifications button listener
        notificationsButton.addActionListener(e -> {
            NotificationView notificationView = new NotificationView(notificationController, parentFrame);
            switchToView(notificationView);
        });

        // Tutorials button listener
        tutorialsButton.addActionListener(e -> {
            TutorialsView tutorialsView = new TutorialsView(tutorialController, parentFrame);
            switchToView(tutorialsView);
        });

        // View Appliances button listener
        viewAppliancesButton.addActionListener(e -> {
            ApplianceListView applianceListView = new ApplianceListView(
                    controller, // Use the controller parameter passed to this method
                    parentFrame,
                    notificationController,
                    tutorialController
            );

            switchToView(applianceListView);
        });
    }

    private void switchToView(JPanel newView) {
        parentFrame.getContentPane().removeAll(); // Remove current content
        parentFrame.getContentPane().add(newView); // Add the new view
        parentFrame.revalidate(); // Revalidate to refresh the frame
        parentFrame.repaint(); // Repaint to ensure visual updates
    }

    // Method to display the textual information on the home screen
    private String getHomeScreenTxt() {
        return """
                <html>
                <body style="font-family: Arial, sans-serif; margin: 10px;">
                    <h1 style="text-align: center; color: #333;">House Maintenance</h1>
                    <p style="text-align: center; color: #555;">
                        Let us manage your maintenance schedule on your household appliances so that you don't have to!
                    </p>
                    <h2 style="color: #333;">Getting Started:</h2>
                    <ul style="color: #555;">
                        <li>View your appliance list below.</li>
                        <li>Don't have any yet? Add what you need to stay on top of maintenance.</li>
                        <li>The notifications tab will keep you up to date on your upcoming and overdue services.</li>
                        <li>Want to do the maintenance yourself? Click on Tutorials to see DIY tips!</li>
                    </ul>
                    <h3 style="color: #333;">Thank You</h3>
                    <p style="color: #777; font-style: italic;">
                        Enjoy peace of mind—your maintenance is covered!
                    </p>
                </body>
                </html>
                """;
    }
}
