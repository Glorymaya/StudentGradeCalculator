package com.studentgrade.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable

// Extension function using scope functions for composable utilities
@Composable
fun ScrollState.rememberScrollState(): ScrollState = this.apply {
    // Configuration for scroll state
}

// Utility object with lambda functions for grade operations
object GradeCalculationUtils {
    
    // Lambda function to validate grades (0-100 scale)
    val validateGrades: (List<Double>) -> Boolean = { grades ->
        grades.all { it in 0.0..100.0 }
    }

    // Lambda function to calculate average
    val calculateAverage: (List<Double>) -> Double = { grades ->
        grades.average()
    }

    // Lambda function to determine letter grade (0-100 scale)
    val getLetterGrade: (Double) -> String = { average ->
        when {
            average >= 80 -> "A"
            average >= 70 -> "B+"
            average >= 60 -> "B"
            average >= 55 -> "C+"
            average >= 50 -> "C"
            average >= 45 -> "D+"
            average >= 40 -> "D"
            else -> "F"
        }
    }

    // Lambda function to determine grade points (GPA)
    val getGradePoints: (Double) -> Double = { average ->
        when {
            average >= 80 -> 4.0
            average >= 70 -> 3.5
            average >= 60 -> 3.0
            average >= 55 -> 2.5
            average >= 50 -> 2.0
            average >= 45 -> 1.5
            average >= 40 -> 1.0
            else -> 0.0
        }
    }

    // Lambda function to parse grades from strings
    val parseGrades: (List<String>) -> Result<List<Double>> = { gradeStrings ->
        try {
            Result.success(gradeStrings.map { it.toDouble() })
        } catch (e: NumberFormatException) {
            Result.failure(e)
        }
    }

    // Lambda function for course type label
    val getCourseTypeLabel: (String) -> String = { courseType ->
        when (courseType) {
            "C" -> "Compulsory"
            "E" -> "Elective"
            "R" -> "Required"
            "G" -> "University Requirement"
            else -> "Compulsory"
        }
    }

    // Scope function using 'apply' to build formatted result string
    fun formatGradeResult(gradeResult: GradeResult): String = StringBuilder().apply {
        append("╔═════════════════════════════════╗\n")
        append("║  GRADE CALCULATION RESULT       ║\n")
        append("╚═════════════════════════════════╝\n")
        append("Student Name: ${gradeResult.name}\n")
        append("Subject 1: ${String.format("%.1f", gradeResult.grade1)}%\n")
        append("Subject 2: ${String.format("%.1f", gradeResult.grade2)}%\n")
        append("Subject 3: ${String.format("%.1f", gradeResult.grade3)}%\n")
        append("─────────────────────────────────\n")
        append("Average: ${String.format("%.2f", gradeResult.average)}%\n")
        append("Letter Grade: ${gradeResult.letterGrade}\n")
        append("Grade Points (GPA): ${gradeResult.gradePoints}\n")
        append("Course Type: ${gradeResult.courseTypeLabel}\n")
        append("═════════════════════════════════\n")
    }.toString()

    // Extension function with lambda to apply transformations
    inline fun <T, R> List<T>.transformAndValidate(
        transform: (T) -> R,
        validate: (List<R>) -> Boolean
    ): Result<List<R>> = try {
        val transformed = this.map(transform) // Using lambda parameter
        if (validate(transformed)) {
            Result.success(transformed)
        } else {
            Result.failure(IllegalArgumentException("Validation failed: Grades must be between 0-100"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    // Lambda function for grading category assessment
    val getGradeCategory: (Double) -> String = { gpa ->
        when {
            gpa >= 3.5 -> "EXCELLENT"
            gpa >= 3.0 -> "VERY GOOD"
            gpa >= 2.5 -> "GOOD"
            gpa >= 2.0 -> "SATISFACTORY"
            gpa >= 1.5 -> "ACCEPTABLE"
            gpa >= 1.0 -> "PASSING"
            else -> "FAILING"
        }
    }

    // Lambda function to determine special status codes
    val getSpecialStatus: (String) -> String? = { statusCode ->
        when (statusCode) {
            "W" -> "Withdrew"
            "I" -> "Incomplete"
            "X" -> "Absent from Exams"
            "N" -> "No Credit"
            else -> null
        }
    }
}

// Extension function using scope function 'let' for safe operations
fun <T> T?.ifNotNull(action: (T) -> Unit) {
    this?.let(action)
}

// Extension function using scope function 'run' for property setting
inline fun <T> T.configureAndReturn(configure: T.() -> Unit): T = this.run {
    configure()
    this
}

// Extension function for formatting GPA display
fun Double.formatGPA(): String = String.format("%.2f", this)

// Extension function for grade range description
fun Double.getGradeRange(): String = when {
    this >= 80 -> "90-100%"
    this >= 70 -> "70-79%"
    this >= 60 -> "60-69%"
    this >= 55 -> "55-59%"
    this >= 50 -> "50-54%"
    this >= 45 -> "45-49%"
    this >= 40 -> "40-44%"
    else -> "0-39%"
}
