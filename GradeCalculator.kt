fun main() {
    println("=== Student Grade Calculator ===")
    println("Grading Scale: 0-100%")
    print("Enter student name: ")
    val name = readLine()

    print("Enter grade for Subject 1 (0-100): ")
    val grade1 = readLine()!!.toDouble()

    print("Enter grade for Subject 2 (0-100): ")
    val grade2 = readLine()!!.toDouble()

    print("Enter grade for Subject 3 (0-100): ")
    val grade3 = readLine()!!.toDouble()

    // Validate grades
    if (grade1 < 0 || grade1 > 100 || grade2 < 0 || grade2 > 100 || grade3 < 0 || grade3 > 100) {
        println("Error: All grades must be between 0 and 100")
        return
    }

    val average = (grade1 + grade2 + grade3) / 3

    val letterGrade = when {
        average >= 80 -> "A"
        average >= 70 -> "B+"
        average >= 60 -> "B"
        average >= 55 -> "C+"
        average >= 50 -> "C"
        average >= 45 -> "D+"
        average >= 40 -> "D"
        else -> "F"
    }

    val gradePoints = when {
        average >= 80 -> 4.0
        average >= 70 -> 3.5
        average >= 60 -> 3.0
        average >= 55 -> 2.5
        average >= 50 -> 2.0
        average >= 45 -> 1.5
        average >= 40 -> 1.0
        else -> 0.0
    }

    println("\n╔═════════════════════════════════╗")
    println("║  GRADE CALCULATION RESULT       ║")
    println("╚═════════════════════════════════╝")
    println("Student: $name")
    println("Subject 1: $grade1%")
    println("Subject 2: $grade2%")
    println("Subject 3: $grade3%")
    println("─────────────────────────────────")
    println("Average Grade: ${String.format("%.2f", average)}%")
    println("Letter Grade: $letterGrade")
    println("Grade Points (GPA): $gradePoints")
    println("═════════════════════════════════")
}
