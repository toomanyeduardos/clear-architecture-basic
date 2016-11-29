# About this project...

This is a generic repo to help me understand Uncle Bob's version of clean architecture.
This repo should be generic, without any work related elements (from any company).

The main purpose of this app is to learn and understand the main 4 layers of clean architecture, what goes in them
and how each layer communicates with other.

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

Dependency must only be inwards the ring (so Layer 3 can know about Layer 2, but Layers 1 and 2 do not know
about Layers 3 or 4).

Visual image of the rings ([from Uncle Bob](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)):
![alt tag](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture-8b00a9d7e2543fa9ca76b81b05066629.jpg)

Ideally this project will not have libraries that obscure behavior, like Dagger, Otto or RxJava.

# Data flow - Last update: Nov 10 2016
- The application has a `MyApplication` class, which holds a singleton for the class `ServiceLocator`
- The `ServiceLocation` acts as the factory class for elements in Layer 4. For now this is database and activities.

`MyApplication` and `ServiceLocator` are special factory classes, and we currently believe they do not fit inside
any specific layer. We think these classes can be used in any layer at any time, therefore they don't fit anywhere. We
may be wrong though, but this is a concept from the Service Locator Pattern.
The `ServiceLocator` is also intended to help us create manual dependency injection.

- The `MainActivity` (Layer 4) calls the `ServiceLocator` to setup the `MainActivity`'s configuration. This will then
create a UseCase object, with a `DatabaseAdapter` (Layer 3)
- The `UseCase` (Layer 2) calls the `DatabaseAdapter` (Layer 3) through the `DatabaseAdapterInterface` (between layers 2 and 3)
to get a `List<Rectangle>`
- The `DatabaseAdapter` (Layer 3) calls the `ServiceLocator` to get a `Database` object (Layer 4) and return a String with
the contents of the `Database`
- The `DatabaseAdapter` the converts the returned `String` into a `List<Rectangle>` and the data returns back up through their
calling interfaces.

Eduardo.