package com.appge.androidtemplate.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {

    const val PERMISSIONS_CAMERA = 1

    private val permissionList = mapOf(
        PERMISSIONS_CAMERA to arrayOf(android.Manifest.permission.CAMERA)
    )

    private fun getPermissions(requestCode: Int) = permissionList[requestCode] ?: throw IllegalArgumentException("You must use requestCode defined at PermissionHelper")

    fun checkPermission(context: Context, requestCode: Int): Boolean {
        val permissions = getPermissions(requestCode)
        return checkPermissions(context, permissions)
    }

    private fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions(context: Context, permissions: Array<String>): Boolean {
        return permissions.fold(true, { acc, permission ->
            acc && checkPermission(context, permission)
        })
    }

    fun requestPermissions(activity: Activity, requestCode: Int) {
        val permissions = getPermissions(requestCode)
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    fun onRequestPermissionResult(context: Context, requestCode: Int, funcAllowed: () -> Unit, funcNotAllowed: () -> Unit = {}) {
        val permissions = getPermissions(requestCode)
        if (checkPermissions(context, permissions)) {
            funcAllowed.invoke()
        } else {
            funcNotAllowed.invoke()
        }
    }
}

// PermissionHelper Extensions
fun Activity.requestPermissions(requestCode: Int) = PermissionHelper.requestPermissions(this, requestCode)

fun Context.checkPermissions(requestCode: Int) = PermissionHelper.checkPermission(this, requestCode)

fun Context.onRequestPermissionResult(requestCode: Int, funcAllowed: () -> Unit, funcNotAllowed: () -> Unit = {}) =
    PermissionHelper.onRequestPermissionResult(this, requestCode, funcAllowed, funcNotAllowed)

fun Activity.checkPermissionsAndDoFunctionOrRequest(requestCode: Int, funcAllowed: () -> Unit) {
    if (this.checkPermissions(requestCode))
        funcAllowed.invoke()
    else
        this.requestPermissions(requestCode)
}