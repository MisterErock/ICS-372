package edu.metrostate.controllers;

import edu.metrostate.models.Appliance;
import edu.metrostate.models.ApplianceList;
import edu.metrostate.views.ApplianceListView;


public class ApplianceController {
    private ApplianceList model;
    private ApplianceListView view;

    public ApplianceController(ApplianceList model, ApplianceListView view) {
        this.model = model;
        this.view = view;
    }

    public void addAppliance(Appliance appliance) {
        model.addAppliance(appliance);
        view.displayAppliances(model.listAllAppliances());
    }

    public void updateAppliance(Appliance appliance) {
        model.updateAppliance(appliance);
        view.displayAppliances(model.listAllAppliances());
    }

    public void deleteAppliance(int applianceId) {
        model.removeAppliance(applianceId);
        view.displayAppliances(model.listAllAppliances());
    }

    public void fetchAllAppliances() {
        view.displayAppliances(model.listAllAppliances());
    }
}
