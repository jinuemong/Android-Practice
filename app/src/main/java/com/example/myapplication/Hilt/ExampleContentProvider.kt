package com.example.myapplication.Hilt

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

//    @EntryPoint를 활용한 진입점 생성
class ExampleContentProvider : ContentProvider() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ExampleContentProviderEntryPoint {
        fun analyticsService(): AnalyticsService
    }

    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        // 아래와 같이 진입점에 엑세스 할 수 있다
        //매개변수는  매개변수 구성요소 인스턴스이거나 구성요소 소유자 역할을 하는 @AndroidEntryPoint 객체여야 한다
        // 매개변수로 전달하는 구성요소와 EntryPointAccessor 정적 매서드가 모두 @EntryPoint 인터페이스의
        // @InstallIn 주석에 있는 Android 클래스와 일치해야 한다.
        // 진입점이 SingletonComponent이므로 ApplicationContext를 사용해서 진입점을 검색해야 한다.
        // 검색하려는 결합이 ActivityComponent에 있다면 ActivityContext 대신 사용
        val appContext = context?.applicationContext ?: throw IllegalStateException()
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(appContext, ExampleContentProviderEntryPoint::class.java)

        val analyticsService = hiltEntryPoint.analyticsService()
        return null
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

}