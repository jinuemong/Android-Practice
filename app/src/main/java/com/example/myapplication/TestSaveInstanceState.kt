package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_first.*

class TestSaveInstanceState : AppCompatActivity() {
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tets_save_instance_state)

        textView = findViewById<TextView>(R.id.test_text)

        // 데이터 불러오기
        if (savedInstanceState!=null){
            val data = savedInstanceState.getString("Key")
            textView.text = data
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        // 데이터 저장
        val data = textView.text.toString()
        outState.putString("Key",data)
    }
}