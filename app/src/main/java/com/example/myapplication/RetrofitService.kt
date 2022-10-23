package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//url을 관리 할 수 있는 파일
interface RetrofitService {
    @GET("stas/") //얻어옴 주소를 (정보 요청)
    fun getStudentsList(): Call<ArrayList<PersonforServer>> //이 타입으로
//baseurl 뒷부분만 적어줌
    @POST("json/students/") //정보 추가 요청 1
    fun createStudent(
    @Body params:HashMap<String, Any>
    ): Call<PersonforServer> //리턴 타입에 맞는 인자를 넣어줌
    //request 메소드

    @POST("stas/") //정보 추가 요청2
    fun createStudentEasy(
        @Body person: PersonforServer
    ): Call<PersonforServer>

}