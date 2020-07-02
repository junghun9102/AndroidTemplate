package com.appge.androidtemplate.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

operator fun Lifecycle.plusAssign(observer: LifecycleObserver) {
    this.addObserver(observer)
}