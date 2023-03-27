package com.example.waterreminder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.R
import com.example.waterreminder.model.TopModel

class TopAdapterRV(private val dataSet : List<TopModel>, val OnclickListener : (Int) -> Unit) :
    RecyclerView.Adapter<TopAdapterRV.ViewHolder>() {
    var currentPosition = 0
    var selectedPositionTop = -1

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.findViewById(R.id.DrinkIV)
        val textView : TextView = view.findViewById(R.id.drinkTypeIVRV)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drink_types, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val item = dataSet[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.textView.text = item.text
        holder.imageView.isSelected = dataSet[holder.adapterPosition].isSelected

        holder.itemView.setOnClickListener {
            dataSet[currentPosition].isSelected = false
            dataSet[position].isSelected = true
            currentPosition = position
            OnclickListener(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() : Int {
        return dataSet.size
    }

    fun setSelectedPosition(position : Int) {
        val previousPosition = selectedPositionTop
        selectedPositionTop = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPositionTop)
    }
}