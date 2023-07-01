package com.example.myapplication.Hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

// [ Binds ] 어노테이션 활용

// 사용방법 : InstallIn 필수
// Hilt가 AnalyticsModule의 종속 항목을 ExampleActivity에 삽입하기를 원함
// Hilt 모듈 AnalyticsModule에 @InstallIn(ActivityComponent.class)  지정
// 이 주석을 통해서 AnalyticsModule의 모든 종속 항목을 앱의 모든 활동에서 사용할 수 있음
@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule{

    @Binds //인터페이스의 인스턴스를 제공해야 할 때 사용할 구현을 알려줌
    // 함수 반환 유형은 함수가 어떤 인터페이스의 인스턴스를 제공하는지
    // 함수 매개변수는 제공할 구현을 Hilt에 알려줌
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: AnalyticsServiceImpl
    ) : AnalyticsService
}
