package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//뷰 페이저에 나타낼 calendarFragment 의 어댑터
class CalendarStateAdapter (fragmentActivity:FragmentActivity)
    :FragmentStateAdapter(fragmentActivity) {
    @RequiresApi(Build.VERSION_CODES.O)

    //현재 달에 대한 데이터를 보내줌
    val dateMonth: Int = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM")).toInt()

    private var pageCount = Int.MAX_VALUE
    //무한 반복을 위함 infinite view
    val firstFragmentPosition = pageCount/2

    override fun getItemCount(): Int =pageCount

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        val calendarFragment = CalendarFragment(dateMonth)
        calendarFragment.pageIndex = position //월을 지정하는 값
        return calendarFragment
    }
}