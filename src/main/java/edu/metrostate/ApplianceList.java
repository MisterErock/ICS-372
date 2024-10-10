package edu.metrostate.models;


import java.util.ArrayList;
import java.util.List;

public class ApplianceList {
    private List<Appliance> appliances = new ArrayList<>();

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public void removeAppliance(int applianceId) {
        appliances.removeIf(appliance -> appliance.getApplianceId() == applianceId);
    }

    public void updateAppliance(Appliance appliance) {
        int index = appliances.indexOf(appliance);
        if (index != -1) {
            appliances.set(index, appliance);
        }
    }

    public Appliance getAppliance(int applianceId) {
        return appliances.stream().filter(appliance -> appliance.getApplianceId() == applianceId).findFirst().orElse(null);
    }

    public List<Appliance> listAllAppliances() {
        return new ArrayList<>(appliances);
    }
}
