# How to Run the Grade Calculator UI

## Prerequisites

- Android Studio (latest version recommended)
- Android SDK 24+
- Kotlin 1.8+
- Java Development Kit (JDK) 11+

## Project Setup Steps

### 1. Clone or Import the Project
```bash
# If using Git
git clone https://github.com/Androit-development/StudentGradeCalculator.git
cd StudentGradeCalculator
```

### 2. Open in Android Studio
1. Open Android Studio
2. Click **File** → **Open**
3. Navigate to the project directory
4. Select the root folder and click **OK**
5. Wait for Gradle sync to complete

### 3. Configure Gradle Build File

In your project's `build.gradle` (Module: app), add these dependencies:

```gradle
dependencies {
    // Jetpack Compose
    implementation 'androidx.activity:activity-compose:1.8.0'
    implementation 'androidx.compose.ui:ui:1.6.0'
    implementation 'androidx.compose.material3:material3:1.1.0'
    implementation 'androidx.compose.foundation:foundation:1.6.0'
    
    // Lifecycle
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1'
    
    // Testing
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.6.0'
    debugImplementation 'androidx.compose.ui:ui-tooling:1.6.0'
}
```

Add to `build.gradle` (Project level):
```gradle
plugins {
    id 'com.android.application' version '8.0.0'
    id 'org.jetbrains.kotlin.android' version '1.9.0'
}
```

### 4. Configure AndroidManifest.xml

In `src/main/AndroidManifest.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.studentgrade.ui">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.App">

        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:theme="@style/Theme.App">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>
```

### 5. Create Theme Resources

In `res/values/themes.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="Theme.App" parent="Theme.Material3.DynamicColorsDark">
        <!-- Customize as needed -->
    </style>
</resources>
```

## Running the Application

### Option 1: Using Android Studio

1. **Connect a Device or Start Emulator**
   - Physical Device: Connect via USB, enable Developer Mode
   - Emulator: Android Studio > Tools > AVD Manager > Create/Start virtual device

2. **Run the App**
   - Click the **Run** button (green play icon) in the toolbar
   - Select your device/emulator
   - Wait for the app to build and deploy

3. **Build Variants**
   - You can switch between Debug/Release variants
   - Select **Build** > **Select Build Variant**

### Option 2: Using Command Line

```bash
# Navigate to project root
cd StudentGradeCalculator

# Build the project
./gradlew build

# Run on connected device/emulator
./gradlew installDebug

# Run with one command
./gradlew run
```

### Option 3: Generate APK

```bash
# Build debug APK
./gradlew assembleDebug
# APK location: app/build/outputs/apk/debug/

# Build release APK (requires signing configuration)
./gradlew assembleRelease
```

## Using the Application

### Input Screen
1. **Enter Student Name** - Type the student's full name
2. **Enter Grades for 3 Subjects** - Enter numeric grades (0-100%)
3. **Select Course Type** (Optional)
   - C: Compulsory
   - E: Elective
   - R: Required
   - G: University Requirement

### Calculate Button
- Calculates average of three grades
- Determines letter grade based on GPA scale:
  - **A**: 4.0 GP (80-100%)
  - **B+**: 3.5 GP (70-79%)
  - **B**: 3.0 GP (60-69%)
  - **C+**: 2.5 GP (55-59%)
  - **C**: 2.0 GP (50-54%)
  - **D+**: 1.5 GP (45-49%)
  - **D**: 1.0 GP (40-44%)
  - **F**: 0.0 GP (0-39%)

- Special Status Codes:
  - **W**: Withdrew (0.0 GP)
  - **I**: Incomplete (0.0 GP)
  - **X**: Absent from Exams (0.0 GP)
  - **N**: No Credit (0.0 GP)

### Reset Button
- Clears all fields
- Ready for new calculation

### Result Display
Shows:
- Student name
- All three grades
- Average percentage
- Letter grade with GP
- Course type
- Grade category

## Troubleshooting

### Build Issues

**Gradle sync fails**
- File > Invalidate Caches > Invalidate and Restart
- Delete `.gradle` folder and re-sync

**Compilation errors**
- Ensure Kotlin plugin is installed
- Update Android Studio to latest version
- Check Java/JDK compatibility

### Runtime Issues

**App crashes on launch**
- Check AndroidManifest.xml configuration
- Ensure minSdkVersion is 24 or higher
- Check logcat for detailed errors

**Emulator not found**
- Open AVD Manager (Tools > AVD Manager)
- Create and start a virtual device
- Ensure Intel HAXM or Hyper-V is enabled

### Input Validation Errors

**Invalid grade input**
- Grades must be between 0-100
- Use numeric values only (decimals allowed)
- All three grades are required

**Missing student name**
- Calculate button is disabled without name
- Enter student name before calculating

## Development Tips

1. **Hot Reload** - Make code changes and use Run to hot reload
2. **Layout Preview** - Use Compose Preview to see UI without running
3. **Debugging** - Use Android Studio debugger to step through code
4. **Logcat** - Monitor logs in Logcat window for errors/debug output

## File Structure

```
StudentGradeCalculator/
├── UI/
│   ├── MainActivity.kt              # Entry point
│   ├── GradeCalculatorScreen.kt     # UI composables
│   ├── GradeCalculatorViewModel.kt  # State management
│   ├── GradeCalculationUtils.kt     # Utility functions
│   ├── HOW_TO_RUN.md               # This file
│   └── README.md                    # Feature documentation
└── [other files]
```

## Key Classes

- **MainActivity** - Activity that sets up Compose
- **GradeCalculatorScreen** - Main UI composable
- **GradeCalculatorViewModel** - Manages state and calculations
- **GradeResult** - Data class for results
- **GradeCalculationUtils** - Helper functions for grade calculations

## Testing

### Manual Testing Checklist
- [ ] Valid grades (e.g., 85, 92, 78)
- [ ] Grade at boundaries (80, 79, 50, 39)
- [ ] Invalid input (non-numeric, negative)
- [ ] Out of range (0, 100, beyond)
- [ ] Empty fields
- [ ] Reset functionality
- [ ] Different course types

## Building for Production

1. Create a keystore file (for signing)
2. Configure signing in `build.gradle`
3. Run `./gradlew assembleRelease`
4. Upload APK to Google Play or distribute directly

## Additional Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Studio Setup Guide](https://developer.android.com/studio/intro)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Material Design 3](https://m3.material.io/)

---

**Happy Coding!** 🚀
