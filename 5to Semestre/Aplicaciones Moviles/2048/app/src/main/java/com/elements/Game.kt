package com.elements

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import com.utilities.Direction

class Game(var context: Context, val gameListener: GameListener) {

    lateinit var matrix: Array<Array<Cell>>
    var moves = false
    var win = false
    var score = 0

    init {
        makeNewGame()
    }

    fun makeNewGame() {
        matrix = Array(4) {
            arrayOf(
                Cell(Color.parseColor("#B6B3B3"), 0, false, Rect()),
                Cell(Color.parseColor("#B6B3B3"), 0, false, Rect()),
                Cell(Color.parseColor("#B6B3B3"), 0, false, Rect()),
                Cell(Color.parseColor("#B6B3B3"), 0, false, Rect())
            )
        }
        moves = false
        win = false
        score = 0
        generateRandomNumber()
        generateRandomNumber()
//        matrix[0][0].value = 1024
////        matrix[0][1].value = 1024

    }

    private fun generateRandomNumber() {
        while (true) {
            val random = (0..3).random()
            val random2 = (0..3).random()
            if (matrix[random][random2].value == 0) {
                matrix[random][random2].value = 2
                changeBackgrounds(matrix[random][random2], 2)
                break
            }
        }
    }

    fun move(direction: Direction) {
        when (direction) {
            Direction.UP -> {
                for (row in 1..3) {
                    for (column in 0..3) {
                        if (matrix[row][column].value == 0) {
                            continue
                        }
                        if (!moves) moves = moveCellUp(row, column) else moveCellUp(row, column)
                    }
                }
            }
            Direction.DOWN -> {
                for (row in 2 downTo 0) {
                    for (column in 3 downTo 0) {
                        if (matrix[row][column].value == 0) {
                            continue
                        }
                        if (!moves) moves = moveCellDown(row, column) else moveCellDown(row, column)
                    }
                }
            }
            Direction.RIGHT -> {
                for (row in 3 downTo 0) {
                    for (column in 2 downTo 0) {
                        if (matrix[row][column].value == 0) {
                            continue
                        }
                        if (!moves) moves = moveCellRight(row, column) else moveCellRight(
                            row,
                            column
                        )
                    }
                }
            }
            Direction.LEFT -> {
                for (row in 0..3) {
                    for (column in 1..3) {
                        if (matrix[row][column].value == 0) {
                            continue
                        }
                        if (!moves) moves = moveCellLeft(row, column) else moveCellLeft(row, column)
                    }
                }
            }
        }
        if (moves) {
            generateRandomNumber()
            moves = false
        }
        if (!validateMovements()) {
            finishGame()
        }
        clearMergedCells()
    }


    private fun moveCellUp(rowIndex: Int, columnIndex: Int): Boolean {
        var actualRow = rowIndex
        var actualCell = matrix[rowIndex][columnIndex]
        while (actualRow > 0) {
            val upCell = matrix[actualRow - 1][columnIndex]
            if (upCell.value == 0) {
                upCell.value = actualCell.value
                actualCell.value = 0
                changeBackgrounds(upCell, upCell.value)
                changeBackgrounds(actualCell, 0)
            } else {
                return mergeCells(upCell, actualCell)
            }
            actualCell = upCell
            actualRow--
        }
        return true
    }

    private fun moveCellDown(rowIndex: Int, columnIndex: Int): Boolean {
        var actualRow = rowIndex
        var actualCell = matrix[rowIndex][columnIndex]
        while (actualRow < 3) {
            val bottomCell = matrix[actualRow + 1][columnIndex]
            if (bottomCell.value == 0) {
                bottomCell.value = actualCell.value
                actualCell.value = 0
                changeBackgrounds(bottomCell, bottomCell.value)
                changeBackgrounds(actualCell, 0)
            } else {
                return mergeCells(bottomCell, actualCell)
            }
            actualCell = bottomCell
            actualRow++
        }
        return true
    }

