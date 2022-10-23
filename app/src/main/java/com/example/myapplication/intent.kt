package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        val change_activity = findViewById<Button>(R.id.change_activity)
        change_activity.setOnClickListener {
              //명시적 인텐트 -> 정확히 대상에게 요청
//            val intent = Intent(this@intent,MainActivity::class.java)
//            intent.putExtra("number1",10)
//            intent.putExtra("number2",20)
//            startActivity(intent)

//            //apply 사용하기
//            //코드의 가독성 증가
//            val intent2 = Intent(this@intent,MainActivity::class.java)
//            intent2.apply {
//                this.putExtra("number1",10)
//                this.putExtra("number2",20)
//            }
//            startActivity(intent)

            // 암시적 인텐트 -> 할수 있는 대상에 요청
            // 버튼 클릭 시 특정 인터넷 페이지 염
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
            startActivity(intent)
        }
    }
}