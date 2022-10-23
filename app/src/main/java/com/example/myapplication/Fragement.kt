package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_fragement.*

open class Fragement : AppCompatActivity() , FragmentOne.OnDataPassListener {

    override fun onDataPass(data: String?) {
        Log.d("pass",data.toString())
    }

    // 라이프 사이클이 존재, 액티비티 종속적
    // 사용방법 - xml에 viewcomponent로 추가
    //        - 동적 코드로 추가
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragement)
        //프라그먼트를 동적으로 변환
        val fragmentOne: FragmentOne = FragmentOne()
        val fragmentManager = supportFragmentManager

        //프라그먼트에 데이터를 넣어주는 방법
        val bundle : Bundle = Bundle()
        bundle.putString("hello","hello")
        fragmentOne.arguments = bundle //할당

        var Button_click : Boolean = true
        fragment_button.setOnClickListener {
            //트랜잭션 : 작업의 단위 제작  > 시작과끝이 있음
            val fragmentTransaction = fragmentManager.beginTransaction()
            if (Button_click == true){
                //프라그먼트 추가 : replace/add
                fragmentTransaction.replace(R.id.contanier, fragmentOne)
                fragmentTransaction.commit()
                // 끝 방법 <commit : 시간 될때 , commitnow : 당장>
                Button_click = false
            }
            else{
                //프라그먼트 삭제 : remove/ detach
                fragmentTransaction.remove(fragmentOne)
                fragmentTransaction.commit()
                Button_click = true
            }


        }

    }
}