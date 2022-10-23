package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view.*

class ListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //리스트로 만들고 싶은 아이템 리스트를 준비한다.
        //adapter를 이용한다
        //addview와 listview의 차이점
        //다른 방식의 제작, 다른 그림
        //addview는 리스트의 개수와 상관없이 한번에 다 그림
        //ListView는 보여지는 부분 + 알파만 한번 그림 + 필요한 경우 더 그림
        //리스트뷰는 보이는 부분만 로딩하기 때문에 스크롤 시 속도가 빠름

        //아이템 리스트 준비 제작
        val carList = ArrayList<CarforList>()
        for (i in 0 until 10){
            carList.add(CarforList(i.toString()+"번째 자동차"
                ,i.toString()+"번째 engine"))
        }

        val adapter = ListViewAdapter(carList,LayoutInflater.from(this@ListView))
        listView.adapter = adapter
        listView.setOnItemClickListener{
            parent,view,postion,id->
            val carName = (adapter.getItem(postion) as CarforList).name
            val carEngine = (adapter.getItem(postion) as CarforList).engine
            Toast.makeText(this@ListView, carName+", "+carEngine,Toast.LENGTH_SHORT).show()

        }

    }
}

class ListViewAdapter(
    val carForList: ArrayList<CarforList>,
    val layoutInflater :LayoutInflater
) : BaseAdapter(){
    override fun getCount(): Int {
        //그리고자 하는 아이템 리스트의 전체 수
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        //그리고자 하는 아이템 리스트의 하나(포지션에 해당하는)
        return carForList.get(position) //해당 인덱스에 해당하는 아이템 정보를 알려줌
    }

    override fun getItemId(position: Int): Long {
        //해당 포지션에 위치에 있는 아이템 뷰의 아이디 설정
        return position.toLong() //몇 번째인지 아이디 부여
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //아래 방식은 findviewbyid의 많은 작업 시간으로 비 효율적
//        val view = layoutInflater.inflate(R.layout.item_view,null)
//        val carNameTextView = view.findViewById<TextView>(R.id.car_name)
//        val carEngineTextView = view.findViewById<TextView>(R.id.car_engine)
//        carNameTextView.setText(carForList.get(position).name)
//        carEngineTextView.setText(carForList.get(position).engine)
//        return view
        //뷰 홀더를 이용하는 방식
        val view : View
        val holder : ViewHolder
        if (convertView==null){
            //convertView = 재활용 뷰 시간 복잡도를 줄이기 위함
            //태그 생성
            view = layoutInflater.inflate(R.layout.item_view,null)
            holder = ViewHolder()
            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)
            //홀더에 집어 넣음
            view.tag = holder //찾기 쉽게 태그를 지정
        }else{
            holder = convertView.tag as ViewHolder
            view = convertView
            //있다면 가져와서 씌움
        }
        holder.carName?.setText(carForList.get(position).name)
        holder.carEngine?.setText(carForList.get(position).engine)
        return view
    }


}
//findViewById는 많은 시간 차지 - viewholder 사용
class ViewHolder{
    var carName : TextView? = null
    var carEngine : TextView? = null

}