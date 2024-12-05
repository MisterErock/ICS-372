package edu.metrostate.controller;

import edu.metrostate.model.Appliance;
import edu.metrostate.view.ApplianceListView;

import java.util.ArrayList;
import java.util.List;

public class ApplianceController {
    private List<Appliance> applianceList; // List of appliances managed by the controller
    private ApplianceListView listView;   // Reference to the UI view

    public ApplianceController() {
        this.applianceList = new ArrayList<>();
    }

    public void setListView(ApplianceListView listView) {
        this.listView = listView;
    }

    public List<Appliance> getApplianceList() {
        return applianceList;
    }

    public void addAppliance(Appliance appliance) {
        applianceList.add(appliance);
        if (listView != null) {
            listView.displayAppliances(applianceList); // Update the UI
        }
    }

    public void deleteAppliance(Appliance appliance) {
        applianceList.remove(appliance);
        if (listView != null) {
            listView.displayAppliances(applianceList); // Update the UI
        }
    }

    public void updateAppliance(Appliance updatedAppliance) {
        for (int i = 0; i < applianceList.size(); i++) {
            if (applianceList.get(i).getApplianceId().equals(updatedAppliance.getApplianceId())) {
                applianceList.set(i, updatedAppliance);
                break;
            }
        }
        if (listView != null) {
            listView.displayAppliances(applianceList); // Update the UI
        }
    }
}
