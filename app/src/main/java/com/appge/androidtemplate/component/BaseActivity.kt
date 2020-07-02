package com.appge.androidtemplate.component

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import com.appge.androidtemplate.extension.observeNotNull
import com.appge.androidtemplate.extension.plusAssign
import com.appge.androidtemplate.utils.AutoClearedDisposable
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    lateinit var disposables: AutoClearedDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDisposable()
    }

    private fun initDisposable() {
        disposables = AutoClearedDisposable(this)
        lifecycle += disposables
    }

    protected fun initActionbar(
        toolbar: Toolbar,
        titleColor: Int = Color.WHITE,
        isEnableNavigation: Boolean = true,
        onClickNavigation: () -> Unit = {}
    ) {
        toolbar.setTitleTextColor(titleColor)
        setSupportActionBar(toolbar)
        if (isEnableNavigation) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onClickNavigation.invoke()
            }
        }
    }

    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) = observeNotNull(this@BaseActivity, observer)

}