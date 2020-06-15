package com.appge.androidtemplate.ui.game.surface_view.`object`

import android.content.Context
import android.graphics.Canvas

interface GameObject {
    fun draw(canvas: Canvas, context: Context)
}