package com.example.whowherewhen.manager.taskgroup.details.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedTaskData
import com.example.whowherewhen.data.TaskData
import com.example.whowherewhen.manager.employee.ManView_EmpView_RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManView_TaskGroView_DetView_TasksView : Fragment() {

    lateinit var data: ArrayList<ExtendedTaskData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_gro_view_det_view_tasks_view, container, false)

        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        data = db.getExtendedTaskGroupTasks(keeper.getTaskGroupId())

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_DetView_TakView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newTask = view.findViewById<FloatingActionButton>(R.id.ManView_TaskGroView_DetView_TaskView_FloatingActionButton)
        newTask.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetailsView_to_ManView_TaskGroView_DetView_TaskView_AddTaskView)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_DetView_TakView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_TaskView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.id }
            recycler.adapter = ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_TaskView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.uppercase() }
            recycler.adapter = ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter(data)
        }
        val sortStatus = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_TaskView_TextViewBtn_Status)
        sortStatus.setOnClickListener {
            data.sortBy { it.status }
            recycler.adapter = ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter(data)
        }
        val sortTime = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_TaskView_TextViewBtn_Time)
        sortStatus.setOnClickListener {
            data.sortBy { it.time }
            recycler.adapter = ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter(data)
        }
    }
}