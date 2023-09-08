package com.example.myapplication.RoomDB

import androidx.room.TypeConverter
import java.util.*

// 커스텀한 데이터 객체를 저장하는 방법
// Convertes 클래스를 제작해서 특정 timestamp와 Date 객체를 반환하도록 함
// 특정 timestamp와 Date 객체를 반환
// 이 Converter들을 직접 필요한 Entity에 추가하여 적용
// to ChatMessage
class Converters {
    @TypeConverter
    fun fromTimestamp(value : Long?) : Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? {
        return date?.time?.toLong()
    }
}