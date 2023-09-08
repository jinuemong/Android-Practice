package com.example.myapplication.PagingWithJetpack

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

// Repository 구현
// dao 를 실행하는 역할
// PagingData 타입의 데이터를 Flow 타입으로 랩한 값을 리턴
// pageSize 한번의 로딩에 얼마나 많은 아이템을 표시할 지 지정
class ItemRepository(
  private val dao : ItemDao,
  private val apiService : ApiService
) {
    fun getItemPager() : Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // 한 번에 읽을 데이터 수
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dao.getItemPager()
            }
        ).flow
    }
}