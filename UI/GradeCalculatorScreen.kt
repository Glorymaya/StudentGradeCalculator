package com.studentgrade.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradeCalculatorScreen() {
    var studentName by remember { mutableStateOf("") }
    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }
    var courseType by remember { mutableStateOf("C") }
    var result by remember { mutableStateOf<GradeResult?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var expandedCourseType by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Student Grade Calculator",
            fontSize = 28.sp,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "GPA Scale: 0-100%",
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 32.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Student Name Input
        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Student Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Grade 1 Input
        OutlinedTextField(
            value = grade1,
            onValueChange = { grade1 = it },
            label = { Text("Grade for Subject 1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Grade 2 Input
        OutlinedTextField(
            value = grade2,
            onValueChange = { grade2 = it },
            label = { Text("Grade for Subject 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Grade 3 Input
        OutlinedTextField(
            value = grade3,
            onValueChange = { grade3 = it },
            label = { Text("Grade for Subject 3 (0-100)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Course Type Dropdown
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)) {
            OutlinedButton(
                onClick = { expandedCourseType = !expandedCourseType },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Course Type: $courseType")
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Course Type",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            DropdownMenu(
                expanded = expandedCourseType,
                onDismissRequest = { expandedCourseType = false },
                modifier = Modifier.fillMaxWidth(0.95f)
            ) {
                listOf(
                    "C" to "Compulsory",
                    "E" to "Elective",
                    "R" to "Required",
                    "G" to "University Requirement"
                ).forEach { (code, label) ->
                    DropdownMenuItem(
                        text = { Text("$code - $label") },
                        onClick = {
                            courseType = code
                            expandedCourseType = false
                        }
                    )
                }
            }
        }

        // Calculate Button with Lambda function
        Button(
            onClick = {
                calculateGrade(
                    name = studentName,
                    g1 = grade1,
                    g2 = grade2,
                    g3 = grade3,
                    courseType = courseType,
                    onSuccess = { gradeResult ->
                        result = gradeResult
                        errorMessage = ""
                    },
                    onError = { error ->
                        errorMessage = error
                        result = null
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = studentName.isNotEmpty() && grade1.isNotEmpty() && grade2.isNotEmpty() && grade3.isNotEmpty()
        ) {
            Text("Calculate Grade", fontSize = 16.sp)
        }

        // Reset Button with Lambda function
        OutlinedButton(
            onClick = {
                studentName = ""
                grade1 = ""
                grade2 = ""
                grade3 = ""
                courseType = "C"
                result = null
                errorMessage = ""
                expandedCourseType = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(top = 8.dp)
        ) {
            Text("Reset", fontSize = 16.sp)
        }

        // Error Message Display
        if (errorMessage.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Result Display with Scope Functions
        result?.let { gradeResult ->
            ResultCard(gradeResult = gradeResult)
        }
    }
}

@Composable
private fun ResultCard(gradeResult: GradeResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Using scope function 'apply' to build result text
            val resultText = StringBuilder().apply {
                append("╔═══════════════════════════╗\n")
                append("║  GRADE RESULT SUMMARY     ║\n")
                append("╚═══════════════════════════╝\n")
                append("Student: ${gradeResult.name}\n")
                append("Subject 1: ${String.format("%.1f", gradeResult.grade1)}%\n")
                append("Subject 2: ${String.format("%.1f", gradeResult.grade2)}%\n")
                append("Subject 3: ${String.format("%.1f", gradeResult.grade3)}%\n")
                append("─────────────────────────────\n")
                append("Average: ${String.format("%.2f", gradeResult.average)}%\n")
                append("Letter Grade: ${gradeResult.letterGrade}\n")
                append("Grade Points: ${gradeResult.gradePoints}\n")
                append("Course Type: ${gradeResult.courseTypeLabel}\n")
            }.toString()

            Text(
                text = resultText,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 12.dp),
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
            )

            // Grade indicator with lambda color mapping
            val indicatorColor = when {
                gradeResult.average >= 80 -> androidx.compose.material3.Green
                gradeResult.average >= 70 -> androidx.compose.material3.Blue
                gradeResult.average >= 60 -> androidx.compose.material3.Yellow
                gradeResult.average >= 45 -> androidx.compose.material3.Cyan
                else -> androidx.compose.material3.Red
            }

            LinearProgressIndicator(
                progress = { (gradeResult.average / 100).toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = indicatorColor,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

// Lambda function for grade calculation with scope functions
private fun calculateGrade(
    name: String,
    g1: String,
    g2: String,
    g3: String,
    courseType: String,
    onSuccess: (GradeResult) -> Unit,
    onError: (String) -> Unit
) {
    try {
        // Using scope function 'run' to convert and validate grades
        val grades = listOf(g1, g2, g3).map { it.toDouble() }.run {
            if (this.any { it < 0 || it > 100 }) {
                throw IllegalArgumentException("Grades must be between 0 and 100")
            }
            this
        }

        // Using scope function 'apply' to create and configure the result
        val result = GradeResult(
            name = name,
            grade1 = grades[0],
            grade2 = grades[1],
            grade3 = grades[2],
            average = grades.average(),
            courseType = courseType,
            letterGrade = "",
            gradePoints = 0.0,
            courseTypeLabel = ""
        ).apply {
            letterGrade = when {
                average >= 80 -> "A"
                average >= 70 -> "B+"
                average >= 60 -> "B"
                average >= 55 -> "C+"
                average >= 50 -> "C"
                average >= 45 -> "D+"
                average >= 40 -> "D"
                else -> "F"
            }
            
            gradePoints = when {
                average >= 80 -> 4.0
                average >= 70 -> 3.5
                average >= 60 -> 3.0
                average >= 55 -> 2.5
                average >= 50 -> 2.0
                average >= 45 -> 1.5
                average >= 40 -> 1.0
                else -> 0.0
            }
            
            courseTypeLabel = when (courseType) {
                "C" -> "Compulsory"
                "E" -> "Elective"
                "R" -> "Required"
                "G" -> "University Requirement"
                else -> "Compulsory"
            }
        }

        onSuccess(result)
    } catch (e: NumberFormatException) {
        onError("Please enter valid numbers (0-100) for all grades")
    } catch (e: IllegalArgumentException) {
        onError(e.message ?: "Invalid input")
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
