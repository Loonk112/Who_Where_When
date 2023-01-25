package com.example.whowherewhen.manager.employee.details.taskgroup

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
import com.example.whowherewhen.data.TaskGroupData

class ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter (private val taskGroupList: ArrayList<ExtendedTaskGroupData>) : RecyclerView.Adapter<ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskGroTemp_DeleteTaskGroup)!!
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupName)!!
        val timeView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupTime)!!
        val detailsBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskGroTemp_TaskGroupDetails)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_group_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idView.text = taskGroupList[position].id.toString()
        holder.nameView.text = taskGroupList[position].name
        holder.timeView.text = "${taskGroupList[position].time/3600}:${(taskGroupList[position].time/60)%60}"

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteGroupWorker(taskGroupList[position].id)
            taskGroupList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.detailsBtn.setOnClickListener {
            val keeper = Keeper()
            keeper.setTaskGroupId(taskGroupList[position].id)
            Navigation.findNavController(holder.view).navigate(R.id.action_ManView_EmpView_DetailsView_to_manView_EmpView_DetView_TaskGroView_DetailsView)
        }
    }

    override fun getItemCount(): Int {
        return taskGroupList.size
    }
}