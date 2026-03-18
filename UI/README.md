# Grade Calculator UI - Jetpack Compose Implementation

This folder contains the Android UI implementation of the Student Grade Calculator using Jetpack Compose.

## Project Structure

- **GradeCalculatorScreen.kt** - The main Compose UI with input fields, buttons, and result display
- **MainActivity.kt** - Activity entry point that sets up the Compose content
- **GradeCalculatorViewModel.kt** - State management using ViewModel and StateFlow
- **GradeCalculationUtils.kt** - Utility functions and extension functions
- **HOW_TO_RUN.md** - Complete setup and execution guide

## Features

### Same Logic as Original Calculator
- Takes student name as input
- Accepts three subject grades (0-100%)
- Selectable course type (Compulsory, Elective, Required, University Requirement)
- Calculates average grade
- Determines letter grade and GPA points based on 4.0 scale

### Grading System (4.0 GPA Scale, 0-100%)

| Letter Grade | GPA Points | Percentage Range |
|:----------:|:----------:|:----------------:|
| A | 4.0 | 80-100% |
| B+ | 3.5 | 70-79% |
| B | 3.0 | 60-69% |
| C+ | 2.5 | 55-59% |
| C | 2.0 | 50-54% |
| D+ | 1.5 | 45-49% |
| D | 1.0 | 40-44% |
| F | 0.0 | 0-39% |

### Special Status Codes (All 0.0 GP)
- **W** - Withdrew
- **I** - Incomplete
- **X** - Absent from Exams
- **N** - No Credit

### Course Types
- **C** - Compulsory
- **E** - Elective
- **R** - Required
- **G** - University Requirement

### Lambda Functions Used
1. **Button onClick handlers** - Lambda expressions for Calculate and Reset buttons
2. **TextField onValueChange** - Lambda functions for state updates
3. **Grade calculation callbacks** - onSuccess and onError lambda functions
4. **Color mapping** - Lambda in when expression for indicator color selection
5. **ViewModel methods** - updateStudentName, updateGrade1-3, updateCourseType
6. **Utility lambda functions** - getLetterGrade, getGradePoints, parseGrades, etc.

### Scope Functions Used

#### `apply` - Configuration function
```kotlin
StringBuilder().apply {
    append("Student: ${gradeResult.name}\n")
    // ... configure object
}

GradeResult(...).apply {
    letterGrade = determineLetterGrade(this.average)
}
```

#### `run` - Transform and validate
```kotlin
val grades = listOf(g1, g2, g3).map { it.toDouble() }.run {
    if (this.any { it < 0 || it > 100 }) {
        throw IllegalArgumentException("Grades must be between 0 and 100")
    }
    this
}
```

#### `let` - Safe block execution
```kotlin
result?.let { gradeResult ->
    ResultCard(gradeResult = gradeResult)
}

errorMessage.let { ... }
```

#### `with` - Context on specific object
```kotlin
with(_uiState.value) {
    if (studentName.isEmpty()) { ... }
}
```

#### `also` - Side effects
```kotlin
_uiState.value.also {
    _uiState.value = it.copy(
        errorMessage = e.message ?: "Invalid input"
    )
}
```

## How to Integrate

### Dependencies (build.gradle)
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
}
```

### AndroidManifest.xml
```xml
<activity
    android:name=".ui.MainActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

## UI Components

- **OutlinedTextField** - Text input for name and grades (0-100)
- **Button** - Calculate button with validation
- **OutlinedButton** - Reset and Course Type buttons
- **DropdownMenu** - Course type selection
- **Card** - Display results and error messages
- **LinearProgressIndicator** - Visual grade representation (0-100%)
- **Text** - Labels and results display
- **Column/Row** - Layout management

## State Management

The ViewModel manages:
- Student input fields (name, grade1, grade2, grade3)
- Course type selection (C, E, R, G)
- Calculation results with GPA points
- Error messages
- Loading state

## Input Validation

- All fields must be filled
- Grades must be valid decimal numbers
- Grades must be between 0 and 100
- Error messages displayed in error container

## Result Display Format

The result card shows:
```
╔═══════════════════════════╗
║  GRADE RESULT SUMMARY     ║
╚═══════════════════════════╝
Student: John Doe
Subject 1: 85.0%
Subject 2: 92.0%
Subject 3: 78.0%
─────────────────────────────
Average: 85.00%
Letter Grade: A
Grade Points: 4.0
Course Type: Compulsory
```

## Testing Suggestions

1. Test with valid grades (e.g., 85, 92, 78)
2. Test with grade at boundaries (80, 79, 70, 69, 50, 49, 40, 39)
3. Test with invalid input (non-numeric values)
4. Test with out-of-range grades (e.g., 101, -5)
5. Test with empty fields
6. Test reset functionality
7. Test different course types
8. Test special status codes (if implemented)

## Running the Application

See [HOW_TO_RUN.md](HOW_TO_RUN.md) for complete setup and execution instructions.

## Key Differences from Original Calculator

- **Scale Changed**: From 0-20 to 0-100% (percentage-based)
- **Grading System**: Now uses standard 4.0 GPA scale
- **More Grade Options**: 8 letter grades (A, B+, B, C+, C, D+, D, F) instead of 5
- **Course Types**: Added course type classification (Compulsory, Elective, Required, University Requirement)
- **Grade Points**: Includes GPA calculation alongside letter grades
- **Better UI**: Modern Jetpack Compose interface with dropdowns and progress indicators

## Technical Details

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **State Management**: ViewModel + StateFlow
- **Validation**: Client-side with error handling
- **Architecture**: MVVM with separated concerns

