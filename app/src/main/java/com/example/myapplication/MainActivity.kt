package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1 = intent.getIntExtra("number1",0)
        val number2 = intent.getIntExtra("number2",0)
        Log.d("number1", number1.toString())
        Log.d("number2", number2.toString())

        val result = number1+number2
        val resultIntent = Intent()
        resultIntent.putExtra("result",result)
        setResult(Activity.RESULT_OK,resultIntent)
        //요청에 대한 결과값을 보내줌
        finish() //액티비티 종료
    }
}