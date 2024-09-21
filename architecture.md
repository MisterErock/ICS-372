Architecture.md

Description
This class diagram illustrates the main entities in the House Checkup application and their relationships. The architecture is designed using a Manager-DAO pattern to separate the business logic (handled by managers) from the database operations (handled by DAOs).

Key Components
The class diagram includes the following classes and their relationships:

User
Attributes: userId, name, email, password
Methods: addDevice(), viewDevices(), updateProfile()
Relationships: One-to-Many with Device
Device
Attributes: deviceId, name, type, purchaseDate, lastServiceDate, nextServiceDueDate
Methods: updateDetails(), scheduleService()
Relationships: Many-to-One with User, One-to-Many with ServiceRecord
Notification
Attributes: notificationId, userId, deviceId, message, date
Methods: send(), markAsRead()
Relationships: Many-to-One with User, Many-to-One with Device
Tutorial
Attributes: tutorialId, title, content, videoUrl
Methods: view(), rate()
ServiceRecord
Attributes: recordId, deviceId, serviceDate, serviceType, cost
Methods: addRecord(), viewHistory()
Relationships: Many-to-One with Device
Menu
Attributes: menuId, name, link
Methods: navigate()


Manager Classes
The manager classes handle business logic and interact with DAOs for database operations.

Device Manager
Responsibilities:
Interacts with DeviceDAO to manage devices.
Business logic for updateDetails(), scheduleService().
Coordinates actions between ServiceManager and NotificationManager for scheduled services.
Notification Manager
Responsibilities:
Handles notifications, interacting with the NotificationDAO to send and mark notifications as read.
Coordinates between DeviceManager and User to deliver relevant alerts and reminders.
Tutorial Manager
Responsibilities:
Fetches and manages tutorials.
Interacts with TutorialDAO for database operations.
Handles business logic for view(), rate() methods.
Service Manager
Responsibilities:
Manages service records by interacting with ServiceDAO.
Business logic for addRecord() and viewHistory().


DAO Classes
The DAO (Data Access Object) classes handle the persistence of entities into the database, providing a clean separation between business logic and data access.

DeviceDAO
Responsibilities:
Database operations for devices, including fetching, updating, and storing device information.
NotificationDAO
Responsibilities:
Handles database operations related to notifications, such as saving sent notifications and retrieving unread messages.
TutorialDAO
Responsibilities:
Manages tutorial-related database transactions, including fetching tutorial content.
ServiceDAO
Responsibilities:
Handles service record persistence, allowing adding and retrieving historical service data for devices.
Relationships in the Class Diagram
User has many Devices.
Device belongs to one User and has many ServiceRecords.
Notification is associated with one User and one Device.
All managers interact with their respective DAOs for database operations.
DeviceManager, ServiceManager, TutorialManager, and NotificationManager coordinate to ensure smooth functioning of the business logic.
The Menu class provides navigation between different sections of the application.

 ![classDiagram2](https://github.com/user-attachments/assets/8b931751-0c15-4dfe-9288-afaae0a9b736)

