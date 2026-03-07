fun main() {
    val words = listOf("apple", "cat", "banana", "dog", "elephant")

    val wordMap = words.associateWith { it.length }

    wordMap.filter { it.value > 4 }
           .forEach { println("${it.key} has length ${it.value}") }
}
