package com.example.myapplication

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCalendarBinding
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

//각 달의 fragment
class CalendarFragment(private val dateMonth:Int) : Fragment() {
    private  var _binding: FragmentCalendarBinding?=null
    lateinit var mContext: Context
    // on create 와 on destroy 에서만 실행
    private val binding get() = _binding!!

    var pageIndex = 0
    lateinit var currentDate: Date
    lateinit var calendarAdapter: CalendarAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as CalendarMainActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView(){
        pageIndex -=(Int.MAX_VALUE/ 2)
        val date = Calendar.getInstance().run {
            add(Calendar.MONTH,pageIndex)
            time
        }
        currentDate = date

        //상단 데이터 적용
        val datetime:String=SimpleDateFormat(
            "yyyy년 MM월",Locale.KOREA
        ).format(date.time)
        binding.root.frag_calender_YYYY_XX.text = datetime
        calendarAdapter = CalendarAdapter(mContext,binding.root.frag_calender_linear,
            currentDate,dateMonth)
        binding.root.frag_calender_recycler.apply {
            adapter = calendarAdapter
        }
    }

}

//나중에 사용 - 뷰에 나타낼 서버
class CalendarViewModel : ViewModel(){
    var dayData = ArrayList<Int>()

}