package com.example.gradecalculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etGrade1 = findViewById<EditText>(R.id.etGrade1)
        val etGrade2 = findViewById<EditText>(R.id.etGrade2)
        val etGrade3 = findViewById<EditText>(R.id.etGrade3)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val name = etName.text.toString()
            val grade1 = etGrade1.text.toString().toDoubleOrNull() ?: 0.0
            val grade2 = etGrade2.text.toString().toDoubleOrNull() ?: 0.0
            val grade3 = etGrade3.text.toString().toDoubleOrNull() ?: 0.0
            val average = (grade1 + grade2 + grade3) / 3
            val letterGrade = when {
                average >= 16 -> "A (Excellent)"
                average >= 14 -> "B (Good)"
                average >= 12 -> "C (Average)"
                average >= 10 -> "D (Pass)"
                else -> "F (Fail)"
            }
            tvResult.text = "Student: $name\nAverage: $average\nGrade: $letterGrade"
        }
    }
}