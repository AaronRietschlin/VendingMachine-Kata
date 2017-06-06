# Vending Machine - Kata
This project is a small kata for the vending machine project to sample some good OOP and testing 
principles in android. This project highlights the following: 

* MVP Pattern. The MVP pattern is useful in a multitude of ways, primarily for siloing your code 
into specific functions which also helps for testing. Due to the nature of android, you cannot run 
unit tests that contain android code in your local JVM. As such, it's good practice to keep your android
code inside of classes that require it. MVP helps you keep that. 
* Testing: I took a primarily TDD approach with most of the classes here where I wrote the test code
first, then implemented the logic until the tests passed. This helps you write more testable, maintainable 
code. 
* Custom View generation: There are a couple of custom views that were created to reduce the amount 
of duplicated code. 

## Prerequisites 
This project was built with the following: 

* Android Studio: 3.0 Canary1
* Android Version: 7.1 (API level 25)
* Build Tools version: 25.0.3
* Gradle version: 4.0 (uses gradle wrapper `gradlew`)
* Gradle Plugin version: 3.0.0-alpha2

## Improvements that could be made
* One thing I would have liked to have done is implement this with 100% kotlin. I have some experience
with Kotlin and love the language but decided to implement in Java as that is what I am most proficient in. 

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
### JUnit Tests
#### Via Command Line
To run the pure JUnit tests, you can run that without a device connected as it runs solely in the local machine. 
Simply run the following command: 
 
```
./gradlew test
```

The output will be displayed in line as the tests run. This is done via the `tests.gradle` file. Alternatively, you can see the results in the build folder in the following location:

* **app module:**
    ```
    Pillar-VendingMachine-Kata/app/build/reports/tests/testDebugUnitTest/index.html
    ```
* **model module:**
    ```
    Pillar-VendingMachine-Kata/model/build/reports/tests/testDebugUnitTest/index.html
    ```

#### Via Android Studio
1. Expand the module for which you would like to test.
2. Expand the `src/test` directory. 
3. Right click on `java`.
4. Click `Run Tests in java`.
5. See results.

### Android Tests
In order to run the android test suite, you will need to have a device connected via ADB. 
#### Via Command Line
Run the following command: 

```
./gradlew connectedAndroidTest
```

To see the outputs, you can view the results in the generated files in the build directory: 

```
Pillar-VendingMachine-Kata/app/build/reports/androidTests/connected/index.html
```

#### Via Android Studio
1. Expand the `app/src/androidTest` directory. 
2. Right click `app/src/androidTest/java`.
3. Click "Run All Tests"
4. Select your target.
5. See the results. 
