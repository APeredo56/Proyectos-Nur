package com.elements

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Board(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var game: Game? = null
    val objPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var yStart = 0f
        val squareWidth = width / 4f
        game?.matrix?.forEachIndexed { i, rows ->
            var xStart = 0f

            rows.forEachIndexed { colJ, rowI ->
                val cell = rows[colJ]
                objPaint.color = cell.background
                canvas?.drawRect(
                    xStart + cell.padding,
                    yStart + cell.padding,
                    xStart + squareWidth - cell.padding,
                    yStart + squareWidth - cell.padding,
                    objPaint
                )
                objPaint.color = Color.BLACK
                objPaint.textSize = 110f
                if (cell.value != 0) {
                    val bounds = cell.textBounds
                    objPaint.getTextBounds(cell.value.toString(), 0, cell.value.toString().length, bounds)
                    val x = xStart + squareWidth / 2f - bounds.width() / 2f
                    val y = yStart + squareWidth / 2f + bounds.height() / 2f
                    canvas?.drawText(cell.value.toString(), x, y, objPaint)
                }
                xStart += squareWidth
            }
            yStart += squareWidth
        }
    }
}