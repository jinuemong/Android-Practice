package com.example.myapplication.RoomDB

import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl(
    private val dao: StudentDao
) : StudentRepository {
    override suspend fun loadAllByStudentIds(studentIds: IntArray): List<Student> {
        return dao.loadAllByIds(studentIds = studentIds)
    }

    override suspend fun findByStudentName(first: String, last: String): Student {
        return dao.findByName(first,last)
    }

    override suspend fun deleteStudent(student: Student) {
        dao.delete(student)
    }

    override suspend fun getFavoriteStudents(): Flow<List<Student>> {
        return dao.getFavoriteStudents()
    }
}