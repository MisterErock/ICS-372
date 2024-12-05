package edu.metrostate.controller;

import edu.metrostate.db.DatabaseManager;
import edu.metrostate.model.Appliance;
import edu.metrostate.model.ApplianceList;
import edu.metrostate.view.ApplianceListView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplianceController {
    private static final Logger logger = Logger.getLogger(ApplianceController.class.getName());
    private ApplianceList model = new ApplianceList();
    private ApplianceListView listView;
    private DatabaseManager dbManager = new DatabaseManager();

    public ApplianceController() {
        this.loadAppliances();
    }

    private void loadAppliances() {
        try {
            List<Appliance> appliances = this.dbManager.loadAppliances();

            for(Appliance appliance : appliances) {
                this.model.addAppliance(appliance);
            }

            logger.log(Level.INFO, "Successfully loaded {0} appliances", appliances.size());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load appliances", e);
            throw new RuntimeException("Failed to load appliances", e);
        }
    }

    public void addAppliance(Appliance appliance) {
        try {
            if (this.validateAppliance(appliance)) {
                this.model.addAppliance(appliance);
                this.dbManager.saveAppliance(appliance);
                this.updateView();
                this.listView.displaySuccess("Appliance added successfully");
                logger.log(Level.INFO, "Successfully added appliance: {0} - {1}", new Object[]{appliance.getApplianceType(), appliance.getModel()});
            } else {
                this.listView.displayError("Invalid appliance data");
                logger.log(Level.WARNING, "Attempted to add invalid appliance data");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add appliance", e);
            this.listView.displayError("Failed to add appliance: " + e.getMessage());
        }

    }


    private boolean validateAppliance(Appliance appliance) {
        return appliance.getApplianceType() != null && !appliance.getApplianceType().trim().isEmpty() && appliance.getModel() != null && !appliance.getModel().trim().isEmpty() && appliance.getPurchaseDate() != null;
    }

    //private void updateView() {
      //  if (this.listView != null) {
    //    this.listView.displayAppliances(this.model.getAllAppliances());
       // }

    //}

    private void updateView() {
        if (this.listView != null) {
            listView.displayAppliances(this.model.getAllAppliances());
        }
    }

    public void setListView(ApplianceListView view) {
        this.listView = view;
        this.updateView();
    }

    //ability to edit a device
   /* public void updateAppliance(Appliance appliance) {
        try {
            if (validateAppliance(appliance)) {
                model.updateAppliance(appliance);
                dbManager.updateAppliance(appliance);
                updateView();
                listView.displaySuccess("Appliance updated successfully");
                logger.log(Level.INFO, "Successfully updated appliance: {0} - {1}", new Object[]{appliance.getApplianceType(), appliance.getModel()});
            } else {
                listView.displayError("Invalid appliance data");
                logger.log(Level.WARNING, "Attempted to update appliance with invalid data");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update appliance", e);
            listView.displayError("Failed to update appliance: " + e.getMessage());
        }
    }

    */
    public void updateAppliance(Appliance updatedAppliance) {
        try {
            this.model.updateAppliance(updatedAppliance); // Update in-memory list
            this.dbManager.updateAppliance(updatedAppliance); // Update database
            this.updateView(); // Refresh the view to reflect the changes
            logger.log(Level.INFO, "Successfully updated appliance: {0}", updatedAppliance.getApplianceType());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update appliance", e);
        }
    }

    }

