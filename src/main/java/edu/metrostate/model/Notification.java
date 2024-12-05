
package edu.metrostate.model;

public class Notification {
    private String message;
    private String applianceId;  // Add appliance field

    // Constructor
    public Notification(String message, String applianceId) {
        this.message = message;
        this.applianceId = applianceId;
    }

    // Getter for the message
    public String getMessage() {
        return message;
    }

    public String getApplianceId(){
        return applianceId;
    }


    // Optional: Override toString for easier debugging or displaying in lists
    @Override
    public String toString() {
        return applianceId + "-" + message;
    }
}