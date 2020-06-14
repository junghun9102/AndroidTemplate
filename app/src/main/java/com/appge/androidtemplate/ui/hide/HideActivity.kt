package com.appge.androidtemplate.ui.hide

import android.os.Bundle
import com.appge.androidtemplate.R
import com.appge.androidtemplate.component.HideSystemUIActivity

class HideActivity : HideSystemUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide)

        setToHideSystemUI()
    }
}
