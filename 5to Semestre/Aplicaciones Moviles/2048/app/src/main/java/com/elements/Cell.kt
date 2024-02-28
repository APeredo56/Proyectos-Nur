package com.elements

import android.graphics.Rect

data class Cell(
    var background: Int,
    var value: Int,
    var merged : Boolean,
    var textBounds : Rect
) {
    val padding = 10
}