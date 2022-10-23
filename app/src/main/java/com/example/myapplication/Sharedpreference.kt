package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sharedpreference.*

class Sharedpreference : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedpreference)
        //안드로이드의 데이터베이스
        //공유된 사용자의 기호를 저장하기 위함 :  Sharedpreference
        //키 -밸류 방식식 ,  하드한 데이터베이스 구축 불가능

       // Mode
        // -MODE_PRIVATE : 생성한 앱에서만 사용 가능
        // -MODE_WORLD_READABLE : 다른 앱에서 가능, 읽기
        // -MODE_WORLD_WRITEABLE : 다른 앱에서 가능 , 쓰기 , 기록 가능
        // - MODE_MULTI_PROCESS: 이미 호출되어 사용중인지 체크
        // - MODE_APPEND : 기존 preference에 신규로 추가
        button_1.setOnClickListener {
            //값을 불러오는 방법
            val sharedpreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val value1 = sharedpreference.getString("hello", "데이터 없음1")
            val value2 = sharedpreference.getString("goodbye", "데이터 없음2")
            Log.d("key-value", "Value1:" + value1)
            Log.d("key-value", "Value2:" + value2)

            //앱을 새로 켜도 데이터가 남아있음
        }

        //값 저장하기
        button_2.setOnClickListener {
            //sharedpreference에 저장하는 방법
            val sharedpreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedpreference.edit()
            editor.putString("hello", "안녕하세요")
            editor.putString("goodbye", "잘가세요")
            editor.apply() //꼭 해줘야함
            // 같은 키, 밸류에도 다른 sharedpreference 이름이면 다른 값임
        }
        //값 일부 삭제하기
        button_3.setOnClickListener {
            val sharedpreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            //값 불러오기
            val editor = sharedpreference.edit()
            editor.remove("hello")
            editor.apply()

        }
        //값 전체 삭제하기
        button_4.setOnClickListener {
            val sharedpreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
            //값 불러오기
            val editor = sharedpreference.edit()
            editor.clear()
            editor.apply()
        }
    }
}