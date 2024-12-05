
package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;



public class ApplianceListView extends JPanel {
    private static final Logger logger = Logger.getLogger(ApplianceListView.class.getName());
    private JList<Appliance> applianceList;
    private DefaultListModel<Appliance> listModel = new DefaultListModel<>();
    private ApplianceController controller;
    private JButton addButton, editButton;
    private JLabel statusLabel;

    private final JFrame parentFrame;

    public ApplianceListView(ApplianceController controller, JFrame parentFrame) {
        this.controller = controller;
        this.parentFrame = parentFrame;

        controller.setListView(this);
        setupUI();
        logger.log(Level.INFO, "ApplianceListView initialized");
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());

        // Title Label at the top
        JLabel titleLabel = new JLabel("<html><h1>Appliance List</h1></html>", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Appliance List in the center
        this.applianceList = new JList<>(this.listModel);
        this.applianceList.setCellRenderer(new ApplianceListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(this.applianceList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Appliances"));
        this.add(scrollPane, BorderLayout.CENTER);


        // Bottom Panel with Status Label, Add Button, Edit Button, and Back Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Status Label on the left
        this.statusLabel = new JLabel(" ");
        bottomPanel.add(this.statusLabel);


    //Buttons on the bottom to go back, edit and add an appliance
        // Back Button (Left)
        if (parentFrame != null) {
            JButton backButton = new JButton("Back to Home");
            backButton.addActionListener(e -> {
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new HomeScreenView(controller, new NotificationController(), new TutorialController(), parentFrame));
                parentFrame.revalidate();
            });
            bottomPanel.add(backButton);
        }

        // Edit Appliance Button (Middle)
        this.editButton = new JButton("Edit Selected Appliance");
        this.editButton.setEnabled(false); // Disabled by default
        this.editButton.addActionListener((e) -> this.showEditApplianceDialog());
        bottomPanel.add(this.editButton);

        // Add Appliance Button (Right)
        this.addButton = new JButton("Add New Appliance");
        this.addButton.setIcon(UIManager.getIcon("FileView.fileIcon"));
        this.addButton.addActionListener((e) -> this.showAddApplianceDialog());
        bottomPanel.add(this.addButton);


        // List Selection Listener for Enabling Edit Button and Showing Details
        this.applianceList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && this.applianceList.getSelectedValue() != null) {
                this.editButton.setEnabled(true);
                showApplianceDetails(this.applianceList.getSelectedValue()); // Show details on selection
            } else {
                this.editButton.setEnabled(false);
            }
        });

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Method to show the edit dialog
    private void showEditApplianceDialog() {
        Appliance selectedAppliance = this.applianceList.getSelectedValue();
        if (selectedAppliance != null) {
            EditApplianceDialog dialog = new EditApplianceDialog(parentFrame , controller, selectedAppliance);
            dialog.setVisible(true);
        }
    }

    //Method for editing to display the device
    public void displayAppliances(List<Appliance> appliances) {
        if (this.listModel == null) {
            logger.log(Level.SEVERE, "listModel is not initialized!");
        } else {
            this.listModel.clear();
            for (Appliance appliance : appliances) {
                this.listModel.addElement(appliance);
            }
            logger.log(Level.FINE, "Updated appliance list display with {0} items", appliances.size());
        }
    }

    public void displaySuccess(String message) {
        this.statusLabel.setText(message);
        this.statusLabel.setForeground(new Color(0, 150, 0));
        Timer timer = new Timer(3000, (e) -> this.statusLabel.setText(" "));
        timer.setRepeats(false);
        timer.start();
        logger.log(Level.INFO, "Success message displayed: {0}", message);
    }

    public void displayError(String message) {
        this.statusLabel.setText(message);
        this.statusLabel.setForeground(Color.RED);
        Timer timer = new Timer(3000, (e) -> this.statusLabel.setText(" "));
        timer.setRepeats(false);
        timer.start();
        logger.log(Level.SEVERE, "Error message displayed: {0}", message);
    }

    private void showAddApplianceDialog() {
        AddApplianceDialog dialog = new AddApplianceDialog(this, this.controller);
        dialog.setVisible(true);
    }

    private void showApplianceDetails(Appliance appliance) {
        if (appliance != null) {
            ApplianceDetailDialog dialog = new ApplianceDetailDialog(this, appliance);
            dialog.setVisible(true);
        }
    }
}
