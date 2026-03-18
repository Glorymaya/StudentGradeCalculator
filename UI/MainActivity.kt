package com.studentgrade.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Using lambda function to set Compose content
        setContent {
            // Using scope function 'apply' to configure theme
            Surface(
                color = MaterialTheme.colorScheme.background
            ).apply {
                // Composable is set as content
            }

            // Display the Grade Calculator Screen
            GradeCalculatorScreen()
        }
    }
}
