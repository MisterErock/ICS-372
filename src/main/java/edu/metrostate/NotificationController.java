package edu.metrostate.controllers;

import edu.metrostate.models.Notification;
import edu.metrostate.views.NotificationView;

public class NotificationController {
    private Notification model;
    private NotificationView view;

    public NotificationController(Notification model, NotificationView view) {
        this.model = model;
        this.view = view;
    }

    public void updateNotification() {
        // Logic to update notification details
        view.displayNotification(model);
    }

    // Other controller methods...
}
