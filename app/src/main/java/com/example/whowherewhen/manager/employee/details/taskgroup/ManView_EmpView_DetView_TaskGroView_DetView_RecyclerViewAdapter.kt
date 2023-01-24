package com.example.whowherewhen.manager.employee.details.taskgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.R
import com.example.whowherewhen.data.TaskTimeData
import java.util.*
import kotlin.collections.ArrayList

class ManView_EmpView_DetView_TaskGroView_DetView_RecyclerViewAdapter(private val taskList: ArrayList<TaskTimeData>) : RecyclerView.Adapter<ManView_EmpView_DetView_TaskGroView_DetView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskGroupTime_TaskId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskGroupTime_TaskName)!!
        val startView = view.findViewById<TextView>(R.id.ManRecView_TaskGroupTime_TimeStart)!!
        val stopView = view.findViewById<TextView>(R.id.ManRecView_TaskGroupTime_TimeStop)!!
        val sumView = view.findViewById<TextView>(R.id.ManRecView_TaskGroupTime_TimeSum)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_group_time_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var calendar = Calendar.getInstance(TimeZone.getDefault())

        holder.idView.text = taskList[position].id.toString()
        holder.nameView.text = taskList[position].name
        calendar.setTimeInMillis(taskList[position].timeStart * 1000)
        holder.startView.text = calendarFormatter(calendar)
        calendar.setTimeInMillis(taskList[position].timeStop * 1000)
        holder.stopView.text = calendarFormatter(calendar)
        holder.sumView.text = "${taskList[position].timeSum/3600}:${(taskList[position].timeSum/60)%60}"
    }

    fun calendarFormatter (calendar: Calendar): String {
        var string = "${calendar.get(Calendar.YEAR)}."
        if (calendar.get(Calendar.MONTH)+1 < 10) {
            string += "0"
        }
        string += "${calendar.get(Calendar.MONTH)+1}."
        if (calendar.get(Calendar.DAY_OF_MONTH)+1 < 10) {
            string += "0"
        }
        string += "${calendar.get(Calendar.DAY_OF_MONTH)} "
        if (calendar.get(Calendar.HOUR_OF_DAY)+1 < 10) {
            string += "0"
        }
        string += "${calendar.get(Calendar.HOUR_OF_DAY)+1}:"
        if (calendar.get(Calendar.MINUTE)+1 < 10) {
            string += "0"
        }
        string += "${calendar.get(Calendar.MINUTE)}:"
        if (calendar.get(Calendar.SECOND)+1 < 10) {
            string += "0"
        }
        string +="${calendar.get(Calendar.SECOND)}"
        return string
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}