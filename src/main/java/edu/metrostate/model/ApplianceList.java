package edu.metrostate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplianceList {
    private List<Appliance> appliances = new ArrayList();


    public ApplianceList() {
    }

    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }

    public void removeAppliance(Appliance appliance) {
        this.appliances.remove(appliance);
    }

    public void removeApplianceById(String id) {
        this.appliances.removeIf((appliance) -> appliance.getApplianceId().equals(id));
    }

    public void updateAppliance(Appliance appliance) {
        for(int i = 0; i < this.appliances.size(); ++i) {
            if (((Appliance)this.appliances.get(i)).getApplianceId().equals(appliance.getApplianceId())) {
                this.appliances.set(i, appliance);
                return;
            }
        }

    }

    public List<Appliance> getAllAppliances() {
        return new ArrayList(this.appliances);
    }

    public Optional<Appliance> getApplianceById(String id) {
        return this.appliances.stream().filter((appliance) -> appliance.getApplianceId().equals(id)).findFirst();
    }

    public int getApplianceCount() {
        return this.appliances.size();
    }

    public void clearAppliances() {
        this.appliances.clear();
    }

    public List<Appliance> searchAppliances(String keyword) {
        keyword = keyword.toLowerCase();
        List<Appliance> results = new ArrayList();

        for(Appliance appliance : this.appliances) {
            if (appliance.getApplianceType().toLowerCase().contains(keyword) || appliance.getModel().toLowerCase().contains(keyword)) {
                results.add(appliance);
            }
        }

        return results;
    }

    public List<Appliance> getAppliancesByType(String type) {
        return this.appliances.stream().filter((appliance) -> appliance.getApplianceType().equals(type)).toList();
    }

    public boolean isApplianceExists(String id) {
        return this.appliances.stream().anyMatch((appliance) -> appliance.getApplianceId().equals(id));
    }
}
