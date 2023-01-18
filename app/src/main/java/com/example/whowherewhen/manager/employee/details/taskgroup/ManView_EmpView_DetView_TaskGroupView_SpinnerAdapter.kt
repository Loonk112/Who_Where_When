package com.example.whowherewhen.manager.employee.details.taskgroup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.whowherewhen.R
import com.example.whowherewhen.data.TaskGroupData

class ManView_EmpView_DetView_TaskGroupView_SpinnerAdapter(ctx: Context, taskGroupList : ArrayList<TaskGroupData>) : ArrayAdapter<TaskGroupData>(ctx, 0, taskGroupList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.manager_spinner_task_group_adapter_template, parent, false)

        list?.let {
            val idTV = view.findViewById<TextView>(R.id.ManSpi_TaskGro_Id)
            val nameTV = view.findViewById<TextView>(R.id.ManSpi_TaskGro_Name)

            idTV.text = list.id.toString()
            nameTV.text = list.name
        }

        return view
    }
}