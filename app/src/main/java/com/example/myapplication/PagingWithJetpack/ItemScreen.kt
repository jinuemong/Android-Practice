package com.example.myapplication.PagingWithJetpack

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.launch

// 페이저 구현

// tip : LazyColum LaunchedEffect
// Jetpack Compose의 스크롤
@Composable
fun ItemScreen (viewModel: ItemViewModel) {
    val lazyPagingItems = viewModel.items.collectAsLazyPagingItems()

    LazyColumn{
        items(lazyPagingItems){item->
            if (item!=null){
                ItemScreen(item)
            }else{
                // 로딩 or placeHolder Ui
            }
        }
    }

    // item이 LazyColum 에 들어왔을 때 아이템이 있는 곳까지 scroll을 내려주는 역할
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(lazyPagingItems){
        coroutineScope.launch {
            val lastIndex = lazyPagingItems.itemCount -1
            if (lastIndex>=0){
                lazyListState.scrollToItem(index = lazyPagingItems.itemCount -1 )
            }
        }
    }
}

@Composable
fun ItemScreen(item: Item){
    Text(text = item.name)
}