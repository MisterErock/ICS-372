Architecture.md 

Description 

This class diagram illustrates the main entities in the House Checkup application and their relationships. 

It includes classes for Device, User, Notification, Tutorial, ServiceRecord, and Menu. 

The class diagram includes the following classes and relationships 

  

1. User 

- Attributes: userId, name, email, password 

- Methods: addDevice(), viewDevices(), updateProfile() 

- Relationships: One-to-Many with Device 

  

2. Device 

- Attributes: deviceId, name, type, purchaseDate, lastServiceDate, nextServiceDueDate 

- Methods: updateDetails(), scheduleService() 

- Relationships: Many-to-One with User, One-to-Many with ServiceRecord 

  

3. Notification 

- Attributes: notificationId, userId, deviceId, message, date 

- Methods: send(), markAsRead() 

- Relationships: Many-to-One with User, Many-to-One with Device 
 

4. Tutorial 

- Attributes: tutorialId, title, content, videoUrl 

- Methods: view(), rate() 

  
 

5. ServiceRecord 

- Attributes: recordId, deviceId, serviceDate, serviceType, cost 

- Methods: addRecord(), viewHistory() 

- Relationships: Many-to-One with Device 

  

6. Menu 

- Attributes: menuId, name, link 

- Methods: navigate() 

  

The diagram shows the following relationships: 

- User has many Devices 

- Device belongs to one User 

- Device has many ServiceRecords 

- Notification is associated with one User and one Device 

- All entities interact with the Menu for navigation 



 
