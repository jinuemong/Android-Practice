package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class Resource: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val ment = resources.getString(R.string.hello)
        val ment2 = getString(R.string.hello)
        //string 값을 불러와서 변경 가능

        val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getColor(R.color.textview_color)
        }else{
            resources.getColor(R.color.textview_color)
        }
        //버전이 바뀌었기 때문에 이전 버전은 다른 호출
    }
}