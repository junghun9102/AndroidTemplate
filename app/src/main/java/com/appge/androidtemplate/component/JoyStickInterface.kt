package com.appge.androidtemplate.component

import android.util.Log
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * onDragFun : return between -100 and 100
 * onTouchEven : when event call performClick, return true
 */

class JoyStickInterface(
    private val onDragFunc: (Result?) -> Unit
) {

    private var curX: Float? = null
    private var curY: Float? = null
    private var preX: Float? = null
    private var preY: Float? = null

    fun onTouchEvent(event: MotionEvent) {
        Log.e("Test", "JoyStickInterface ${event.action} ${event.x} ${event.y}")
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                preX = event.x
                preY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                curX = event.x
                curY = event.y

                val vector = calculateDragVector()
                val result = Result(preX!!, preY!!, vector.first, vector.second)
                onDragFunc.invoke(result)
            }
            MotionEvent.ACTION_UP -> {
                onDragFunc.invoke(null)
            }
        }
    }

    private fun calculateDragVector(): Pair<Int, Int> {
        val maxDistance = 100
        val x = curX?.minus(preX ?: 0f) ?: 0f
        val y = curY?.minus(preY ?: 0f) ?: 0f
        val r = getR(x, y)
        val weight = if (r > maxDistance) maxDistance / r else 1f
        return Pair((x*weight).toInt(), (y*weight).toInt())
    }

    private fun getR(x: Float, y: Float) = sqrt(abs(x.pow(2) + y.pow(2)))

    data class Result(
        val startX: Float,
        val startY: Float,
        val vectorX: Int,
        val vectorY: Int
    )

}