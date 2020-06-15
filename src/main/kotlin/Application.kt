import sudoku.Board
import sudoku.SudokuSolver
import kotlin.text.Charsets.UTF_8

class Application

fun main() {
    val sudokuFile = Application::class.java
        .getResourceAsStream("example/sudoku.txt")
        .readAllBytes()
        .toString(UTF_8)
    solveSudoku(sudokuFile)
}

private fun solveSudoku(sudokuFile: String) {
    println("Sudoku to solve:")
    println("―".repeat(17))
    println(Board.fromString(sudokuFile))
    println("―".repeat(17))

    val solutions = SudokuSolver.solve(sudokuFile)

    println("${solutions.size} Solution${"s".takeIf { solutions.size != 1 } ?: ""} found:")
    println("―".repeat(17))

    solutions.forEachIndexed { index, board ->
        println("${index + 1}. Solution:")
        println(board)
        println("―".repeat(17))
    }
}