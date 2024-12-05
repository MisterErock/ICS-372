package edu.metrostate.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Appliance {
    private String applianceId; // Unique identifier for each appliance
    private String model; // Appliance model name
    private String applianceType; // Type of appliance (e.g., Refrigerator)
    private Date purchaseDate; // Date of purchase
    private Date lastServiceDate; // Date of last service
    private Date nextServiceDueDate; // Date for next service
    private String status; // Status of the appliance (e.g., Active, Inactive)

    public static final String[] APPLIANCE_TYPES = {
            "Refrigerator", "Washing Machine", "Dishwasher",
            "Dryer", "Oven", "Microwave", "Other"
    };

    // Constructor
    public Appliance(String applianceType, String model, Date purchaseDate) {
        this.applianceId = UUID.randomUUID().toString(); // Generate a unique ID
        this.applianceType = applianceType;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.lastServiceDate = purchaseDate; // Initialize with purchase date
        this.status = "Active"; // Default status
        this.nextServiceDueDate = calculateNextServiceDate(); // Calculate next service date
    }

    // Calculate the next service date (6 months after the purchase date)
    private Date calculateNextServiceDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.purchaseDate);
        cal.add(Calendar.MONTH, 6); // Add 6 months
        return cal.getTime();
    }

    // Getters and Setters
    public String getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
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

    @Override
    public String toString() {
        return applianceType + " - " + model;
    }

    // equals method for comparing appliances based on their ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appliance appliance = (Appliance) o;
        return Objects.equals(applianceId, appliance.applianceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applianceId);
    }
}
