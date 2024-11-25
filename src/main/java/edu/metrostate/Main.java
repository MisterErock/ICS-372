package edu.metrostate;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.view.ApplianceListView;
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
                JFrame frame = new JFrame("Appliance Manager");
                frame.setDefaultCloseOperation(3);
                frame.setSize(800, 600);
                ApplianceListView listView = new ApplianceListView(controller);
                frame.add(listView);
                frame.setLocationRelativeTo((Component)null);
                frame.setVisible(true);
                logger.log(Level.INFO, "Application GUI initialized successfully");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to initialize application", e);
                JOptionPane.showMessageDialog((Component)null, "Failed to start application: " + e.getMessage(), "Error", 0);
                System.exit(1);
            }

        });
    }
}
