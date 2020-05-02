package com.appge.androidtemplate.custom

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.appge.androidtemplate.R
import kotlinx.android.synthetic.main.view_dialog.view.*

class CustomDialog(
    context: Context,
    title: String,
    content: String? = null
) {

    constructor(context: Context, titleResId: Int, contentResId: Int? = null) : this(
        context,
        context.getString(titleResId),
        contentResId?.let { context.getString(it) }
    )

    private val view = LayoutInflater.from(context).inflate(R.layout.view_dialog, null)
    private val titleView = view.tv_view_dialog_title
    private val contentView = view.tv_view_dialog_content
    private val positiveView = view.tv_view_dialog_positive
    private val negativeView = view.tv_view_dialog_negative
    private val defaultClickFunc = { dialog.dismiss() }

    init {
        titleView.text = title
        content?.let {
            contentView.text = it
        } ?: kotlin.run {
            contentView.visibility = View.GONE
        }

        positiveView.setOnClickListener { defaultClickFunc.invoke() }
        negativeView.setOnClickListener { defaultClickFunc.invoke() }
    }

    private val dialog = AlertDialog.Builder(context)
        .setView(view)
        .create()

    fun show() {
        dialog.show()
    }

    fun setPositiveButton(buttonText: String, func: () -> Unit = defaultClickFunc): CustomDialog {
        setTextViewForButton(positiveView, buttonText, func)
        return this
    }

    fun setPositiveButton(buttonTextResId: Int, func: () -> Unit = defaultClickFunc) =
        setPositiveButton(view.context.getString(buttonTextResId), func)

    fun setNegativeButton(buttonText: String, func: () -> Unit = defaultClickFunc): CustomDialog {
        setTextViewForButton(negativeView, buttonText, func)
        return this
    }

    fun setNegativeButton(buttonTextResId: Int, func: () -> Unit = defaultClickFunc) =
        setNegativeButton(view.context.getString(buttonTextResId), func)

    private fun setTextViewForButton(view: TextView, buttonText: String, func: () -> Unit) {
        view.run {
            text = buttonText
            setOnClickListener {
                func.invoke()
                dialog.dismiss()
            }
        }
    }

}