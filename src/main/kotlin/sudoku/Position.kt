package sudoku

sealed class Position {
    data class Vector(
        val col: Int,
        val row: Int
    ) : Position() {
        fun next() =
            Vector(
                col = (col + 1) % 9,
                row = row + col / 8
            ).takeIf { it.row < 9 } ?: LastPosition

        companion object {
            val INITIAL_POSITION = Vector(0, 0)
        }
    }

    object LastPosition : Position()
}
