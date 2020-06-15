package com.appge.androidtemplate.ui.game.surface_view.`object`

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import com.appge.androidtemplate.component.JoyStickInterface
import com.appge.androidtemplate.ext.dCircle
import com.appge.androidtemplate.ext.dStrokeCircle

class JoyStick : GameObject {

    private var joyStickResult: JoyStickInterface.Result? = null

    fun setResult(result: JoyStickInterface.Result?) {
        joyStickResult = result
    }

    override fun draw(canvas: Canvas, context: Context) {
        joyStickResult?.let {
            canvas.dStrokeCircle(it.startX, it.startY, 200f, 10f, Color.GRAY)
            canvas.dCircle(it.startX+it.vectorX, it.startY+it.vectorY, 100f, Color.RED)
        }
    }

}