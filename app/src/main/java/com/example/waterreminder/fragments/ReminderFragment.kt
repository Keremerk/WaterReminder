@file:Suppress("DEPRECATION")

package com.example.waterreminder.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterreminder.adapter.ReminderAdapterRV
import com.example.waterreminder.alarms.AlarmReceiver
import com.example.waterreminder.databinding.FragmentReminderBinding
import com.example.waterreminder.model.ReminderModel
import com.example.waterreminder.utils.ReminderObjects
import java.util.*

class ReminderFragment : Fragment() {
    private lateinit var binding: FragmentReminderBinding
    private var calendar = Calendar.getInstance()
    private lateinit var reminderAdapterRV: ReminderAdapterRV

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReminderBinding.inflate(inflater, container, false)

        binding.apply {
            backButtonIV.setOnClickListener { findNavController().navigateUp() }
            addReminderButton.setOnClickListener { showTimePickerDialog() }

            recyclerViewReminder.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            reminderAdapterRV = ReminderAdapterRV(ReminderObjects.reminderAdapterList)
            recyclerViewReminder.adapter = reminderAdapterRV

        }
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showTimePickerDialog() {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minuteOfHour ->
            val amPm = if (hourOfDay < 12) "AM" else "PM"
            val selectedTime = String.format(
                "%02d:%02d %s",
                if (hourOfDay > 12) hourOfDay - 12 else hourOfDay,
                minuteOfHour,
                amPm
            )
            val newReminder = ReminderModel(selectedTime)
            ReminderObjects.reminderAdapterList.add(newReminder)
            reminderAdapterRV.notifyDataSetChanged()
            setAlarm(hourOfDay, minuteOfHour)
        }, hour, minute, false)

        timePickerDialog.show()
    }

    private fun setAlarm(hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra("vibrator", true)

        val requestCode = 0
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getBroadcast(requireContext(), requestCode, intent, flags)
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        reminderAdapterRV.onClick = {
            if (it) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            } else {
                alarmManager.cancel(pendingIntent)
            }
        }
    }
}
