# Compose

A simple Kotlin-based mobile application that allows users to log in, view medicine details, and interact with the data through a user-friendly interface.

## Features
- **Login Screen**: Users can log in with any username and password without validation.
  <img src= "https://github.com/user-attachments/assets/9f16e5a4-348f-483e-9665-a503f990ceb7" alt="Splash" width="300" height="400">
- **Medicine Cards**: Displays a list of medicines with details (name, dose, strength) fetched from a remote API and stored locally using Room Database.
  ![Data](https://github.com/user-attachments/assets/46907762-8b75-4d52-b9d6-775f79c655ad)
- **Detailed View**: Tapping on a medicine card opens a detailed view showing all medicine details.
  ![Details](https://github.com/user-attachments/assets/085fd2c7-07fb-4295-b413-933de14d8f36)
- **Internet Connectivity Check**: The app checks for internet connectivity before fetching data.
- **Logout **: Logout is used to clear local room data.
  ![Dialog ](https://github.com/user-attachments/assets/f322f17e-af12-4f42-bc6b-1e49809bf763)

## Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (Clean Architecture)
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Database**: Room
- **State Management**: Flow and StateFlow
- **Testing**: JUnit for unit tests

## Usage
- Launch the app and enter any username and password on the login screen.
- Upon successful login, you will be greeted based on the time of day.
- You will see a list of medicines. Click on any card to view detailed information.

## Unit Tests
- The application includes unit tests for the login functionality and database interactions.


