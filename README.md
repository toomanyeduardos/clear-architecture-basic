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

# Data flow - Last update: Nov 29 2016
- The application has a `MyApplication` class, which holds a singleton for the class `ServiceLocator`
- The `ServiceLocation` acts as the factory class for elements in Layer 4. For now this is database and activities.

`MyApplication` and `ServiceLocator` are special factory classes, and we currently believe they do not fit inside
any specific layer. We think these classes can be used in any layer at any time, therefore they don't fit anywhere. We
may be wrong though, but this is a concept from the [Service](https://msdn.microsoft.com/en-us/library/ff648968.aspx) [Locator](http://www.oracle.com/technetwork/java/servicelocator-137181.html) [Pattern.](https://en.wikipedia.org/wiki/Service_locator_pattern)
The `ServiceLocator` is also intended to help us create manual dependency injection.

Initial setup:
- The app starts, and calls `MyApplication.java`
- `MyApplication` creates a new `ServiceLocator` object with the String name of the database
- The `ServiceLocator` object gets the database name, and sets up the elements for the `ServiceLocator` class
to be invoced as a singleton object.
- The `ServiceLocator` class also has a `configureMainActivity()` method with the purpose of setting up the `UseCase` class from the `MainActivity`.
Currently, this is the end of the setup. The rest should begin its lifecycle from the `MainActivity` class.

MainActivity:
- When the `MainActivity` class calls its `onCreate()`` method, it gets the singleton reference of `ServiceLocator`, and tells it to setup the `MainActivity`,
using the `configureMainActivity()` method.
- Thanks to the `ServiceLocator`, the `UseCase` object inside the `MainActivity` has already been initialized, so now the `MainActivity` class
can call `useCase.getListRectangles()`, from the `UseCase` class.
- The `MainActivity` (Layer 4) calls the `UseCase.getListRectangles()` method (Layer 2) directly and without an interface. We're still debating if this breaks any rule.
- The `UseCase` class (Layer 2) calls the `DatabaseAdapterInterface` interface (between Layers 2 and 3) to get the `getListRectangles()` method from whatever class that implements
the `DatabaseAdapterInterface`.
**This is key because we want this task of `getListRectangles()` to be performed by an adapter in Layer 3, but Layer 2 cannot know about elements in Layer 3.**
- The `DatabaseAdapterInterface` interface (between Layers 2 and 3) is implemented by `DatabaseAdapter` (Layer 3). In here the `DatabaseAdapter` overrides the `getListRectangles()` method that came
from the `DatabaseAdapterInterface` interface and returns a list of `Rectangle` POJOs.
- Since each method returns a `List<Rectangle>`, the `MainActivity` receives that List, from the initial call it made to the `UseCase`.



Eduardo.