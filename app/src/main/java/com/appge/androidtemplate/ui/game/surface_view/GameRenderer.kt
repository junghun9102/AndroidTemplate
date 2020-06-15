package com.appge.androidtemplate.ui.game.surface_view

import android.content.Context
import android.graphics.Canvas
import com.appge.androidtemplate.component.JoyStickInterface
import com.appge.androidtemplate.ui.game.surface_view.`object`.Background
import com.appge.androidtemplate.ui.game.surface_view.`object`.JoyStick
import com.appge.androidtemplate.ui.game.surface_view.`object`.Player

class GameRenderer(width: Int, height: Int) {

    private val background = Background(width, height)
    private val player = Player(width, height)
    private val joyStick = JoyStick()

    fun updateJoyStick(result: JoyStickInterface.Result?) {
        player.setVector(result?.vectorX ?: 0, result?.vectorY ?: 0)
        joyStick.setResult(result)
    }

    fun draw(context: Context, canvas: Canvas) {
        updateForFrame()
        background.draw(canvas, context)
        player.draw(canvas, context)
        joyStick.draw(canvas, context)
    }

    private fun updateForFrame() {
        player.move()
    }

}