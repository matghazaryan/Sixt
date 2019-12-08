# Sixt

I tried to use most recent and used libraries.

I tried to create simple UI using Sixt app color and logos.

Application works also in **offline mode**. If once the data fetched from internet you can start using it in offline mode. If the first time the data couldn't retrive from the server error messaged appears.

You can find APK file from [here](http://bit.ly/2rgdQ9n)

You can see the demonstration of application [here](https://youtu.be/vJePn2CLm_8)

- **Architecture Components** (Navigation, ViewModel, Databinding, LiveData)

- **Room** for SQLite

- **Retrofit** for networking

- **Coroutines** for async programming

- **Koin** for dependency injection

# Multi-modules

I have divided project into several modules

- **App** module - *This module is responsible for almost nothing and is actually doing nothing except managing dependency injection and including other modules*

- **Data** modules - *Those modules are about providing data. Each module is reponsible for the creation of its objects by creating a DI Module. Data modules contains remote,local and repository module*

- **Feature module** - *Those modules represent the features of app. I can say each screen. Feature module is responsible for creating its dependencies through DI Module*

- **Navigation module** - *This part help you to navigate between feature modules*

- **Common & CommonTest modules** - Those modules contain some common classes potentially usable everywhere. 

# MVVM and Databinding
- **ViewModel** - easiest way to store and manage UI-related data in a lifecycle conscious way. It allows data to survice on configration changes.

- **Model** - represents the data and the business logic.

- **Use Case** - If sometimes we need modofy raw data fetched from the repostiroy module it's good to use Use Case class between Repository and ViewModel

- **View** - this component has simply the responsibility to observe the LiveData and react to its changes

**Repository** - serves as the communication bridge between the data and the rest of the app

- **Databinding** - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

# Unit testing
- **Koin**  - A pragmatic lightweight dependency injection framework for Kotlin developers. Written in pure Kotlin using functional resolution only: no proxy, no code generation, no reflection!

There is unit testing on each module (remote,local, repository, list)
