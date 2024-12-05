package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;
import edu.metrostate.model.Appliance;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplianceListView extends JPanel {
    private static final Logger logger = Logger.getLogger(ApplianceListView.class.getName());
    private JList<Appliance> applianceList;
    private DefaultListModel<Appliance> listModel = new DefaultListModel<>();
    private ApplianceController controller;
    private JButton addButton, editButton, deleteButton, backButton;
    private JLabel statusLabel;

    private final JFrame parentFrame;
    private final NotificationController notificationController;
    private final TutorialController tutorialController;

    public ApplianceListView(ApplianceController controller, JFrame parentFrame, NotificationController notificationController, TutorialController tutorialController) {
        this.controller = controller;
        this.parentFrame = parentFrame;
        this.notificationController = notificationController;
        this.tutorialController = tutorialController;

        controller.setListView(this);
        setupUI();
        logger.log(Level.INFO, "ApplianceListView initialized");
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><h1>Appliance List</h1></html>", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        this.applianceList = new JList<>(this.listModel);
        JScrollPane scrollPane = new JScrollPane(this.applianceList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Appliances"));
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.statusLabel = new JLabel(" ");
        bottomPanel.add(this.statusLabel);

        this.backButton = new JButton("Back to Home");
        this.backButton.addActionListener(e -> navigateBackToHome());
        bottomPanel.add(this.backButton);

        this.editButton = new JButton("Edit");
        this.editButton.addActionListener(e -> showEditApplianceDialog());
        this.editButton.setEnabled(false);
        bottomPanel.add(this.editButton);

        this.addButton = new JButton("Add");
        this.addButton.addActionListener(e -> showAddApplianceDialog());
        bottomPanel.add(this.addButton);

        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(e -> deleteSelectedAppliance());
        this.deleteButton.setEnabled(false);
        bottomPanel.add(this.deleteButton);

        this.applianceList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && this.applianceList.getSelectedValue() != null) {
                Appliance selectedAppliance = this.applianceList.getSelectedValue();
                showApplianceDetails(selectedAppliance); // Show appliance details
                this.editButton.setEnabled(true);
                this.deleteButton.setEnabled(true);
            } else {
                this.editButton.setEnabled(false);
                this.deleteButton.setEnabled(false);
            }
        });

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showAddApplianceDialog() {
        new AddApplianceDialog(parentFrame, controller).setVisible(true);
    }

    private void showEditApplianceDialog() {
        Appliance selectedAppliance = applianceList.getSelectedValue();
        if (selectedAppliance != null) {
            new EditApplianceDialog(parentFrame, controller, selectedAppliance).setVisible(true);
        }
    }

    private void deleteSelectedAppliance() {
        Appliance selectedAppliance = applianceList.getSelectedValue();
        if (selectedAppliance != null) {
            controller.deleteAppliance(selectedAppliance);
        }
    }

    private void showApplianceDetails(Appliance appliance) {
        if (appliance != null) {
            new ApplianceDetailDialog(parentFrame, appliance).setVisible(true);
        }
    }

    private void navigateBackToHome() {
        if (parentFrame != null) {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(
                    new HomeScreenView(controller, notificationController, tutorialController, parentFrame)
            ); // Pass all required controllers
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }

    public void displayAppliances(List<Appliance> appliances) {
        listModel.clear();
        for (Appliance appliance : appliances) {
            listModel.addElement(appliance);
        }
    }
}
