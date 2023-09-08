package com.example.myapplication.PagingWithJetpack

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// RoomDataBase 구현
// PagingSourceTime 리턴  -> pagingSource 함수

@Dao
interface ItemDao {

    @Insert
    suspend fun upsertAll(items : List<ItemEntity>)

    // key - value 형식
    // key : paging 할 기준 값
    // value : 결과로 받을 타입
    @Query("SELECT * FROM items")
    fun pagingSource() : PagingSource<Int, ItemEntity>


    // 컬럼의 데이터를 가져오는 dao 작성
    // 리턴 타입이 반드시 pagingSource 타입이어야 함
    @Query("SELECT * FROM items ORDER BY timestamp DESC")
    fun getItemPager() : PagingSource<Int, Item>

}