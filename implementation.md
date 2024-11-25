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
