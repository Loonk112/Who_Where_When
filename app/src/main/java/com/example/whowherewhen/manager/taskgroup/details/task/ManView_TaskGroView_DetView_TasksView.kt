package com.example.whowherewhen.manager.taskgroup.details.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManView_TaskGroView_DetView_TasksView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_gro_view_det_view_tasks_view, container, false)

        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        val data = db.getTaskGroupTasks(keeper.getTaskGroupId())

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
    }
}