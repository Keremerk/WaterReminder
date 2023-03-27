package com.example.waterreminder.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.R
import com.example.waterreminder.model.ReminderModel

class ReminderAdapterRV(private val dataSet : MutableList<ReminderModel>):
    RecyclerView.Adapter<ReminderAdapterRV.ViewHolder>() {
    var onClick : ((Boolean) -> Unit)? = null
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val switch : Switch = view.findViewById(R.id.switchReminder)
        val textView : TextView = view.findViewById(R.id.alarmTimeTV)

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reminder_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val item = dataSet[position]
        holder.textView.text = item.reminderText

        holder.switch.setOnClickListener {
            onClick?.invoke(holder.switch.isChecked)
        }


    }

    override fun getItemCount() : Int {
        return dataSet.size
    }

}