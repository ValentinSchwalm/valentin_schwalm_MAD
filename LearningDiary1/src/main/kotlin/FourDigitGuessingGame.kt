import java.util.*
import kotlin.math.min

// Four-Digit Guessing Game
fun main(args: Array<String>) {

    // Delare random number + n + m
    var randomDigits = getRandomFourDigitNumber()
    var n = 0
    var m = 0

    // User manual
    println("Guess the random generated four-digit number!")
    println("--> Hint for testing purposes: $randomDigits $\n")

    // Game loop
    while (m < 4) {

        // Reset n and m
        n = 0
        m = 0

        // User Input
        print("User input: ")
        var input = readLine()?.toCharArray()

        // Detect duplicates
        var inputNoDuplicates = input?.distinct()

        // Calculate n
        for (number in inputNoDuplicates.toString()) {

            n += if (randomDigits.contains(number)) 1 else 0
        }

        // Calculate m
        for (i in 0 until min(input!!.size, randomDigits.length)) {

            m += if (input[i] == randomDigits[i]) 1 else 0
        }

        // Output
        println("$\b, Output: $n:$m")
    }

    // Winning log
    println("$\nCongrats you guessed the right number: $randomDigits")
}

// Creates a four-digit number with unique digits and returns it as a string
fun getRandomFourDigitNumber() : String {

    // Create new array
    var numbers = IntArray(10)

    // Fill array 1 - 10
    for (i in numbers.indices) {
        numbers[i] = i
    }

    // Shuffle array
    numbers.shuffle()

    // Return random four-digit number
    return numbers[0].toString() + numbers[1].toString() + numbers[2].toString() + numbers[3].toString()
}