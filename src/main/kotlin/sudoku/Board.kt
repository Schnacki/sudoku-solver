package sudoku

import sudoku.Position.Vector

class Board(private val cells: Array<Array<Int?>>) {

    private fun cellsInRow(p: Vector) = cells[p.row].mapNotNull { it }

    private fun cellsInCol(p: Vector) = cells.mapNotNull { it[p.col] }

    private fun cellsInSquare(p: Vector) = cells.slice(p.row - p.row % 3..p.row - p.row % 3 + 2)
        .flatMap {
            it.slice(p.col - p.col % 3..p.col - p.col % 3 + 2).mapNotNull { it }
        }

    fun usableNumbers(p: Vector) =
        (1..9).filterNot { it in cellsInCol(p) || it in cellsInRow(p) || it in cellsInSquare(p) }

    fun hasValue(pos: Vector) = cells[pos.row][pos.col] != null

    fun copy() = Board(cells.map { it.clone() }.toTypedArray())

    fun <A> withValue(pos: Vector, value: Int, f: () -> A): A {
        cells[pos.row][pos.col] = value
        val res = f()
        cells[pos.row][pos.col] = null
        return res
    }

    override fun toString(): String =
        cells.joinToString(separator = "\n") { row ->
            row.joinToString(separator = " ") { it?.toString() ?: " " }
        }

    companion object {
        fun fromString(input: String) = Board(
            input
                .replace('.', '0')
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .map { it.takeIf { it != 0 } }
                .chunked(9)
                .map { it.toTypedArray() }
                .toTypedArray()
        )
    }
}