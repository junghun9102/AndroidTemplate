package com.appge.androidtemplate.ui.game.surface_view.`object`

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.appge.androidtemplate.ext.dCircle

class Player(
    private val width: Int,
    private val height: Int
) : GameObject, Movable {

    private var x = width/2f
    private var y = height/2f

    var vectorX = 0f
    var vectorY = 0f

    fun setVector(vectorX: Int, vectorY: Int) {
        this.vectorX = vectorX * WEIGHT_SPEED
        this.vectorY = vectorY * WEIGHT_SPEED
    }

     override fun move() {
         val newX = x + vectorX
         val newY = y + vectorY
         if (newX in RADIUS..width-RADIUS) x = newX
         if (newY in RADIUS..height-RADIUS) y = newY
    }

    override fun draw(canvas: Canvas, context: Context) {
        canvas.dCircle(x, y, RADIUS, Color.WHITE)
    }

    companion object {
        private const val WEIGHT_SPEED = 0.1f
        private const val RADIUS = 100f
    }

}