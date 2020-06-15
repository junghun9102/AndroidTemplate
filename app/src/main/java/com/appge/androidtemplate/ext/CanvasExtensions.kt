package com.appge.androidtemplate.ext

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect


fun Canvas.dCircle(x: Float, y: Float, r: Float, color: Int) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = color
    }
    drawCircle(x, y, r, paint)
}

fun Canvas.dRectangle(rect: Rect, color: Int) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = color
    }
    drawRect(rect, paint)
}


fun Canvas.dStrokeCircle(x: Float, y: Float, r: Float, stokeWidth: Float, color: Int) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = color
        strokeWidth = stokeWidth
        style = Paint.Style.STROKE
    }
    drawCircle(x, y, r, paint)
}