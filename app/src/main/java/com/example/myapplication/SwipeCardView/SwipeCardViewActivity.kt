package com.example.myapplication.SwipeCardView

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySwipeCardViewBinding
import com.opencsv.CSVReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class SwipeCardViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySwipeCardViewBinding
    private lateinit var adapter : CardViewAdapter
    private var dataSet : ArrayList<MovieData>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivitySwipeCardViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSet = arrayListOf()
        loadData()
        setSwipe()
    }
    private fun loadData(){
        val assertManager = this@SwipeCardViewActivity.assets
        val inputStream = assertManager.open("imdb_movies.csv")
        val csvReader = CSVReader(InputStreamReader(inputStream,"EUC-KR"))

        val dataList =csvReader.readAll()
        for (data in dataList){
            dataSet!!.add(MovieData(
                data[0].toString(), // 이름
                data[1].toString(), // data_x
                data[2].toString(), // score
                data[3].toString() // genre
            ))
        }
        dataSet!!.removeAt(0)

        adapter = CardViewAdapter(this@SwipeCardViewActivity, dataSet!!)
        binding.recyclerView.adapter= adapter
    }

    private fun setSwipe(){
        // swipe 데이터 구현
        val swipeController = SwipeController()
        swipeController.apply {

            setButtonActionListener(object : SwipeControllerActions{
                override fun onLeftClicked(position: Int) {
                    super.onLeftClicked(position)
                }

                override fun onRightClicked(position: Int) {
                    adapter.delData(position)
                }
            })
        }
        val itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })

    }
}

