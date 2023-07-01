package com.example.myapplication.Hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

//매번 새로운 인스턴스를 사용해야 하는 경우 !!

// ActivityScoped를 적용해서
// 필드 삽입을 통해서 adapter를 제공할 때마다
// AnalyticsAdapterScope의 새 인스턴스를 제공
@ActivityScoped
class AnalyticsAdapterScope @Inject constructor(
    private val service: AnalyticsService
) {

}
// 제공된 객체는 구성요소가 제거될 때까지 메모리에 남아있음
// 결합의 범위를 그 구성요소로 지정하면 많은 비용 발생
// 애플리케이션에서 범위가 지정된 결합의 사용을 최소화 해야함

// [ 아래 경우에 사용 ]
// 특정 범위 내에서 동일한 인스턴스를 사용해야 하는 내부 상태가 있는 결합
// 동기화가 필요한 결합
// 많은 비용이 드는 결합


//매번 동일한 인스턴스를 사용해야 하는 경우 !!
// If AnalyticsService is an interface.
@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule3 {

    @Singleton // 싱글톤으로 선언해서 동일한 인스턴스 사용 명시
    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: AnalyticsServiceImpl
    ): AnalyticsService
}

// 결합의 범위를 구성요소로 지정하는 방법
// 결합의 범위는 결합이 설치된 구성요소의 범위와 일치
// If you don't own AnalyticsService.
@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule4 {

    @Singleton
    @Provides
    fun provideAnalyticsService(): AnalyticsService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(AnalyticsService::class.java)
    }
}