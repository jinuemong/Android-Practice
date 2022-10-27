package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

//calendarFirst 에서 viewpager - calendarStateAdapter 로 CalendarFragment 에 연결
class CalendarFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.calendar_view_pager)
        val calendarStateAdapter = CalendarStateAdapter(requireActivity())
        viewPager.adapter = calendarStateAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        calendarStateAdapter.apply {
            //바로 현재 달로 이동해야 하므로 smooth = false
            viewPager.setCurrentItem(this.firstFragmentPosition,false)
        }
    }

}