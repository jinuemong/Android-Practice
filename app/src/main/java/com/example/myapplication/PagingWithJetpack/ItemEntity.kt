package com.example.myapplication.PagingWithJetpack

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity 구현

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey val id : Int,
    val name : String
)