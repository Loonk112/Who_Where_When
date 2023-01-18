package com.example.whowherewhen.manager.taskgroup.details.employee

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
import com.example.whowherewhen.data.ExtendedGroupEmployeeData
import com.example.whowherewhen.manager.taskgroup.details.task.ManView_TaskGroView_DetView_TaskView_RecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ManView_TaskGroView_DetView_EmployeesView : Fragment() {

    lateinit var data: ArrayList<ExtendedGroupEmployeeData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_gro_view_det_view_employees_view, container, false)
        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        data = db.getTaskGroupEmployees(keeper.getTaskGroupId())

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_DetView_EmpView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val addEmp = view.findViewById<FloatingActionButton>(R.id.ManView_TaskGroView_DetView_EmpView_FloatingActionButton)
        addEmp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetailsView_to_ManView_TaskGroView_DetView_EmpView_AddEmployeeView)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_DetView_EmpView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_EmpView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.emp_id.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_EmpView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter(data)
        }
        val sortSurname = view.findViewById<TextView>(R.id.ManView_TaskGroView_DetView_EmpView_TextViewBtn_Surname)
        sortSurname.setOnClickListener {
            data.sortBy { it.surname.toString().uppercase() }
            recycler.adapter = ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter(data)
        }
    }
}