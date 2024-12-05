package edu.metrostate;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.view.HomeScreenView;

//for notifications:
import edu.metrostate.controller.NotificationController;


import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Main() {
    }

    public static void main(String[] args) {
        logger.log(Level.INFO, "Starting Appliance Manager application");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to set Look and Feel", e);
        }

        SwingUtilities.invokeLater(() -> {
            try {
                ApplianceController controller = new ApplianceController();
                NotificationController notificationController = new NotificationController();
                //notificationController.generateSampleNotifications();  // Add some sample notifications for testing


                JFrame frame = new JFrame("Appliance Manager");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                // BEGIN: Updated logic to initialize with HomeScreenView
                HomeScreenView homeScreen = new HomeScreenView(controller, notificationController, frame);
                frame.add(homeScreen);// Add HomeScreenView as the default content
                // END: Updated logic for initial screen

                frame.setLocationRelativeTo((Component) null);
                frame.setVisible(true);
                logger.log(Level.INFO, "Application GUI initialized successfully");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to initialize application", e);
                JOptionPane.showMessageDialog(
                        (Component) null,
                        "Failed to start application: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(1);
            }
        });
    }
}
