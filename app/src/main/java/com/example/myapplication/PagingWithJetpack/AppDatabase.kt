package com.example.myapplication.PagingWithJetpack

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao() : ItemDao
}