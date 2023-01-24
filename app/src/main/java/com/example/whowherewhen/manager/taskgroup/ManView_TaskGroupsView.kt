package com.example.whowherewhen.manager.taskgroup

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
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedTaskGroupData
import com.example.whowherewhen.data.TaskGroupData
import com.example.whowherewhen.manager.employee.ManView_EmpView_RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ManView_TaskGroupsView : Fragment() {

    lateinit var data: ArrayList<ExtendedTaskGroupData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_groups_view, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)
        data = db.getExtendedTaskGroups()

        recycler.adapter = ManView_TaskGroView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newUser = view.findViewById<FloatingActionButton>(R.id.ManView_TaskGroView_FloatingActionButton)
        newUser.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_managerView_to_ManView_TaskGroView_AddTaskGroupView)
        }


        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.ManView_TaskGroView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.id.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.ManView_TaskGroView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_RecyclerViewAdapter(data)
        }
        val sortTime = view.findViewById<TextView>(R.id.ManView_TaskGroView_TextViewBtn_Time)
        sortName.setOnClickListener {
            data.sortBy { it.name.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_RecyclerViewAdapter(data)
        }

    }
}