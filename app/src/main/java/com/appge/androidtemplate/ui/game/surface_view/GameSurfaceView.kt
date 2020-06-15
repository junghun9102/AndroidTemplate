package com.appge.androidtemplate.ui.game.surface_view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var renderThread: RenderThread? = null
    private lateinit var gameRenderer: GameRenderer

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        Log.e("Test", "GameSurfaceView surfaceCreated")
        startRenderThread()
        if (::gameRenderer.isInitialized.not())
            gameRenderer = GameRenderer(width, height)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        Log.e("Test", "GameSurfaceView surfaceDestroyed")
        stopRenderThread()
    }

    private fun startRenderThread() {
        stopRenderThread()
        renderThread = RenderThread()
        renderThread?.start()
    }

    private fun stopRenderThread() {
        renderThread?.let {
            it.stopRunning()
            renderThread = null
        }
    }

    inner class RenderThread : Thread() {

        private var isRunning = true

        fun stopRunning() {
            isRunning = false
        }

        override fun run() {
            var canvas: Canvas
            try {
                while (isRunning) {
                    Log.e("Test", "GameSurfaceView RenderThread run")
                    canvas = holder.lockCanvas()
                    canvas?.let {
                        gameRenderer.draw(context, it)
                        holder.unlockCanvasAndPost(it)
                    }

                    sleep(1000L / FRAME)
                }
            } catch (e: InterruptedException) {
                Log.e("Test", "GameSurfaceView InterruptedException")
            } catch (e: IllegalStateException) {
                Log.e("Test", "GameSurfaceView IllegalStateException")
                stopRunning()
            }
        }
    }

    companion object {
        private const val FRAME = 50
    }

}