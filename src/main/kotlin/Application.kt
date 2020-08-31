import sudoku.Board
import sudoku.SudokuSolver
import kotlin.text.Charsets.UTF_8

class Application

fun main() = Application::class.java
    .getResourceAsStream("example/sudoku.txt")
    .readAllBytes()
    .toString(UTF_8)
    .let(::solveSudoku)


private fun solveSudoku(sudoku: String) {
    println("Sudoku to solve:")
    printLine()
    println(Board.fromString(sudoku))
    printLine()

    val solutions = SudokuSolver.solve(sudoku)

    println("${solutions.size} Solution${"s".takeIf { solutions.size != 1 } ?: ""} found:")
    printLine()

    solutions.forEachIndexed { index, board ->
        println("${index + 1}. Solution:")
        println(board)
        printLine()
    }
}

private fun printLine(size: Int = 17) = println("―".repeat(size))