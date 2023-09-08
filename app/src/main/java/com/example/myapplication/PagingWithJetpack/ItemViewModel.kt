package com.example.myapplication.PagingWithJetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

// tip1> CachedIn
// getItemPager에 사용
// 화면전환 등 환경 변화에도 값을 viewModelScope에 저장해서 데이터를 유지
// 환경이 변화되더라도 값을 매번 로딩할 필요가 없음

class ItemViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    val items : Flow<PagingData<Item>> =
        repository.getItemPager()
            .cachedIn(viewModelScope)
}