package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.myapplication.databinding.FragmentCalendarBinding
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalendarFragment : Fragment() {
    private  var _binding:FragmentCalendarBinding?=null
    lateinit var mContext: Context
    // oncreate와 ondestroy에서만 실행
    private val binding get() = _binding!!
    var pageIndex = 0
    lateinit var currentDate: Date

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initIndex()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun initIndex(){
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
    }

}

class CalendarViewModel : ViewModel(){
    var dayData = ArrayList<Int>()

}