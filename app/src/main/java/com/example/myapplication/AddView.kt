package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

class AddView : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)
        //ListView : 유사하게 반복되는 뷰를 그리기 위한 도구
//        addView - 실제 자주 사용 x
        // - item을 담을 xml을 제작 , xml을 채움, 컨테이너 뷰에 더해줌 >>반복
//        ListView - 예전에 많이 사용
//        RecycleView - 가장 좋은 효율

        //아이템 리스트 준비 제작
        val carList = ArrayList<CarforList>()
        for (i in 0 until 10){
            carList.add(CarforList(i.toString()+"번째 자동차"
                ,i.toString()+"번째 engine"))
        }

        //컨테이너를 통해서 삽입
        val container = findViewById<LinearLayout>(R.id.add_view_container)
        val inflater = LayoutInflater.from(this@AddView)
        for (i in 0 until 10){
            val itemView = inflater.inflate(R.layout.item_view,null)
            // 뷰를 가져온다
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)
            //해당 뷰에서 가져온다
            carNameView.setText(carList.get(i).name)
            carEngineView.setText(carList.get(i).engine)
            container.addView(itemView) //세팅한 뷰를 더해줌
        }

    }
}

class CarforList(val name : String, val engine:String){

}