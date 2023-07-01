package com.example.myapplication.Hilt

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// @ApplicationContext 어노테이션을 사용해서 애플리케이션 컨텍스트 결합

class AnalyticsServiceImpl2 @Inject constructor(
    @ApplicationContext context: Context
) : AnalyticsService {
    // 필수 메서드
    override fun analyticsMethods() {
        TODO("Not yet implemented")
    }

}

//// Qualifier :커스텀 어노테이션 없이 애플리케이션 바인딩 사용
// The Application binding is available without qualifiers.
class AnalyticsServiceImpl3 @Inject constructor(
    application: Application
) : AnalyticsService {
    // 필수 메서드
    override fun analyticsMethods() {
        TODO("Not yet implemented")
    }

}

// 활동 컨텍스트 결합의 다른 예

// 액티비티 컨텍스트 사용
class AnalyticsAdapter4 @Inject constructor(
    @ActivityContext context: Context
) {
}

// 단순 액티비티 바인딩 전달
// The Activity binding is available without qualifiers.
class AnalyticsAdapter5 @Inject constructor(
    activity: FragmentActivity
) {

}