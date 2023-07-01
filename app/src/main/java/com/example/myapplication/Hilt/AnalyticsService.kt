package com.example.myapplication.Hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

// 인터페이스 선언
interface AnalyticsService {
    fun analyticsMethods()
}

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.

class AnalyticsServiceImpl @Inject constructor(

) : AnalyticsService {
    // 상속하였으니 함수 오버라이드
    override fun analyticsMethods() {
        TODO("Not yet implemented")
    }
}


