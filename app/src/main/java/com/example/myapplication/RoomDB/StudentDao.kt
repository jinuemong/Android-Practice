package com.example.myapplication.RoomDB

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// DB에 엑세스 하는 메소드
// SQL 쿼리를 미리 함수에 저장해 두었다가 사용
// Query 애노테이션에 괄호를 열고 SQL 작성
// CRUD
// vararg -> kotlin의 가변인자


// ! Room은 SQLite 기반
// true : 1 , false : 0으로 표현
const val SQL_TRUE = 1
const val SQL_FALSE = 0


// 정렬 순서 설정
// SELECT * FROM chat_messages ORDER BY createdAt DESC

@Dao
interface StudentDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg students : Student)

    @Query(value = "SELECT * FROM students")
    fun getAll() : List<Student>

    @Query(value = "SELECT * FROM students WHERE studentId IN (:studentIds)")
    fun loadAllByIds(studentIds: IntArray) : List<Student>

    @Query(value = "SELECT * FROM students WHERE first_name LIKE :first And " +
            "last_name LIKE : last LIMIT 1")
    fun findByName(first : String, last : String) : Student

    @Query(value = "SELECT * FROM students WHERE score BETWEEN :min AND :max")
    fun findStudentsBetweenScores(min:Int, max:Int)

    @Update
    fun update(student:Student)

    @Delete
    fun delete(student:Student)

    @Query("SELECT * FROM tests WHERE isTrue = :isTrue")
    fun getFavoriteStudents(isTrue : Int = SQL_TRUE) : Flow<List<Student>>
}

