package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_tread.*
import java.lang.Thread

class Thread : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tread)
        //방법1
        val runnable : Runnable = object : Runnable {
            override fun run() {
                Log.d("Thread1","Thread1 is made")
            }
        }
        val thread : Thread = Thread(runnable)
        button_thread.setOnClickListener {
            thread.start()
        }
        //방법2
        Thread(object :Runnable{
            override fun run() {
                Log.d("Thread3","Thread2 is made")
            }
        }).start()
        //방법3
        Thread(Runnable{
            Log.d("Thread3","Thread3 is made")
        }).start()
    }
}