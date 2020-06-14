package com.appge.androidtemplate.ui.main

import android.os.Bundle
import android.view.View
import com.appge.androidtemplate.R
import com.appge.androidtemplate.component.BaseActivity
import com.appge.androidtemplate.component.HideSystemUIActivity
import com.appge.androidtemplate.ui.hide.HideActivity
import com.appge.androidtemplate.ui.transparent.TransparentActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btn_main_hide.setOnClickListener {
            startActivity<HideActivity>()
        }

        btn_main_transparent.setOnClickListener {
            startActivity<TransparentActivity>()
        }
    }

}
