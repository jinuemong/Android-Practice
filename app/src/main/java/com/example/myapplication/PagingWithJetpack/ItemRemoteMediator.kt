package com.example.myapplication.PagingWithJetpack

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator

// remote mediator
// 리모트 서버와 데이터를 이어주는 역할
// https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator
@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(

) : RemoteMediator<Int,ItemEntity>(){

    // 가장 중요한 구현
    // 서버에서 어떤 값들을 가지고 와서 어떻게 db에 넣어서 보여줄 지 결정
    // load type에 따라서 구현이 달라짐
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}