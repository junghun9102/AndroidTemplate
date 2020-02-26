package com.appge.androidtemplate.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appge.androidtemplate.R
import com.appge.androidtemplate.util.PermissionHelper
import com.appge.androidtemplate.util.checkPermissionsAndDoFunctionOrRequest
import com.appge.androidtemplate.util.onRequestPermissionResult
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * 1. Manifest 권한 추가
 * 2. PermissionHelper 필요한 동작에 따른 권한들을 요청코드와 Map 에 정의
 * 3. 동적 권한이 필요한 동작이 발생하면 권한 체크
 *    - Activity.checkPermission(requestCode, ...)
 * 4. 권한이 허용되어 있다면 동작 실행
 *    - Activity.checkPermission(.., funcAllowed)
 * 5. 권한이 허용되어 있지 않다면 권한 요청
 *    - Activity.checkPermission(requestCode, funcAllowed)
 * 6. 권한 요청에 따른 사용자 반응이 허용이라면 동작 실행
 *    - Context.onRequestPermissionsResult(.., funcAllowed, ..)
 * 7. 거부라면 거부에 따른 처리
 *    - Context.onRequestPermissionsResult(.., funcNotAllowed)
 *
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btn_main_camera.setOnClickListener {
            checkPermissionAndStartCamera()
        }
    }

    private fun checkPermissionAndStartCamera() {
        checkPermissionsAndDoFunctionOrRequest(PermissionHelper.PERMISSIONS_CAMERA) {
            startCamera()
        }
    }

    private fun startCamera() {
        Toast.makeText(this, "카메라를 시작합니다 위이잉", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PermissionHelper.PERMISSIONS_CAMERA ->
                onRequestPermissionResult(PermissionHelper.PERMISSIONS_CAMERA, {
                    startCamera()
                }) {
                    showPermissionDeniedMessage()
                }
        }
    }

    private fun showPermissionDeniedMessage() {
        Toast.makeText(this, "권한을 허용하지 않아 카메라를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show()
    }
}
