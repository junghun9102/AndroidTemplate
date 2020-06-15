package com.appge.androidtemplate.ui.component

import android.view.View

abstract class HideSystemUIActivity : BaseActivity() {

    private var flags = FLAGS_HIDE_DEFAULT or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    // must call in onCreate so that set listener
    fun setToHideSystemUI(hideStatusBar: Boolean = true, hideNavigationBar: Boolean = true) {
        flags = FLAGS_HIDE_DEFAULT
        if (hideStatusBar) flags = flags or View.SYSTEM_UI_FLAG_FULLSCREEN
        if (hideNavigationBar) flags = flags or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        window.decorView.setOnSystemUiVisibilityChangeListener {
            if (it != flags) hideSystemUI()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = flags
    }

    companion object {
        private const val FLAGS_HIDE_DEFAULT = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

}