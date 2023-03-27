package com.example.waterreminder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.R
import com.example.waterreminder.model.BottomModel

class BottomAdapterRV(
    var dataSet : List<BottomModel>,
    private val OnclickListener : (Int) -> Unit, ) :
    RecyclerView.Adapter<BottomAdapterRV.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val lockImageView : ImageView = view.findViewById(R.id.lockIV)
        val backgroundImageView : ImageView = view.findViewById(R.id.backgroundIV)
        val imageView : ImageView = view.findViewById(R.id.drinkTypeImageView)
        val textView : TextView = view.findViewById(R.id.drinkTypeTV)
        val textViewPercentage : TextView = view.findViewById(R.id.percantageTV)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.specific_drink, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() : Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val item = dataSet[position]
        holder.imageView.setImageResource(item.imageResIdBottom)
        holder.textView.text = item.textBottom
        holder.textViewPercentage.text = "%" + item.percentage.toString()

        holder.itemView.setOnClickListener {
            OnclickListener.invoke(position)
        }

        // if reklam alınırsa  item.isPremium=false

        if (item.isPremium) {
            holder.backgroundImageView.setImageResource(R.drawable.shadowed_background)
            holder.itemView.isClickable=false
            holder.lockImageView.visibility=VISIBLE
        } else {
            holder.backgroundImageView.setImageResource(R.drawable.bottom_rectangle_basic)
            holder.itemView.isClickable=true
            holder.lockImageView.visibility=INVISIBLE


        }
    }


}
