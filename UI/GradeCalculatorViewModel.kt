package com.studentgrade.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Data class for UI state
data class GradeCalculatorState(
    val studentName: String = "",
    val grade1: String = "",
    val grade2: String = "",
    val grade3: String = "",
    val courseType: String = "C",
    val result: GradeResult? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)

class GradeCalculatorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GradeCalculatorState())
    val uiState: StateFlow<GradeCalculatorState> = _uiState.asStateFlow()

    // Lambda function to update student name
    fun updateStudentName(name: String) {
        _uiState.value = _uiState.value.copy(studentName = name)
    }

    // Lambda function to update grade 1
    fun updateGrade1(grade: String) {
        _uiState.value = _uiState.value.copy(grade1 = grade)
    }

    // Lambda function to update grade 2
    fun updateGrade2(grade: String) {
        _uiState.value = _uiState.value.copy(grade2 = grade)
    }

    // Lambda function to update grade 3
    fun updateGrade3(grade: String) {
        _uiState.value = _uiState.value.copy(grade3 = grade)
    }

    // Lambda function to update course type
    fun updateCourseType(courseType: String) {
        _uiState.value = _uiState.value.copy(courseType = courseType)
    }

    // Lambda function to calculate grades with scope functions
    fun calculateGrades() {
        try {
            // Using scope function 'with' to access current state
            with(_uiState.value) {
                // Validate inputs
                if (studentName.isEmpty() || grade1.isEmpty() || grade2.isEmpty() || grade3.isEmpty()) {
                    throw IllegalArgumentException("All fields are required")
                }

                // Using scope function 'run' to convert and process grades
                val grades = listOf(grade1, grade2, grade3).map { it.toDouble() }.run {
                    if (this.any { it < 0 || it > 100 }) {
                        throw IllegalArgumentException("Grades must be between 0 and 100")
                    }
                    this
                }

                // Using scope function 'apply' to build result
                val result = GradeResult(
                    name = studentName,
                    grade1 = grades[0],
                    grade2 = grades[1],
                    grade3 = grades[2],
                    average = grades.average(),
                    courseType = courseType,
                    letterGrade = "",
                    gradePoints = 0.0,
                    courseTypeLabel = ""
                ).apply {
                    // Lambda function for grade determination
                    letterGrade = determineLetterGrade(this.average)
                    gradePoints = determineGradePoints(this.average)
                    courseTypeLabel = determineCourseType(courseType)
                }

                // Update state with result
                _uiState.value = _uiState.value.copy(
                    result = result,
                    errorMessage = ""
                )
            }
        } catch (e: NumberFormatException) {
            // Using scope function 'let' to update error state
            _uiState.value.let {
                _uiState.value = it.copy(
                    errorMessage = "Please enter valid numbers (0-100) for all grades",
                    result = null
                )
            }
        } catch (e: IllegalArgumentException) {
            // Using scope function 'also' for side effect
            _uiState.value.also {
                _uiState.value = it.copy(
                    errorMessage = e.message ?: "Invalid input",
                    result = null
                )
            }
        }
    }

    // Lambda function to reset form
    fun resetForm() {
        _uiState.value = GradeCalculatorState()
    }

    // Lambda function for letter grade determination (0-100 scale)
    private fun determineLetterGrade(average: Double): String = when {
        average >= 80 -> "A"
        average >= 70 -> "B+"
        average >= 60 -> "B"
        average >= 55 -> "C+"
        average >= 50 -> "C"
        average >= 45 -> "D+"
        average >= 40 -> "D"
        else -> "F"
    }

    // Lambda function for grade points determination
    private fun determineGradePoints(average: Double): Double = when {
        average >= 80 -> 4.0
        average >= 70 -> 3.5
        average >= 60 -> 3.0
        average >= 55 -> 2.5
        average >= 50 -> 2.0
        average >= 45 -> 1.5
        average >= 40 -> 1.0
        else -> 0.0
    }

    // Lambda function for course type label
    private fun determineCourseType(courseType: String): String = when (courseType) {
        "C" -> "Compulsory"
        "E" -> "Elective"
        "R" -> "Required"
        "G" -> "University Requirement"
        else -> "Compulsory"
    }
}

// Data class to hold grade results
data class GradeResult(
    val name: String,
    val grade1: Double,
    val grade2: Double,
    val grade3: Double,
    val average: Double,
    val courseType: String,
    var letterGrade: String,
    var gradePoints: Double,
    var courseTypeLabel: String
)
