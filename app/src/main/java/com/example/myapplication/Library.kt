package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library.*

class Library : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Framework : 개발하기 위해 지켜할 틀(안드로이드 스튜디오)
        //Library : 개발하기 위해 필요한 도구가 구현 되어짐 - 함수나 클래스, 프레임워크에 없음
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        Glide.with(this@Library)
            .load("https://drive.google.com/uc?id=1gq2OFKKv7usMqBkGdkpqVYXWpqoFANnY ")
            .into(glide)
    }
}