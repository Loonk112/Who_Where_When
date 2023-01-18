package com.example.whowherewhen.manager.employee.details.taskgroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.TaskGroupData
import com.example.whowherewhen.manager.taskgroup.details.employee.ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter
import com.example.whowherewhen.manager.taskgroup.details.task.ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManView_EmpView_DetView_TaskGroupView : Fragment() {

    lateinit var data: ArrayList<TaskGroupData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view__emp_view__det_view__task_group_view, container, false)

        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        data = db.getEmployeesTaskGroups(keeper.getEmployeeId())

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_EmpView_DetView_TaskGroView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addNewFAB = view.findViewById<FloatingActionButton>(R.id.ManView_EmpView_DetView_TaskGroView_FloatingActionButton)
        addNewFAB.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_EmpView_DetailsView_to_manView_EmpView_DetView_TaskGropView_AddTaskGroup)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_EmpView_DetView_TaskGroView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_TaskGroView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.id.toString().uppercase() }
            recycler.adapter = ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_TaskGroView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.toString().uppercase() }
            recycler.adapter = ManView_EmpView_DetView_TaskGroView_RecyclerViewAdapter(data)
        }
    }
}