package com.example.myapplication

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import com.example.myapplication.databinding.ItemCalendarBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// 일 수를 표현하기 위한 캘린더 내부 어댑터
// linearLayout : 높이를 구하기 위함 , date : customCalender에서 필요
class CalendarAdapter(
    val context: Context,
    private val calendarLayout: LinearLayout,
    private val date: Date,
    private val currentMonth : Int,
    ) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private var dataSet: ArrayList<Int> = arrayListOf()
    //커스텀 데이터로 현재 데이터셋을 리셋해줌
    private var customCalendar:CustomCalendar = CustomCalendar(date)
    init {
        customCalendar.initBaseCalendar()
        dataSet = customCalendar.dateList
    }

    inner class CalendarItemHolder(val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar,parent,false)
        return CalendarItemHolder(ItemCalendarBinding.bind(view))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        //텍스트 표시
        holder.binding.calendarText.text = dataSet[position].toString()

        //각 아이템의 높이 지정
        holder.binding.root.layoutParams.height = calendarLayout.height/6

        //오늘 날짜는 진하게
        val dateDay : String = SimpleDateFormat("dd",Locale.KOREA).format(date)
        val dateMonth : Int = SimpleDateFormat("MM",Locale.KOREA).format(date).toInt()
        //오늘 날짜 + 현재 달인 경우만
        if (dataSet[position]==dateDay.toInt() && currentMonth ==dateMonth){
            holder.binding.calendarText.apply {
                setTypeface(this.typeface,Typeface.BOLD_ITALIC)
            }
        }

        val firstDateIndex  = customCalendar.prevTail
        val lastDateIndex = dataSet.size - customCalendar.nextHead-1
        //다른 달인 경우 회색으로 표시
        if (position<firstDateIndex || position>lastDateIndex){
            holder.binding.calendarText.apply {
                setTextAppearance(R.style.grayColorText)
            }

        }
    }

    override fun getItemCount(): Int = dataSet.size
}
