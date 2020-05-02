package com.appge.androidtemplate.ext

import android.content.Context
import com.appge.androidtemplate.custom.CustomToast

fun Context.showToast(resId: Int) = CustomToast.show(this, resId)