package com.example.myapplication.Hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Qualifier

// Qualifier 어노테이션을 통해서 커스텀 어노테이션 지정
// Qualifier 설정을 통해서 Hilt가 잘못된 종속 항목을 삽입하는 것을 막을 수 있다.
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(otherInterceptor)
            .build()
    }
}

class AuthInterceptor() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}

class OtherInterceptor() : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

}

// Hilt에 정의 된 한정자 사용
class AnalyticsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val service: AnalyticsService
) {
}