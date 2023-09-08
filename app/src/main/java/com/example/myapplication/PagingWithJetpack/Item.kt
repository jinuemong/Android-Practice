package com.example.myapplication.PagingWithJetpack

// 도메인 클래스
// 앱에서 DB의 Entity 나 서버의 Dto 클래스 만으로는 커버하기 부족할 때가 있음
// 디비를 서버의 데이터클래스로부터 분리하기 위한 목적
// 이 예제에서는 데이터 클래스와 차이가 없지만, 실제 데이터에서는 다르다
data class Item(
    val id : Int,
    val name : String
)