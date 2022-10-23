package com.example.myapplication

//lateinit - 나중에 초기값 세팅
lateinit var lateCar : Car
class Car(){
 //초기화가 안되고 호출하면 에러 발생
}
class NullSafety {
    //엘비스 연산자
    val number1: Int = 10
    val number2: Int? = null
    val number = number2 ?: 10



}