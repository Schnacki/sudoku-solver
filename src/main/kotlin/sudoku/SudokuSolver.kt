package sudoku

import sudoku.Position.LastPosition
import sudoku.Position.Vector
import sudoku.Position.Vector.Companion.INITIAL_POSITION

object SudokuSolver {
    private fun solve(board: Board): List<Board> {
        fun solve(pos: Position): List<Board> = when (pos) {
            is LastPosition -> listOf(board.copy())
            is Vector -> {
                if (board hasValue pos) solve(pos.next())
                else {
                    board.usableNumbers(pos).flatMap {
                        board.withValue(pos, it) {
                            solve(pos.next())
                        }
                    }
                }
            }
        }
        return solve(INITIAL_POSITION)
    }

    fun solve(input: String): List<Board> = solve(Board.fromString(input))
}