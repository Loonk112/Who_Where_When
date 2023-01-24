package com.example.whowherewhen.manager.taskgroup.details.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.R
import com.example.whowherewhen.data.EmployeeTimeData
import java.util.*
import kotlin.collections.ArrayList

class ManView_TaskGroView_DetView_TaskView_TimeView_RecyclerView(private val employeeList: ArrayList<EmployeeTimeData>) : RecyclerView.Adapter<ManView_TaskGroView_DetView_TaskView_TimeView_RecyclerView.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val idView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_Id)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_Name)!!
        val surnameView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_Surname)!!
        val startView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_TimeStart)!!
        val stopView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_TimeStop)!!
        val sumView = view.findViewById<TextView>(R.id.ManRecView_EmployeeTime_TimeSum)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_employee_time_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var calendar = Calendar.getInstance(TimeZone.getDefault())

        holder.idView.text = employeeList[position].id.toString()
        holder.nameView.text = employeeList[position].name
        holder.surnameView.text = employeeList[position].surname
        calendar.setTimeInMillis(employeeList[position].timeStart * 1000)
        holder.startView.text = calendarFormatter(calendar)
        calendar.setTimeInMillis(employeeList[position].timeStop * 1000)
        holder.stopView.text = calendarFormatter(calendar)
        holder.sumView.text = "${employeeList[position].timeSum/3600}:${(employeeList[position].timeSum/60)%60}"
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
        return employeeList.size
    }
}