package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        //ListView의 viewholder 기능 사용 , 매우 유연함

        //아이템 리스트 준비 제작
        val carList = ArrayList<CarforList>()
        for (i in 0 until 10){
            carList.add(CarforList(i.toString()+"번째 자동차"
                ,i.toString()+"번째 engine"))
        }
        val adapter = RecyclerViewAdapter(carList,LayoutInflater.from(this@RecyclerViewActivity))
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
//        recycler_view.layoutManager = GridLayoutManager(this@RecyclerViewActivity,2)
        //그리드 레이아웃 사용법


    }
}

class RecyclerViewAdapter(
    val itemList : ArrayList<CarforList>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    //이너 클래스 inner을 추가해야 외부 클래스를 이용 가능
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val carName:TextView
        val carEngine:TextView
        init {
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
            itemView.setOnClickListener{
                val position = bindingAdapterPosition
                val engineName = itemList.get(position).engine

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        //뷰를 그려줌
        val view = inflater.inflate(R.layout.item_view,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        //뷰 홀더의 태그 역할 - 찾기용(묶어줌)
        holder.carName.setText(itemList.get(position).name)
        holder.carEngine.setText(itemList.get(position).engine)
    }

    override fun getItemCount(): Int {
        //아이템 리스트의 사이즈
        return itemList.size
    }

}