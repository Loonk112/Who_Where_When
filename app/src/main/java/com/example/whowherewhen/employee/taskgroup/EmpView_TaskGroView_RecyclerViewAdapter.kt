package com.example.whowherewhen.employee.taskgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedTaskGroupData

class EmpView_TaskGroView_RecyclerViewAdapter(private val taskGroupList: ArrayList<ExtendedTaskGroupData>) : RecyclerView.Adapter<EmpView_TaskGroView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupName)!!
        val timeView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupTime)!!
        val detailsBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskGroTemp_TaskGroupDetails)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_group_template3, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keeper = Keeper()

        holder.idView.text = taskGroupList[position].id.toString()
        holder.nameView.text = taskGroupList[position].name
        holder.timeView.text = "${taskGroupList[position].time/3600}h ${taskGroupList[position].time/60}m"

        holder.detailsBtn.setOnClickListener {
            keeper.setTaskGroupId(taskGroupList[position].id)
            Navigation.findNavController(holder.view).navigate(R.id.action_employeeView_to_empView_TaskGroView_TasksView)
        }

    }

    override fun getItemCount(): Int {
        return taskGroupList.size
    }
}