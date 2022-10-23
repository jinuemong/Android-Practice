package com.example.myapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_permission.*

class permissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        //일반 권한  - 인터넷 등
        //위험 권한 - 카메라, 연락처 등 등
        //권한을 묻는 경우 : 설치하자마자, 특정 기능 사용 시- best fit

        ask.setOnClickListener {

            val cameraPermissioncheck = ContextCompat.checkSelfPermission(
                this@permissionActivity,
                android.Manifest.permission.CAMERA
            )
            if (cameraPermissioncheck != PackageManager.PERMISSION_GRANTED) {
                //권한이 없는 경우
                ActivityCompat.requestPermissions(
                    this@permissionActivity,
                    arrayOf(android.Manifest.permission.CAMERA), //여러 값 보낼 수 있게 array로 처리
                    1000 //요청 코드, 확인용
                )
            } else {
                //있는 경우
                Log.d("permissionCheck ", "권한 있다")
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1000){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) { //하나만 보냈으므로 첫번째 값만 처리
                //요청 승인 완료
                Log.d("permissions","승낙")
            }else{
                //요청 거절
                Log.d("permissions","거절")
            }
        }
    }
}