# Non-functional Requirements

## Scalability 

The application needs to be able to handle the ongoing list of devices as they are added by the user. Ideally this should be done without any performance issues. We plan to optimize the database as best we can and include a scrolling page feature to handle many devices.  

## Usability 

The application should be user-friendly. There should be limitations to what the user can see and what they can edit based on the desired functions of the application. This makes for an application that users can understand and use without reading an info sheet.  

## Data Management 

People should be able to enter the application without worrying that their data is gone or incorrect. The application should be able to securely store and manage data properly, especially regarding upcoming renewals and notifications.  


# Functional Requirement 1: View Device List 

## Actors: User 

## Use case goal: Allow the user to view the device list. 

## Primary Actor: User 

## Preconditions: User opened the application 

## Basic flow:  

1. The system displays the homepage 
2. The user clicks on “Devices List” button 
3. The system brings the user the device list page and displays the list 
4. The user can scroll through the list if necessary  

## Alternative flows :  

### Alternative flow 1 

1. The system displays the homepage  
2. The user clicks on “Devices List” button 
3. The system shows a “No devices” message because there are none yet. 

### Alternative flow 2 

1. The system displays the homepage  
2. The user clicks on “Devices List” button 
3. The user clicks on the menu to return to the homepage 

 

 
# Functional Requirement 2: Add New Device 

## Actors: User 

## Use case goal: Allow the user to add a new device/appliance. 

## Primary Actor: User 

## Preconditions: User opened the application and entered the device list page. 

## Basic flow:  

1. User clicks “Add New” button  
2. A pop up prompts the user to enter the specifications (Through the use of dropdowns) 
3. The user Clicks the complete button to add the new device.  

## Alternative flows :  

### Alternative flow 1 

1. User clicks “Add New” button 
2. User forgets to choose a drop-down option  
3. User clicks complete 
4. System displays message “Incomplete device.” 

### Alternative flow 2 

1. The user clicks “Add New” button 
2. User clicks “Cancel” to return to the previous screen.  


 
 
# Functional Requirement 3: Notifications 

## Actors: User 

## Use case goal: Show the user upcoming dates that are or will be due soon. 

## Primary Actor: User 

## Preconditions: User opened the application and has upcoming services. 

## Basic flow:  

1. The system displays the homepage 
2. The user clicks on the bell 
3. The system displays all upcoming services 
4. The user can scroll if necessary 

## Alternative flows :  

### Alternative flow 1 

1. The system displays the homepage  
2. The user clicks on the bell 
3. The system shows a “No upcoming services” message because there are no upcoming services.  
4. User clicks out of the notifications. 

### Alternative flow 2 

1. The system displays the homepage  
2. The user clicks on the bell 
3. The user checks off a notification.  
4. The system will remove the notification from view upon clicking out of the notifications 

 


# Functional Requirement 4: Tutorials 

## Actors: User 

## Use case goal: Help users navigate their services. 

## Primary Actor: User 

## Preconditions: User opened the application 

## Basic flow:  

1. The system displays the homepage 
2. The user clicks on the “Tutorials” button 
3. User can scroll and look for helpful videos 
4. User clicks on and watches video externally 

## Alternative flows :  

### Alternative flow 1 

1. The system displays the homepage 
2. The user clicks on the “Tutorials” button 
3. User returns to homepage. 

### Alternative flow 2 

1. The system displays the homepage 
2. The user clicks on the “Tutorials” button 
3. Clicks on search to look for specific tutorials 

 


# Functional Requirement 5: Edit Device Specifications 

## Actors: User 

## Use case goal: Edit device specifications. 

## Primary Actor: User 

## Preconditions: The user has devices in the system and is on the device page. 

## Basic flow:  

1. The user clicks on the Device 
2. User can then edit the specifications just as if adding a new device. 
3. User clicks complete and the device has been edited 

## Alternative flows:  

### Alternative flow 1 

1. The user clicks on the device 
2. User clicks cancel returns to the device page.  

### Alternative flow 2 

1. The user clicks on the device 
2. The user removes a specification and forgets to add a new one 
3. The system prompts an “Incomplete device” message.  


 
 
# Functional Requirement 6: Previous Page 

## Actors: User 

## Use case goal: Return to the previous page. 

## Primary Actor: User 

## Preconditions: The user is on a page other than the homepage. 

## Basic flow:  

1. The user clicks on the back button 
2. The system return them to the previous page 

## Alternative flows:  

### Alternative flow 1 
N/A 

### Alternative flow 2 
N/A 
