package com.example.whowherewhen.employee.taskgroup.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedTaskData

class EmpView_TaskGroView_TaskView_RecyclerViewAdapter(private val taskList: ArrayList<ExtendedTaskData>) : RecyclerView.Adapter<EmpView_TaskGroView_TaskView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskTemp_TaskId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskTemp_TaskName)!!
        val timeView = view.findViewById<TextView>(R.id.ManRecView_TaskTemp_TaskTime)!!
        val detailsBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskTemp_Record)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keeper = Keeper()

        holder.idView.text = taskList[position].id.toString()
        holder.nameView.text = taskList[position].name
        holder.timeView.text = "${taskList[position].time/3600}:${(taskList[position].time/60)%60}"

        if (taskList[position].status == 1) {
            holder.detailsBtn.isClickable = false
            holder.detailsBtn.isEnabled = false
            holder.detailsBtn.setImageResource(R.drawable.ic_baseline_check_24)
        } else {
            holder.detailsBtn.setOnClickListener {
                keeper.setTaskId(taskList[position].id)
                Navigation.findNavController(holder.view).navigate(R.id.action_empView_TaskGroView_TasksView_to_empView_TaskGroView_TaskView_Time)
            }
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}