package com.example.myapplication.PagingWithJetpack

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("items")
    suspend fun getItems(@Query("item") item:Int) : List<ItemDto>
}