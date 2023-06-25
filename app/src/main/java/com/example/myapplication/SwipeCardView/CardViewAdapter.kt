package com.example.myapplication.SwipeCardView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CardViewBinding

class CardViewAdapter(
    private val activity: SwipeCardViewActivity,
    private val dataSet : ArrayList<MovieData>
) :RecyclerView.Adapter<CardViewAdapter.ViewHolder>(){
    private lateinit var binding : CardViewBinding

    inner class ViewHolder(
        binding: CardViewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun hold(){
            val item = dataSet[absoluteAdapterPosition]
            binding.name.text = item.name
            binding.dataX.text = item.date_x
            binding.genre.text = item.genre
            binding.score.text = item.score
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CardViewBinding.inflate(LayoutInflater.from(activity),
        parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hold()
    }

    fun delData(position: Int){
        dataSet.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,itemCount)
    }
}