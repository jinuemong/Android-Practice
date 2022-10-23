package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {

    //프레그먼트에서 엑티비티로 데이터 전달
    interface OnDataPassListener{
        fun onDataPass(data:String?)

    }
    lateinit var dataPassListener:OnDataPassListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as OnDataPassListener //형변환

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val data = arguments?.getString("hello")
        Log.d("data",data.toString())
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflater : 뷰를 그려줌
        //container : 삽입될 곳 , 부모뷰
        return inflater.inflate(R.layout.fragment_one, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Activity의 Oncrate에서 했던 작업을 여기서 해줌
        pass.setOnClickListener{
            dataPassListener.onDataPass("Good Bye")
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}