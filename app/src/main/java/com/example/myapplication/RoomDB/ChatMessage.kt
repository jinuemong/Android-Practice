package com.example.myapplication.RoomDB

import androidx.room.Entity
import androidx.room.TypeConverters

@Entity
data class ChatMessage(
    @TypeConverters(Converters::class)
    val messageType : MessageType,
)

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO
}