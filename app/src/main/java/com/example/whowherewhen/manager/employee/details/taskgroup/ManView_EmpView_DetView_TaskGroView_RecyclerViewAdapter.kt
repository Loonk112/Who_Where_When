package com.example.whowherewhen.manager.employee.details.taskgroup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.TaskGroupData

class ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter (private val taskGroupList: ArrayList<TaskGroupData>) : RecyclerView.Adapter<ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskGroTemp_DeleteTaskGroup)!!
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskGroTemp_TaskGroupName)!!
        val timeBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskGroTemp_TaskGroupTime)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_group_template2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idView.text = taskGroupList[position].id.toString()
        holder.nameView.text = taskGroupList[position].name

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteGroupWorker(taskGroupList[position].id)
            taskGroupList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.timeBtn.setOnClickListener {
            val keeper = Keeper()
            keeper.setTaskGroupId(taskGroupList[position].id)
            //TODO:
        }
    }

    override fun getItemCount(): Int {
        return taskGroupList.size
    }
}