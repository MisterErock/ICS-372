package edu.metrostate.models;
import java.util.Date;

public class Appliance {
    private int applianceId;
    private String model;
    private Date purchaseDate;
    private Date lastServiceDate;
    private Date nextServiceDueDate;
    private String status;

    public int getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(int applianceId) {
        this.applianceId = applianceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public Date getNextServiceDueDate() {
        return nextServiceDueDate;
    }

    public void setNextServiceDueDate(Date nextServiceDueDate) {
        this.nextServiceDueDate = nextServiceDueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateDetails(String model, Date lastService, Date nextService) {
        // Update details implementation
    }

    public void scheduleService() {
        // Schedule service implementation
    }

    public void checkWarranty() {
        // Check warranty implementation
    }

    public void notifyServiceDue() {
        // Notify service due implementation
    }
}
