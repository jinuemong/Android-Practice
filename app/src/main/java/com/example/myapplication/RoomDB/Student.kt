package com.example.myapplication.RoomDB

import androidx.room.*


// data class
// 참고
// primary key의 auto-generate는 Room Entity의 데이터클래스로 객체를 생성할 때 설정된 값이
// 이 객체에 설정된 id값이 그대로 DB에 반영되지 않음
// Insert 메소드를 실행할 때 auto - generate 되며, id 부분을 바로 사용해야 한다면,
// Insert 되고 난 시점에 사용해 주어야 한다

@Entity(tableName = "students")
class Student (
    @PrimaryKey(autoGenerate = true) val studentId : Int,
    @ColumnInfo(name = "first_name") val firstName : String?,
    @ColumnInfo(name = "last_name") val lastName : String?,
    @ColumnInfo(name = "isTrue") val isTrue : Boolean
)

// 그 외 필드

//@Ignore 필드 무시


// foreignKey 활용
class Teacher()

@Entity(
        foreignKeys = [ForeignKey(entity = Teacher::class,
        parentColumns = arrayOf("teacherId"),
            childColumns = arrayOf("teacherId"))]
)
class Student2(
    @PrimaryKey(autoGenerate = true) val studentId : Int,
    @ColumnInfo(name = "first_name") val firstName : String?,
    @ColumnInfo(name = "last_name") val lastName : String?,
    @ColumnInfo(name = "teacherId") val teacherId : Int
)