package com.appge.androidtemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.appge.androidtemplate.custom.CustomDialog
import com.appge.androidtemplate.ext.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toast.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btn_main_toast.setOnClickListener {
            showToast(R.string.app_name)
        }

        btn_main_alert_dialog.setOnClickListener {
            CustomDialog(this, R.string.dialog_delete_title, R.string.dialog_delete_content)
                .setPositiveButton(R.string.common_delete) {
                    showToast(R.string.message_delete_complete)
                }
                .show()
        }
    }
}
