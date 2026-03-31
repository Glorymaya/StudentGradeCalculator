data class SmartBin(
    val id: Int,
    val location: String,
    val fillLevel: Int
)

fun main() {
    println("=== WasteWatch - Smart Waste Management System ===")
    println("HYSACAM - Yaoundé, Cameroon")
    println("==================================================")

    val bins = listOf(
        SmartBin(1, "Bastos", 90),
        SmartBin(2, "Nlongkak", 45),
        SmartBin(3, "Melen", 85),
        SmartBin(4, "Mvog-Mbi", 20),
        SmartBin(5, "Essos", 95),
        SmartBin(6, "Ekounou", 60)
    )

    println("\nAll Bins Status:")
    bins.forEach { bin ->
        println("Bin ${bin.id} - ${bin.location}: ${bin.fillLevel}% full")
    }

    println("\nBins that need collection (above 80%):")
    bins.filter { it.fillLevel > 80 }
        .forEach { bin ->
            println("Bin ${bin.id} - ${bin.location}: ${bin.fillLevel}% full - NEEDS COLLECTION!")
        }

    val averageFill = bins.map { it.fillLevel }.average()
    println("\nAverage fill level: ${"%.1f".format(averageFill)}%")
}
