package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarStateAdapter (fragmentActivity:FragmentActivity)
    :FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    //무한 반복을 위함 infinite view
    val firstFragmentPosition = pageCount/2

    override fun getItemCount(): Int =pageCount

    override fun createFragment(position: Int): Fragment {
        val calendarFragment = CalendarFragment()
        calendarFragment.pageIndex = position //월을 지정하는 값
        return calendarFragment
    }
}