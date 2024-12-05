# Phase 1

## Add Appliance
The system allows users to add new appliances with the following features:
- Type selection from predefined categories
- Model specification
- Purchase date selection using calendar
- Data validation
- SQLite database storage
- Success/error feedback

Testing:
1. Launch application
2. Click "Add New Appliance"
3. Select appliance type
4. Enter model number
5. Choose purchase date
6. Click Save
7. Verify:
    - Appliance appears in list
    - Success message shows
    - Data persists after restart

## View Appliances
Provides a comprehensive view of all appliances with:
- Scrollable list
- Type and model display
- Detailed view on selection
- Auto-refresh on updates

Testing:
1. Launch application
2. View appliance list
3. Select an appliance
4. Verify details window shows:
    - Type
    - Model
    - Purchase date
    - Service dates
    - Status
5. Add new appliance
6. Verify list updates automatically

<br><br><br>

# Phase 2

## Notification View
The system would show the users upcoming(7 days) and overdue service dates on the home screen after clicking the notifications button.
- View the notifications for the appliances that are present
- When they are due
- Last service date
- The appliance type and id

Testing:
1. Add an appliance with an overdue date
2. Add an appliance with a due date approaching within 7 days
3. Return to home page
4. Click the notification tab 
5. View the items with upcoming or overdue services
6. Verify:
   - Appliances appears in list
   - The service records are correct
   - The upcoming service date is correct
   - The message matches to the correct overdue or upcoming appliance
   
## Tutorials View
The system allows users to view tutorials related to troubleshooting the appliances in the application
- Scrollable list
- Clickable links
- Appliance type titles
- Short description

Testing
1. Enter the homepage
2. Click the tutorials button
3. View and scroll through the tutorial options
4. Verify: 
    - The list is visible
    - The links are hyper
    - The titles match the intended videos
    - The descriptions are neat and concise
  
<br><br><br>

# Phase 3

## Back Button
The system allows users to return to the home screen from any of the pages
- Notifications View
- Tutorials View
- Appliance View
  
Testing
1. Click a view
2. Click the back button

## Edit Device
The system allows users to edit a device in the system
- Change the model
- Change the type
- Change the service date
  
Testing
1. Enter appliance view
2. Add a new appliance if none exist
3. click the appliance
4. click edit appliance
5. change desired field
6. click save
7. verify:
   - Fields have changed
   - Related views are up to date
