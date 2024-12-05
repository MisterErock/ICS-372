package edu.metrostate.controller;

import edu.metrostate.model.Appliance;
import edu.metrostate.model.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//11/28/ 8pm
import java.util.Date;


public class NotificationController {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    // Method to generate and return a list of notifications
    public List<Notification> getNotifications(List<Appliance> appliances) {
        List<Notification> notifications = new ArrayList<>();
        Date currentDate = new Date();

        for (Appliance appliance : appliances) {
            Date nextServiceDueDate = appliance.getNextServiceDueDate();

            // Check if the appliance is overdue or service is soon
            if (nextServiceDueDate.before(currentDate)) {


                //OLD LOGIC
                // Appliance is overdue for service
                String overdueMessage =  appliance.getApplianceType() + "is OVERDUE for service. Last service was on: " + DATE_FORMAT.format(appliance.getLastServiceDate()) +
                        ". Next service was due on: " + DATE_FORMAT.format(nextServiceDueDate) + ".";
                notifications.add(new Notification(appliance.getApplianceId(), overdueMessage));
            } else if (isServiceSoon(nextServiceDueDate, currentDate)) {
                // Appliance is soon for service
                String serviceSoonMessage = appliance.getApplianceType() + "is due for service soon. Next service is due on: " + DATE_FORMAT.format(nextServiceDueDate) + ".";
                notifications.add(new Notification(appliance.getApplianceId(), serviceSoonMessage));
            }
        }
        return notifications;
    }

    // Helper method to check if service is soon (e.g., within 7 days)
    private boolean isServiceSoon(Date nextServiceDueDate, Date currentDate) {
        long differenceInMilliSeconds = nextServiceDueDate.getTime() - currentDate.getTime();
        long differenceInDays = differenceInMilliSeconds / (1000 * 60 * 60 * 24);  // Convert milliseconds to days
        return differenceInDays <= 7;  // You can adjust this threshold as needed
    }
}