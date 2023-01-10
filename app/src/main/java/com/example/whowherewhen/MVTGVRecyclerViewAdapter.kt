package com.example.whowherewhen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class MVTGVRecyclerViewAdapter (private val taskGroupList: ArrayList<TaskGroupData>) : RecyclerView.Adapter<MVTGVRecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.MVTGVRVITDeleteTaskGroup)!!
        val idView = view.findViewById<TextView>(R.id.MVTGVRVITTaskGroupId)!!
        val nameView = view.findViewById<TextView>(R.id.MVTGVRVITTaskGroupName)!!
        val detailBtn = view.findViewById<ImageButton>(R.id.MVTGVRVITTaskGroupDetails)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVTGVRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.m_v_t_g_v_recycler_view_item_template, parent, false)
        return MVTGVRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MVTGVRecyclerViewAdapter.ViewHolder, position: Int) {

        holder.idView.text = taskGroupList[position].id.toString()
        holder.nameView.text = taskGroupList[position].name

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteTaskGroup(taskGroupList[position].id)
            taskGroupList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.detailBtn.setOnClickListener {
            //TODO: Assistant class/object
            Navigation.findNavController(holder.view).navigate(R.id.action_managerView_to_MVTGVDetailsView)
        }
    }

    override fun getItemCount(): Int {
        return taskGroupList.size
    }
}