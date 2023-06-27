package com.example.myapplication.DIStudy

import android.app.Application

class MyApplication : Application() {

    // 애플리케이션 내부에서 앱 컨테이너 인스턴스화
    val appContainer = AppContainer()
}