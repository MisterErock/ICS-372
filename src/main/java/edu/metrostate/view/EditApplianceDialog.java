package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditApplianceDialog extends JDialog {
    private final ApplianceController controller;
    private final Appliance appliance;
    private final JFrame parentFrame;

    public EditApplianceDialog(JFrame parentFrame, ApplianceController controller, Appliance appliance) {
        super(parentFrame, "Edit Appliance", true);
        this.parentFrame = parentFrame;
        this.controller = controller;
        this.appliance = appliance;

        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Appliance fields
        JTextField modelField = new JTextField(appliance.getModel());
        JComboBox<String> typeBox = new JComboBox<>(Appliance.APPLIANCE_TYPES);
        typeBox.setSelectedItem(appliance.getApplianceType());

        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeBox);

        formPanel.add(new JLabel("Model:"));
        formPanel.add(modelField);

        add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveAppliance(modelField.getText(), (String) typeBox.getSelectedItem()));

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::deleteAppliance);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parentFrame);
    }

    private void saveAppliance(String model, String type) {
        // Update appliance details
        appliance.setModel(model);
        appliance.setApplianceType(type);
        controller.updateAppliance(appliance);

        JOptionPane.showMessageDialog(
                this,
                "Appliance details updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose(); // Close the dialog
    }

    private void deleteAppliance(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this appliance?",
                "Delete Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteAppliance(appliance);

            JOptionPane.showMessageDialog(
                    this,
                    "Appliance deleted successfully!",
                    "Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose(); // Close the dialog
        }
    }
}
