package edu.metrostate.models;

public class Notification {
    private int notificationId;
    private int deviceId;
    private String message;
    private String date;  // Consider using LocalDate for date handling
    private String status;

    // Constructor
    public Notification() {
        // Initialize variables if necessary
    }

    // Getters and setters
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Business methods
    public void send() {
        // Implementation to send notification
    }

    public void markAsRead() {
        // Implementation to mark the notification as read
    }

    public void scheduleNotification() {
        // Implementation to schedule a notification
    }
}
