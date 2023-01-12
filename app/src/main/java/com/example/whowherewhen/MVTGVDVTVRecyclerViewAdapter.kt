package com.example.whowherewhen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class MVTGVDVTVRecyclerViewAdapter (private val taskList: ArrayList<TaskData>) : RecyclerView.Adapter<MVTGVDVTVRecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.MRVTVDeleteTask)!!
        val idView = view.findViewById<TextView>(R.id.MRVTVTaskId)!!
        val nameView = view.findViewById<TextView>(R.id.MRVTVTaskName)!!
        val status = view.findViewById<Button>(R.id.MRVTVStatus)!!
        val timeBtn = view.findViewById<ImageButton>(R.id.MRVTVTaskTime)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_task_template, parent, false)
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
            Navigation.findNavController(holder.view).navigate(R.id.action_MVTGVDetailsView_to_MVTGVDVTVTimeView)
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