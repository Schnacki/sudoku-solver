package sudoku

data class Position(val col: Int, val row: Int) {
    fun nextPosition() =
        Position(
            col = (col + 1) % 9,
            row = row + col / 8
        ).takeIf { it.row < 9 }

    companion object {
        val INITIAL_POSITION = Position(0, 0)
    }
}