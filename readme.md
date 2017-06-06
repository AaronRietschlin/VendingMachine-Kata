# Vending Machine - Kata
This project is a small kata for the vending machine project to sample some good OOP & testing principles in android. 

## Prerequisites 
This project was built with the following: 

* Android Studio: 3.0 Canary1
* Android Version: 7.1 (API level 25)
* Build Tools version: 25.0.3
* Gradle version: 4.0 (uses gradle wrapper `gradlew`)
* Gradle Plugin version: 3.0.0-alpha2

## Building The Android App
### Via Android Studio
To build, you should have either a device attached via USB or an emulator running. Simply click the run button and select your device.  

### Via Comman Line
To build, you should have either a device attached via USB or an emulator running. Then, run the following command from within the project root directory 

```
./gradlew :app:clean :app:assembleDebug :app:installDebug
```

This will install the app to all connected devices. 

## Running the Tests
In order to run the android test suite, you will need to have a device connected via ADB. To run the pure JUnit tests, you can run that without a device connected as it runs solely in the local machine. 