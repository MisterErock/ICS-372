package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AddApplianceDialog extends JDialog {
    private static final Logger logger = Logger.getLogger(AddApplianceDialog.class.getName());
    private JComboBox<String> typeComboBox;
    private JTextField modelField;
    private JTextField dateField;
    private ApplianceController controller;

    public AddApplianceDialog(Component parent, ApplianceController controller) {
        super((Frame)SwingUtilities.getWindowAncestor(parent), "Add New Appliance", true);
        this.controller = controller;
        this.setupUI();
        logger.log(Level.FINE, "AddApplianceDialog initialized");
    }

    private void setupUI() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        this.typeComboBox = new JComboBox(Appliance.APPLIANCE_TYPES);
        this.add(this.typeComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Model:"), gbc);
        gbc.gridx = 1;
        this.modelField = new JTextField(20);
        this.add(this.modelField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JLabel("Purchase Date (MM/dd/yyyy):"), gbc);
        gbc.gridx = 1;
        this.dateField = new JTextField(20);
        this.add(this.dateField, gbc);
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((e) -> this.saveAppliance());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> this.dispose());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(buttonPanel, gbc);
        this.setDefaultCloseOperation(2);
        this.pack();
        this.setLocationRelativeTo(this.getOwner());
        this.setResizable(false);
    }

    private void saveAppliance() {
        String type = (String)this.typeComboBox.getSelectedItem();
        String model = this.modelField.getText();
        String dateText = this.dateField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        Date purchaseDate;
        try {
            purchaseDate = dateFormat.parse(dateText);
        } catch (ParseException var8) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/dd/yyyy.", "Validation Error", 0);
            logger.log(Level.WARNING, "Invalid date format entered: {0}", dateText);
            return;
        }

        if (model.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Validation Error", 0);
            logger.log(Level.WARNING, "Attempted to save incomplete appliance data");
        } else {
            try {
                Appliance appliance = new Appliance(type, model, purchaseDate);
                this.controller.addAppliance(appliance);
                logger.log(Level.INFO, "New appliance saved: {0} - {1}", new Object[]{type, model});
                this.dispose();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to save appliance", e);
                JOptionPane.showMessageDialog(this, "Failed to save appliance: " + e.getMessage(), "Error", 0);
            }

        }
    }
}
