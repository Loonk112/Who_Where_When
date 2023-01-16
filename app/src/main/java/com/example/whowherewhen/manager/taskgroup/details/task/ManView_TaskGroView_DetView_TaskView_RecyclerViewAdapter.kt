package com.example.whowherewhen.manager.taskgroup.details.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.TaskData

class ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter (private val taskList: ArrayList<TaskData>) : RecyclerView.Adapter<ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskTemp2_DeleteTask)!!
        val idView = view.findViewById<TextView>(R.id.ManRecView_TaskTemp2_TaskId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_TaskTemp2_TaskName)!!
        val status = view.findViewById<Button>(R.id.ManRecView_TaskTemp2_Status)!!
        val timeBtn = view.findViewById<ImageButton>(R.id.ManRecView_TaskTemp2_TaskTime)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_template_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = DBHelper(holder.view.context, null)

        holder.idView.text = taskList[position].id.toString()
        holder.nameView.text = taskList[position].name
        holder.status.text = taskList[position].status.toString()
        if (taskList[position].status == 0) {
            holder.status.text = "In progress"
        } else {
            holder.status.text = "Done"
        }

        holder.deleteBtn.setOnClickListener {
            db.deleteTask(taskList[position].id)
            taskList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.timeBtn.setOnClickListener {
            val keeper = Keeper()
            keeper.setTaskId(taskList[position].id)
            Navigation.findNavController(holder.view).navigate(R.id.action_ManView_TaskGroView_DetailsView_to_ManView_TaskGroView_DetView_TaskView_TimeView)
        }

        holder.status.setOnClickListener {
            if (taskList[position].status == 0) {
                taskList[position].status = 1
                holder.status.text = "Done"
            } else {
                taskList[position].status = 0
                holder.status.text = "In progress"
            }
            db.changeTaskStatus(taskList[position].id, taskList[position].status)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}