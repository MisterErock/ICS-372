package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.model.Appliance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;

public class ApplianceListView extends JPanel {
    private static final Logger logger = Logger.getLogger(ApplianceListView.class.getName());
    private JList<Appliance> applianceList;
    private DefaultListModel<Appliance> listModel = new DefaultListModel();
    private ApplianceController controller;
    private JButton addButton;
    private JLabel statusLabel;

    public ApplianceListView(ApplianceController controller) {
        this.controller = controller;
        controller.setListView(this);
        this.setupUI();
        logger.log(Level.INFO, "ApplianceListView initialized");
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());
        this.applianceList = new JList(this.listModel);
        this.applianceList.setCellRenderer(new ApplianceListCellRenderer());
        this.applianceList.addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting()) {
                this.showApplianceDetails((Appliance)this.applianceList.getSelectedValue());
            }

        });
        JScrollPane scrollPane = new JScrollPane(this.applianceList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Appliances"));
        this.add(scrollPane, "Center");
        JPanel bottomPanel = new JPanel(new BorderLayout());
        this.addButton = new JButton("Add New Appliance");
        this.addButton.setIcon(UIManager.getIcon("FileView.fileIcon"));
        this.addButton.addActionListener((e) -> this.showAddApplianceDialog());
        this.statusLabel = new JLabel(" ");
        bottomPanel.add(this.statusLabel, "West");
        bottomPanel.add(this.addButton, "East");
        this.add(bottomPanel, "South");
    }

    public void displayAppliances(List<Appliance> appliances) {
        if (this.listModel == null) {
            logger.log(Level.SEVERE, "listModel is not initialized!");
        } else {
            this.listModel.clear();

            for(Appliance appliance : appliances) {
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
