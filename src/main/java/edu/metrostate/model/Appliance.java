package edu.metrostate.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Appliance {
    private String applianceId = UUID.randomUUID().toString();
    private String model;
    private String applianceType;
    private Date purchaseDate;
    private Date lastServiceDate;
    private Date nextServiceDueDate;
    private String status;
    public static final String[] APPLIANCE_TYPES = new String[]{"Refrigerator", "Washing Machine", "Dishwasher", "Dryer", "Oven", "Microwave", "Other"};

    public Appliance(String applianceType, String model, Date purchaseDate) {
        this.applianceType = applianceType;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.lastServiceDate = purchaseDate;
        this.status = "Active";
        this.nextServiceDueDate = this.calculateNextServiceDate();
    }

    private Date calculateNextServiceDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.purchaseDate);
        cal.add(2, 6);
        return cal.getTime();
    }

    public String getApplianceId() {
        return this.applianceId;
    }

    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getApplianceType() {
        return this.applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getLastServiceDate() {
        return this.lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public Date getNextServiceDueDate() {
        return this.nextServiceDueDate;
    }

    public void setNextServiceDueDate(Date nextServiceDueDate) {
        this.nextServiceDueDate = nextServiceDueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return this.applianceType + " - " + this.model;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Appliance appliance = (Appliance)o;
            return this.applianceId.equals(appliance.applianceId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.applianceId.hashCode();
    }
}
