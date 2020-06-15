package com.appge.androidtemplate.ui.game.surface_view.`object`

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.appge.androidtemplate.R
import com.appge.androidtemplate.ext.dRectangle

class Background(
    private val width: Int,
    private val height: Int
) : GameObject {

    override fun draw(canvas: Canvas, context: Context) {
        canvas.dRectangle(Rect(0, 0, width, height), ContextCompat.getColor(context, R.color.colorPrimaryDark))
    }
}