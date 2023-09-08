package com.example.myapplication.RoomDB

import kotlinx.coroutines.flow.Flow

interface StudentRepository {

    suspend fun loadAllByStudentIds(studentIds: IntArray) : List<Student>

    suspend fun findByStudentName(first : String, last : String)  : Student

    suspend fun deleteStudent(student: Student)

    suspend fun getFavoriteStudents() : Flow<List<Student>>
}