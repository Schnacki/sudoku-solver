package sudoku

import sudoku.Position.Companion.INITIAL_POSITION

class SudokuSolver {
    companion object {
        private fun solve(board: Board): List<Board> {
            fun solve(pos: Position?): List<Board> = when {
                pos == null -> listOf(board.copy())
                board.hasValue(pos) -> solve(pos.nextPosition())
                else -> board.usableNumbers(pos).flatMap {
                    board.setValueAt(pos, it)
                    val res = solve(pos.nextPosition())
                    board.setValueAt(pos, null)
                    res
                }
            }
            return solve(INITIAL_POSITION)
        }

        fun solve(input: String): List<Board> = solve(Board.fromString(input))
    }
}