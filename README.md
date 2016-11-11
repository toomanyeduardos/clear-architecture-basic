# clear-architecture-basic

This is a generic repo to help me understand Uncle Bob's version of clean architecture.
This repo should be generic, without any work related elements (from any company).

I called this project my Dog House project.

# User story
The app needs to have a local database from where it will read the width and height values of rectangles.
With these values, the app will then display the area of those rectangles

# Tech story
The app will have a single activity to display data.
The app will have a local database that can be swapped at any point for a different version or type of database.

The database should only return the data read from itself in a simple format, like a POJO or String.
We then have another java class that converts the POJO read from the database, and it converts it to a list of
POJOs for the app to display.
The POJOs in the app should be simple and without logic. Another class should have logic, like data validation.
The UI (activity) and database (or persistance in general) should not communicate directly with each other.

Eduardo.