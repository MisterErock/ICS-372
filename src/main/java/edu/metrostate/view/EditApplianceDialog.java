package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditApplianceDialog extends JDialog {
    private JTextField modelField;
    private JTextField typeField;
    private ApplianceController controller;
    private Appliance appliance;

    public EditApplianceDialog(JFrame parent, ApplianceController controller, Appliance appliance) {
        super(parent, "Edit Appliance", true);
        this.controller = controller;
        this.appliance = appliance;

        setupUI();
        loadApplianceData();
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Appliance Type:"));
        typeField = new JTextField();
        formPanel.add(typeField);

        formPanel.add(new JLabel("Model:"));
        modelField = new JTextField();
        formPanel.add(modelField);

        this.add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveChanges);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadApplianceData() {
        if (appliance != null) {
            typeField.setText(appliance.getApplianceType());
            modelField.setText(appliance.getModel());
        }
    }

    private void saveChanges(ActionEvent e) {
        if (appliance != null) {
            appliance.setApplianceType(typeField.getText().trim());
            appliance.setModel(modelField.getText().trim());

            controller.updateAppliance(appliance); // Assuming controller has a method for updating the appliance
            JOptionPane.showMessageDialog(this, "Appliance updated successfully!");
            dispose();
        }
    }
}
