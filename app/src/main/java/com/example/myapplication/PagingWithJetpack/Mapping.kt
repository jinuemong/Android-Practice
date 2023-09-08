package com.example.myapplication.PagingWithJetpack

// Mapping 함수
// 각 데이터 클래스를 사용할 때마다 수정하는 것은 비효율적
// Mapping 함수를 두어서 사용
// 서버의 데이터를 다루는 Dto 타입의 데이터를 Entity 타입으로 변형, 반대의 경우를 대비해서 미리 함수 제작

// dto ->  entity
fun ItemDto.toItemEntity() : ItemEntity{
    return ItemEntity(
        id = id,
        name = name
    )
}

// entity -> dto
fun ItemEntity.toItemDto() : ItemDto {
    return ItemDto(
        id = id,
        name = name
    )
}