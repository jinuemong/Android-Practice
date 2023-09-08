package com.example.myapplication.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// 컴포넌트 database 구현
// @Database 어노테이션 사용
// abstract class 사용
// RoomLibrary에 맡김 ( SQL 코드 등의 구현을 )
// 개발자가 정의한 코드에 따라서 룸 라이브러리가 필요한 sql 코드들을 생성
// 제작한 entity들을 array 형태로 넣어줌

// 만약 converters 클래스를 가지고 있는 상태로 entity에서 적용하지 않았다면
// 아래 어노테이션을 추가할 수 있다
//@TypeConverters(Converters::class)

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase :RoomDatabase() {
    abstract fun studentDao() : StudentDao
}

// 위의 데이터베이스를 객체로 만들어서 사용할 수 있다
