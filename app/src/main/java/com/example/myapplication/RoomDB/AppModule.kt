package com.example.myapplication.RoomDB

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    // 의존성 주입 1 : Room Database 객체 생성
    @Singleton
    @Provides
    fun provideStudentDatabase(application: Application) : StudentDatabase {
        return Room.databaseBuilder(
            application,
            StudentDatabase::class.java,
            "student_db"
        )
            .fallbackToDestructiveMigration() // 기존 데이터베이스 스택을 지우고 새로 생성
            .build()
    }
    // 의존성 주입 2 : Room을 이용하는 Repository 객체 생성
    @Singleton
    @Provides
    fun provideStudentRepository(studentDatabase: StudentDatabase) : StudentRepository{
        return StudentRepositoryImpl(studentDatabase.studentDao())
    }
}