import java.util.*
import kotlin.random.Random

val words = listOf("mikuru", "kai", "ren", "yutaka", "gustavo", "satoshi", "kleber", "majima")
var word = ""
val guesses = arrayListOf<Char>()
var remainingGuesses = 6

fun main() = setupGame()

fun setupGame() {
    val wordIndex = Random.nextInt(words.size)
    word = words[wordIndex].uppercase(Locale.getDefault())

    for (i in word.indices)
        guesses.add('_')

    var gameOver = false
    do {
        printGameStatus()
        println("Enter a letter:")

        val input = readln()
        if (input.isEmpty()) {
            println("That's not a valid input. Try again")
            continue
        }

        val letter = input[0].uppercaseChar()
        if (!word.contains(letter)) {
            println("That's not part of the word")
            remainingGuesses--
            if (remainingGuesses == 0)
                gameOver = true
            continue
        }

        for (i in word.indices) {
            if (word[i] == letter)
                guesses[i] = letter
        }
        if (!guesses.contains('_'))
            gameOver = true
    } while (!gameOver)

    if (remainingGuesses == 0) {
        printGameStatus()
        println("You lost. The word was $word")
    } else
        println("\nCongratulations, you win!\nThe word was $word")
}

fun printGameStatus() {
    printMistakes(remainingGuesses)
    print("Word: ")
    for (element in guesses) print("$element ")
    println("\nYou have $remainingGuesses guess(es) left")
}

fun printMistakes(remaining: Int) {
    println("  |------|-")
    println("  |      | ")
    println("  |      " + if (remaining < 6) "o" else " ")
    println(
        "  |     " + when (remaining) {
            4 -> " | "
            3 -> "/| "
            2, 1, 0 -> "/|\\"
            else -> "   "
        }
    )
    println("  |      " + if (remaining < 5) "|" else " ")
    println("  |     " + if (remaining == 1) "/" else if (remaining == 0) "/ \\" else "   ")
    println(" /|\\      ")
    println("/ | \\     ")
}