package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Data
import com.example.myapplication.databinding.ItemCalendarBinding

class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val data: Data
                      , private val dataSet:ArrayList<Int>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {
    inner class CalendarItemHolder(val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar,parent,false)
        return CalendarItemHolder(ItemCalendarBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        holder.binding.calendarText.text = dataSet[position].toString()
    }

    override fun getItemCount(): Int = dataSet.size
}
