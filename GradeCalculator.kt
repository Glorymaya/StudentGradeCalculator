fun main() {
    println("=== Student Grade Calculator ===")
    print("Enter student name: ")
    val name = readLine()

    print("Enter grade for Subject 1: ")
    val grade1 = readLine()!!.toDouble()

    print("Enter grade for Subject 2: ")
    val grade2 = readLine()!!.toDouble()

    print("Enter grade for Subject 3: ")
    val grade3 = readLine()!!.toDouble()

    val average = (grade1 + grade2 + grade3) / 3

    val letterGrade = when {
        average >= 16 -> "A (Excellent)"
        average >= 14 -> "B (Good)"
        average >= 12 -> "C (Average)"
        average >= 10 -> "D (Pass)"
        else -> "F (Fail)"
    }

    println("Student: " + name)
    println("Average Grade: " + average)
    println("Letter Grade: " + letterGrade)
}
