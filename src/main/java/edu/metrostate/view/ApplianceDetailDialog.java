package edu.metrostate.view;

import edu.metrostate.model.Appliance;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ApplianceDetailDialog extends JDialog {
    private static final Logger logger = Logger.getLogger(ApplianceDetailDialog.class.getName());
    private Appliance appliance;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public ApplianceDetailDialog(Component parent, Appliance appliance) {
        super((Frame)SwingUtilities.getWindowAncestor(parent), "Appliance Details", true);
        this.appliance = appliance;
        this.setupUI();
        logger.log(Level.FINE, "ApplianceDetailDialog initialized for appliance: {0} - {1}", new Object[]{appliance.getApplianceType(), appliance.getModel()});
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.addField(detailsPanel, "Type:", this.appliance.getApplianceType());
        this.addField(detailsPanel, "Model:", this.appliance.getModel());
        this.addField(detailsPanel, "Purchase Date:", this.formatDate(this.appliance.getPurchaseDate()));
        this.addField(detailsPanel, "Last Service:", this.formatDate(this.appliance.getLastServiceDate()));
        this.addField(detailsPanel, "Next Service Due:", this.formatDate(this.appliance.getNextServiceDueDate()));
        this.addField(detailsPanel, "Status:", this.appliance.getStatus());
        this.add(detailsPanel, "Center");
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener((e) -> this.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        this.add(buttonPanel, "South");
        this.setDefaultCloseOperation(2);
        this.pack();
        this.setLocationRelativeTo(this.getOwner());
        this.setResizable(false);
    }

    private void addField(JPanel panel, String label, String value) {
        panel.add(new JLabel(label, 4));
        panel.add(new JLabel(value != null ? value : "N/A", 2));
    }

    private String formatDate(Date date) {
        return date != null ? this.dateFormat.format(date) : "N/A";
    }
}
