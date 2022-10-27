package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// 메인 액티비티
class CalendarMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_main)
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.main_layout,CalendarFirstFragment())
            .commit()
    }
}