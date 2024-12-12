package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;



public class EditApplianceDialog extends JDialog {
    private final ApplianceController controller;
    private final Appliance appliance;

    private JTextField purchaseDateField;
    private JTextField lastServiceDateField; // New field for last service date
    private JTextField typeField;


    //private JTextField typeField;  // Declare as class-level instance variable
    private JTextField modelField; //

    public EditApplianceDialog(JFrame parentFrame, ApplianceController controller, Appliance appliance) {
        super(parentFrame, "Edit Appliance", true);
        this.controller = controller;
        this.appliance = appliance;

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2)); // Adjust rows as needed


        // Appliance Type Field
        formPanel.add(new JLabel("Appliance Type:"));
        typeField = new JTextField(appliance.getApplianceType());
        System.out.println("Appliance Type at Init: " + appliance.getApplianceType()); // Debug statement
        formPanel.add(typeField);

        // Model Field
        formPanel.add(new JLabel("Model:"));
        modelField = new JTextField(appliance.getModel());
        System.out.println("Model at Init: " + appliance.getModel()); // Debug statement to confirm model initialization
        formPanel.add(modelField);

        formPanel.add(new JLabel("Purchase Date (MM/DD/YYYY):"));
        purchaseDateField = new JTextField(formatDate(appliance.getPurchaseDate()));
        formPanel.add(purchaseDateField);

        formPanel.add(new JLabel("Last Service Date (MM/DD/YYYY):")); // Add label and field for last service date
        lastServiceDateField = new JTextField(formatDate(appliance.getLastServiceDate()));
        formPanel.add(lastServiceDateField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveAppliance());
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    private void saveAppliance() {
        try {
            // Debugging statement to see if the model field is populated
            System.out.println("Model Field Before Saving: " + modelField.getText());

            String newModelValue = modelField.getText().trim();

            String newTypeValue = typeField.getText().trim();
            // Check if the model field is empty
            if (newTypeValue.isEmpty()) {
                throw new IllegalArgumentException("Type field cannot be empty.");
            }

            if (newModelValue.isEmpty()) {
                throw new IllegalArgumentException("Model field cannot be empty.");
            }

            // Update appliance information with values from the form fields
            String newApplianceType = typeField.getText().trim();
            appliance.setApplianceType(newApplianceType);
            appliance.setModel(newModelValue);

            // Save the updated appliance through the controller
            controller.updateAppliance(appliance);

            System.out.println("Updated Appliance Type: " + newApplianceType);
            System.out.println("Updated Model: " + newModelValue);

            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
            Date purchaseDate = dateFormat.parse(purchaseDateField.getText().trim());
            Date lastServiceDate = dateFormat.parse(lastServiceDateField.getText().trim());

            appliance.setPurchaseDate(purchaseDate);
            appliance.setLastServiceDate(lastServiceDate);

            // Debugging statement to see updated appliance details before updating
            System.out.println("Appliance to Update: " + appliance);

            // Save the updated appliance using the controller
            controller.updateAppliance(appliance);
            JOptionPane.showMessageDialog(this, "Appliance updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close dialog after saving
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to update appliance: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
