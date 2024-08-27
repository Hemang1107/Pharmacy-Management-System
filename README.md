# Pharmacy Management System

## Overview
This Pharmacy Management System is a basic Java-based application designed to manage and track medicines in a pharmacy. It supports adding new medicines, managing inventory, and processing purchases.

## Key Features
- **Medicine Management**: Add, view, and manage the list of medicines.
- **Purchase Management**: Track the quantity of medicines purchased.
- **Database Integration**: All data is stored in a MySQL database for persistence.

## Classes

### Medicines:
- Represents a medicine in the inventory with details like name, price, quantity, manufacturing date, expiry date, and description.

### Purchase:
- Represents a purchase record with details about the medicine name and the quantity purchased.

### Main:
- The main driver class that manages the application flow. It handles database connections, user inputs, and processes the main operations.

## Database Setup
- **Database URL**: `jdbc:mysql://localhost:3306/pms`
- **Database User**: Defined within the code (not fully visible)
- **Tables**: The structure and schema for tables are not detailed in the provided code but are expected to handle medicine inventory and purchase records.

## Usage
1. Compile and run the `Main` class.
2. Ensure that the MySQL database is running and accessible with the credentials specified in the code.
3. Interact with the system through the command line to manage the pharmacy's medicines and purchases.
