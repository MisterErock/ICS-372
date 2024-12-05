package edu.metrostate;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;
import edu.metrostate.view.HomeScreenView;

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
                TutorialController tutorialController = new TutorialController();  // New TutorialController instance

                JFrame frame = new JFrame("Appliance Manager");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                // Updated logic to initialize with HomeScreenView
                HomeScreenView homeScreen = new HomeScreenView(controller, notificationController, tutorialController, frame);
                frame.add(homeScreen);  // Add HomeScreenView as the default content

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

