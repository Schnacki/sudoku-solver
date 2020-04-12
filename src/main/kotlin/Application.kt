import sudoku.SudokuSolver
import kotlin.text.Charsets.UTF_8

class Application

fun main() {
    val sudoku = Application::class.java.getResourceAsStream("example/sudoku.txt").readAllBytes().toString(UTF_8)
    val solutions = SudokuSolver.solve(sudoku)
    println("${solutions.size} Solution${"s".takeIf { solutions.size != 1 } ?: ""} found:")
    solutions.forEachIndexed { index, board ->
        println("${index + 1}. Solution:")
        println(board)
        println("―".repeat(17))
    }
}