package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
// new for notifications
import edu.metrostate.controller.NotificationController;

import javax.swing.*;
import java.awt.*;

public class HomeScreenView extends JPanel {
    private final JFrame parentFrame;
    //new for notifications
    private final NotificationController notificationController;



    public HomeScreenView(ApplianceController controller, NotificationController notificationController ,JFrame parentFrame) {
        this.parentFrame = parentFrame;
        //new for notifications:
        this.notificationController = notificationController;


        setLayout(new BorderLayout());

        // Top Section: User and Notifications
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel("Welcome, User!"); // User greeting label
        JButton notificationsButton = new JButton("🔔 Notifications"); // Notifications button
        topPanel.add(userLabel, BorderLayout.WEST); // Align label to the left
        topPanel.add(notificationsButton, BorderLayout.EAST); // Align button to the right
        add(topPanel, BorderLayout.NORTH); // Add top panel to the top of the screen

        // Center Section: Updated Services List
        JList<String> servicesList = new JList<>(getHomeScreenTxt()); // Temporary data for services
        JScrollPane scrollPane = new JScrollPane(servicesList); // Add scrolling capability to the list
        add(scrollPane, BorderLayout.CENTER); // Add list to the center of the screen

        // Bottom Section: Navigation Buttons
        JPanel bottomPanel = new JPanel();
        JButton tutorialsButton = new JButton("Tutorials"); // Button to navigate to tutorials
        JButton addDeviceButton = new JButton("Add Device"); // Button to open Add Appliance dialog
        bottomPanel.add(tutorialsButton); // Add Tutorials button to the bottom panel
        bottomPanel.add(addDeviceButton); // Add Device button to the bottom panel
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to the bottom of the screen

        // Set up navigation listeners
        setupListeners(notificationsButton, tutorialsButton, addDeviceButton, controller); // Initialize button actions
    }


    private void setupListeners(JButton notificationsButton, JButton tutorialsButton, JButton addDeviceButton, ApplianceController controller) {
        // Notifications button listener (with updates to show view
        //notificationsButton.addActionListener(e -> new NotificationView(parentFrame, notificationController).setVisible(true));
        notificationsButton.addActionListener(e -> {
            // Now NotificationView only needs the notificationController
            NotificationView notificationView = new NotificationView(notificationController);
            parentFrame.getContentPane().removeAll(); // Clear the current content
            parentFrame.getContentPane().add(notificationView); // Add the new NotificationView
            parentFrame.revalidate(); // Revalidate to refresh the frame
        });


        // Tutorials button listener
        tutorialsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tutorials clicked!"));

        // Add Device button listener navigates to ApplianceListView instead of opening AddApplianceDialog
        addDeviceButton.setText("View Appliances"); // Update button text to "View Appliances"
        addDeviceButton.addActionListener(e -> {
            // Navigate to ApplianceListView
            ApplianceListView applianceListView = new ApplianceListView(controller);
            parentFrame.getContentPane().removeAll(); // Remove current content (HomeScreenView)
            parentFrame.getContentPane().add(applianceListView); // Add ApplianceListView
            parentFrame.revalidate(); // Revalidate to refresh the frame

        });
    }
    // Temporary Method To Show A Message On The HomeScreen
    private String[] getHomeScreenTxt() {
        return new String[]{
                "<html><h1>House Maintenance</h1></html>" , // Sample service item
                "<html><strong>Let us manage your maintenance schedule on your household appliances so that you don't have to!</strong><br></html>", // Description
                "<html><br><h2><strong>Getting Started:</h2></html>", // Subheading
                "<html><p>View your appliance list below</p></html>", // Instruction
                "<html><p>Don't have any yet? View and then add what you need to remain maintained</p></html>",
                "<html><p>The notifications tab will keep you up to date on your upcoming and overdue services.</p></html>", // Notifications info
                "<html><p>Want to do the maintenance yourself? Click the tutorials below to see DIY videos on keeping your appliances up to date!</p></html>",// Tutorials info
                "<html><br><h3><strong>Thank You</h3></html>",
                "<html><i>Enjoy a clear mind, we've got your scheduled maintenance covered!</i></html>"
        };
    }

}
