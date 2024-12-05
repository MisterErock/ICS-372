package edu.metrostate.view;

import edu.metrostate.model.Appliance;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplianceDetailDialog extends JDialog {
    private static final Logger logger = Logger.getLogger(ApplianceDetailDialog.class.getName());
    private final Appliance appliance;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public ApplianceDetailDialog(Component parent, Appliance appliance) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Appliance Details", true);
        this.appliance = appliance;
        setupUI();
        logger.log(Level.FINE, "ApplianceDetailDialog initialized for appliance: {0} - {1}",
                new Object[]{appliance.getApplianceType(), appliance.getModel()});
    }

    private void setupUI() {
        // Set the main layout
        this.setLayout(new BorderLayout());

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add appliance fields
        addField(detailsPanel, "Type:", appliance.getApplianceType());
        addField(detailsPanel, "Model:", appliance.getModel());
        addField(detailsPanel, "Purchase Date:", formatDate(appliance.getPurchaseDate()));
        addField(detailsPanel, "Last Service:", formatDate(appliance.getLastServiceDate()));
        addField(detailsPanel, "Next Service Due:", formatDate(appliance.getNextServiceDueDate()));
        addField(detailsPanel, "Status:", appliance.getStatus());

        this.add(detailsPanel, BorderLayout.CENTER);

        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> this.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // Dialog settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(this.getOwner());
        this.setResizable(false);
    }

    private void addField(JPanel panel, String label, String value) {
        panel.add(new JLabel(label, SwingConstants.RIGHT)); // Right-align labels
        panel.add(new JLabel(value != null ? value : "N/A", SwingConstants.LEFT)); // Left-align values
    }

    private String formatDate(Date date) {
        return date != null ? dateFormat.format(date) : "N/A";
    }
}
