package com.appge.androidtemplate.custom

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.appge.androidtemplate.R
import kotlinx.android.synthetic.main.view_toast.view.*
import org.jetbrains.anko.dimen

object CustomToast {

    fun show(context: Context, stringResId: Int, duration: Int = Toast.LENGTH_SHORT) = show(context, context.getString(stringResId), duration)

    private fun show(context: Context, content: String, duration: Int = Toast.LENGTH_SHORT) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_toast, null)
        view.tv_view_toast.text = content

        Toast(context).apply {
            setGravity(
                Gravity.FILL_HORIZONTAL or Gravity.BOTTOM,
                0,
                context.dimen(R.dimen.custom_toast_bottom_margin)
            )
            this.duration = duration
            setView(view)
        }.show()
    }

}