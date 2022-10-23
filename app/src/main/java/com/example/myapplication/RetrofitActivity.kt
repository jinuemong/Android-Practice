package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofitlibrary)
        //"http://mellowcode.org/json/students/"
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://1c09-59-14-57-18.jp.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    //서버로 부터 원하는 데이터 타입으로 바꾸는 과정을 도와줌
        val service = retrofit.create(RetrofitService::class.java) //만든 파일로 서비스 제작

        //GET 요청하기
        service.getStudentsList().enqueue( //통신을 대기줄에 올려놓음
            object :Callback<ArrayList<PersonforServer>>{
                @SuppressLint("LongLogTag")
                override fun onResponse( //통신 성공
                    call: Call<ArrayList<PersonforServer>>,
                    response: Response<ArrayList<PersonforServer>>
                ) {
                    if(response.isSuccessful){
                        val personList = response.body() //이 한줄로 doinbackground 대체
                        Log.d("retrofit : response.body()",personList?.get(0)?.user_id.toString())
                    }
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<ArrayList<PersonforServer>>, t: Throwable) {
                    //통신 실패
                    Log.d("retrofit : response.body()","err")
                }

            })

        //POST 요청하기 (첫번째 방법
//        val params= HashMap<String, Any>()
//        params.put("name","김개똥")
//        params.put("age",20)
//        params.put("intro","안녕하세요")
//        service.createStudent(params).enqueue(
//            object :Callback<PersonforServer>{
//                override fun onResponse(
//                    call: Call<PersonforServer>,
//                    response: Response<PersonforServer>
//                ) {
//                    if(response.isSuccessful) {
//                        val person = response.body()
//                        Log.d("retrofit:POST","name:"+person?.name)
//                    }
//                }
//
//                override fun onFailure(call: Call<PersonforServer>, t: Throwable) {
//
//                }
//
//            }
//        )

        //POST 요청하기 (두번째 방법 person 객체를 만들어서 시작
        val person = PersonforServer(user_id="w123",pw1 ="123", pw2 ="123")
        service.createStudentEasy(person).enqueue(
            object :Callback<PersonforServer>{
                override fun onResponse(
                    call: Call<PersonforServer>,
                    response: Response<PersonforServer>
                ) {
                    if(response.isSuccessful) {
                        val person = response.body()
                        Log.d("retrofit:POST","user_id:"+person?.user_id)
                    }
                }

                override fun onFailure(call: Call<PersonforServer>, t: Throwable) {

                }

            }
        )

    }
}