package com.example.whowherewhen.employee.taskgroup

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
import com.example.whowherewhen.data.EmployeeData
import com.example.whowherewhen.data.ExtendedTaskGroupData
import com.example.whowherewhen.manager.employee.ManView_EmpView_RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EmpView_TaskGroupView : Fragment() {

    lateinit var data: ArrayList<ExtendedTaskGroupData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_emp_view__task_group_view, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_EmpView_TaskGroView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)
        val keeper = Keeper()
        data = db.getExtendedUserTaskGroups(keeper.getUserID())

        recycler.adapter = EmpView_TaskGroView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.ManView_EmpView_TaskGroView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.ManView_EmpView_TaskGroView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.id }
            recycler.adapter = EmpView_TaskGroView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.ManView_EmpView_TaskGroView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.uppercase() }
            recycler.adapter = EmpView_TaskGroView_RecyclerViewAdapter(data)
        }
        val sortTime = view.findViewById<TextView>(R.id.ManView_EmpView_TaskGroView_TextViewBtn_Time)
        sortTime.setOnClickListener {
            data.sortBy { it.time }
            recycler.adapter = EmpView_TaskGroView_RecyclerViewAdapter(data)
        }
    }
}