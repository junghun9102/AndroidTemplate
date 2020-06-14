package com.appge.androidtemplate.ui.transparent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.appge.androidtemplate.R
import kotlinx.android.synthetic.main.activity_transparent.*

class TransparentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)

        ll_transparent.setOnApplyWindowInsetsListener { v, insets ->
            Log.e("Test", "insets t ${insets.systemWindowInsetTop}, b ${insets.systemWindowInsetBottom}, l ${insets.systemWindowInsetLeft}, r ${insets.systemWindowInsetRight}")
            (tv_transparent_title.layoutParams as LinearLayout.LayoutParams).let {
                it.topMargin = insets.systemWindowInsetTop
                tv_transparent_title.layoutParams = it
            }

            (tv_transparent_bottom.layoutParams as ConstraintLayout.LayoutParams).let {
                it.bottomMargin = insets.systemWindowInsetBottom
                tv_transparent_bottom.layoutParams = it
            }

            insets.consumeSystemWindowInsets()
        }
    }
}
