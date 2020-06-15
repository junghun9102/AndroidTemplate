package com.appge.androidtemplate.ui.main

import android.os.Bundle
import com.appge.androidtemplate.R
import com.appge.androidtemplate.ui.component.BaseActivity
import com.appge.androidtemplate.ui.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        btn_main.setOnClickListener {
            startActivity<GameActivity>()
        }
    }
}