    private fun moveCellRight(rowIndex: Int, columnIndex: Int): Boolean {
        var actualColumn = columnIndex
        var actualCell = matrix[rowIndex][columnIndex]
        while (actualColumn < 3) {
            val rightCell = matrix[rowIndex][actualColumn + 1]
            if (rightCell.value == 0) {
                rightCell.value = actualCell.value
                actualCell.value = 0
                changeBackgrounds(rightCell, rightCell.value)
                changeBackgrounds(actualCell, 0)
            } else {
                return mergeCells(rightCell, actualCell)
            }
            actualCell = rightCell
            actualColumn++
        }
        return true
    }

    private fun moveCellLeft(rowIndex: Int, columnIndex: Int): Boolean {
        var actualColumn = columnIndex
        var actualCell = matrix[rowIndex][columnIndex]
        while (actualColumn > 0) {
            val leftCell = matrix[rowIndex][actualColumn - 1]
            if (leftCell.value == 0) {
                leftCell.value = actualCell.value
                actualCell.value = 0
                changeBackgrounds(leftCell, leftCell.value)
                changeBackgrounds(actualCell, 0)
            } else {
                return mergeCells(leftCell, actualCell)
            }
            actualCell = leftCell
            actualColumn--
        }
        return true
    }

    private fun mergeCells(cellWithValue: Cell, emptyCell: Cell): Boolean {
        if (cellWithValue.value != emptyCell.value) {
            return false
        }
        if (cellWithValue.merged) {
            cellWithValue.merged = false
            return false
        }
        cellWithValue.value = cellWithValue.value + emptyCell.value
        cellWithValue.merged = true
        emptyCell.value = 0
        changeBackgrounds(cellWithValue, cellWithValue.value)
        changeBackgrounds(emptyCell, 0)
        score += cellWithValue.value

        if (cellWithValue.value == 2048) {
            win = true
            finishGame()
            return true
        }
        return true
    }

    private fun changeBackgrounds(cell: Cell, value: Int) {
        when (value) {
            0 -> cell.background = Color.parseColor("#B6B3B3")
            2 -> cell.background = Color.parseColor("#eee7dc")
            4 -> cell.background = Color.parseColor("#ece0c9")
            8 -> cell.background = Color.parseColor("#efb279")
            16 -> cell.background = Color.parseColor("#f59765")
            32 -> cell.background = Color.parseColor("#f27d63")
            64 -> cell.background = Color.parseColor("#f46143")
            128 -> cell.background = Color.parseColor("#eacf76")
            256 -> cell.background = Color.parseColor("#ebcc67")
            512 -> cell.background = Color.parseColor("#e8c258")
            1024 -> cell.background = Color.parseColor("#e8c356")
            2048 -> cell.background = Color.parseColor("#e6bd4e")
        }
    }

    private fun validateMovements(): Boolean {
        for (i in 0..3) {
            for (j in 0..3) {
                val actualCell = matrix[i][j]
                if (actualCell.value == 0) {
                    return true
                }
                var upperCell: Cell? = null
                var bottomCell: Cell? = null
                var rightCell: Cell? = null
                var leftCell: Cell? = null

                if (i > 0) upperCell = matrix[i - 1][j]
                if (i < 3) bottomCell = matrix[i + 1][j]
                if (j > 0) leftCell = matrix[i][j - 1]
                if (j < 3) rightCell = matrix[i][j + 1]

                if (upperCell != null && upperCell.value == actualCell.value) {
                    return true
                } else if (bottomCell != null && bottomCell.value == actualCell.value) {
                    return true
                } else if (rightCell != null && rightCell.value == actualCell.value) {
                    return true
                } else if (leftCell != null && leftCell.value == actualCell.value) {
                    return true
                }
            }
        }
        return false
    }

    private fun finishGame() {
        if (win) {
            showDialog("Ganaste")
        } else {
            showDialog("Perdiste")
        }
    }

    private fun showDialog(title: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage("Tu puntuación final fue: $score")
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            makeNewGame()
            gameListener.onFinish()
        }
        builder.show()
    }

    private fun clearMergedCells() {
        for (i in 0..3) {
            for (j in 0..3) {
                matrix[i][j].merged = false
            }
        }

    }

    interface GameListener{
        fun onFinish()
    }
}