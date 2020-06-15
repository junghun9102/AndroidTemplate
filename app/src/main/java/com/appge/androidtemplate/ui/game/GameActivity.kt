package com.appge.androidtemplate.ui.game

import android.os.Bundle
import com.appge.androidtemplate.R
import com.appge.androidtemplate.component.HideSystemUIActivity

class GameActivity : HideSystemUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setToHideSystemUI()
    }

}
