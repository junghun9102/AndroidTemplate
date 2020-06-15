package com.appge.androidtemplate.ui.game.surface_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.appge.androidtemplate.R

class GameRenderer(
    private val width: Int,
    private val height: Int
) {

    private var circleX = width/2f
    private var circleY = height/2f
    private val circleR = 100f

    fun draw(context: Context, canvas: Canvas) {
        drawRectangle(canvas, Rect(0, 0, width, height), ContextCompat.getColor(context, R.color.colorPrimaryDark))
        drawCircle(canvas, circleX, circleY, circleR, Color.WHITE)
    }

    private fun drawCircle(canvas: Canvas, x: Float, y: Float, r: Float, color: Int) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
        }
        canvas.drawCircle(x, y, r, paint)
    }

    private fun drawRectangle(canvas: Canvas, rect: Rect, color: Int) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.color = color
        }
        canvas.drawRect(rect, paint)
    }

}