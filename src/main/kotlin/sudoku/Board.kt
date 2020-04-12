package sudoku

class Board(private val cells: Array<Array<Int?>>) {

    private fun cellsInRow(p: Position) = cells[p.row].mapNotNull { it }

    private fun cellsInCol(p: Position) = cells.mapNotNull { it[p.col] }

    private fun cellsInSquare(p: Position) = cells.slice(p.row - p.row % 3..p.row - p.row % 3 + 2)
        .flatMap {
            it.slice(p.col - p.col % 3..p.col - p.col % 3 + 2).mapNotNull { it }
        }

    fun usableNumbers(p: Position) =
        (1..9).filterNot { it in cellsInCol(p) || it in cellsInRow(p) || it in cellsInSquare(p) }

    fun setValueAt(pos: Position, value: Int?) {
        cells[pos.row][pos.col] = value
    }

    fun hasValue(pos: Position) = cells[pos.row][pos.col] != null

    fun copy() = Board(cells.map { it.clone() }.toTypedArray())

    override fun toString(): String =
        cells.joinToString(separator = "\n") { row ->
            row.joinToString(separator = " ") { it?.toString() ?: " " }
        }

    companion object {
        fun fromString(input: String) = Board(
            input
                .replace('.', '0')
                .filter(Character::isDigit)
                .map { (it - '0').takeIf { it != 0 } }
                .chunked(9)
                .map { it.toTypedArray() }
                .toTypedArray()
        )
    }
}