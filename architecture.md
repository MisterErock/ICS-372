# Architecture.md

## Description

This MVC class diagram illustrates the main entities in the **House Checkup** application and their relationships. 

### **Key Components**

The class diagram includes the following classes and their relationships:

Models:
1. Appliance
2. ApplianceList 
3. Notification

Views:
1. AddApplianceView 
2. ApplianceListView 
3. NotificationView

Controllers:
1. ApplianceController
2. NotificationController

## **Relationships in the Class Diagram and UML Notations**
1. Controllers to Models (Dependency):

* ApplianceController -> Appliance and ApplianceList
  * a dotted line with a hollow arrow pointing from ApplianceController towards
Appliance and ApplianceList. This indicates that the controller uses these
models but does not own them. 

* NotificationController -> Notification
  * a dotted line with a hollow arrow pointing from NotificationController to Notification.

2. Controllers to Views (Association):
* ApplianceController -> ApplianceListView and AddApplianceView
  * a solid line with a hollow arrow pointing from ApplianceController to ApplianceListView and AddApplianceView. This shows that the controller updates and manages these views. 

* NotificationController -> NotificationView
  * a solid line with a hollow arrow pointing from NotificationController to NotificationView.

3. Model to Model:

* Notification needs to reference Appliance to know which appliance a notification is for. A solid line without arrows if it’s a bidirectional association or with arrows for unidirectional access. We used arrows as it is a unidirectional access.

* ApplianceList to Appliance (Aggregation):
  * Aggregation is used because ApplianceList acts as a container that can hold multiple Appliance objects but does not have exclusive ownership (i.e., Appliances can exist independently of ApplianceList).
  * Diagram Representation: a solid line with a hollow diamond at the ApplianceList end pointing towards Appliance. The multiplicity on the Appliance side would typically be 1..*, indicating that one ApplianceList can contain many Appliances.

* Appliance to Notification (Association):
  * Since Notifications might be triggered based on the state or events related to an Appliance (e.g., service due), we can establish a direct association. This could be a unidirectional association if Notifications need to reference Appliances (to get details about which appliance the notification is related to) but not necessarily the other way around.
  * Diagram Representation: a solid line with an arrow pointing from Notification to Appliance. The multiplicity would be 1..1 under typical scenarios, assuming each notification is specific to one appliance.



![MVC_class_diagram](https://github.com/user-attachments/assets/04621914-bfc1-4e85-b7ab-21566246cedd)



                                   ### **Figure: MVC class Diagram**
