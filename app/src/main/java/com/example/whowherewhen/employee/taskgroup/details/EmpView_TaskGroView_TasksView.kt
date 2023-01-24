package com.example.whowherewhen.employee.taskgroup.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedTaskData
import com.example.whowherewhen.data.ExtendedTaskGroupData
import com.example.whowherewhen.employee.taskgroup.EmpView_TaskGroView_RecyclerViewAdapter


class EmpView_TaskGroView_TasksView : Fragment() {

    lateinit var data: ArrayList<ExtendedTaskData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_emp_view__task_gro_view__tasks_view, container, false )

        val recycler = view.findViewById<RecyclerView>(R.id.EmpView_TaskGroView_TaskView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)
        val keeper = Keeper()
        data = db.getEmployeeExtendedTaskGroupTasks(keeper.getTaskGroupId(), keeper.getUserID())

        recycler.adapter = EmpView_TaskGroView_TaskView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnBtn = view.findViewById<ImageButton>(R.id.EmpView_TaskGroView_TaskView_ReturnBtn)
        returnBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_empView_TaskGroView_TasksView_to_employeeView)
        }

        val recycler = view.findViewById<RecyclerView>(R.id.EmpView_TaskGroView_TaskView_RecyclerView)

        val sortID = view.findViewById<TextView>(R.id.EmpView_TaskGroView_TaskView_TextViewBtn_ID)
        sortID.setOnClickListener {
            data.sortBy { it.id }
            recycler.adapter = EmpView_TaskGroView_TaskView_RecyclerViewAdapter(data)
        }
        val sortName = view.findViewById<TextView>(R.id.EmpView_TaskGroView_TaskView_TextViewBtn_Name)
        sortName.setOnClickListener {
            data.sortBy { it.name.uppercase() }
            recycler.adapter = EmpView_TaskGroView_TaskView_RecyclerViewAdapter(data)
        }
        val sortTime = view.findViewById<TextView>(R.id.EmpView_TaskGroView_TaskView_TextViewBtn_Time)
        sortTime.setOnClickListener {
            data.sortBy { it.time }
            recycler.adapter = EmpView_TaskGroView_TaskView_RecyclerViewAdapter(data)
        }

        val keeper = Keeper()
        val db = DBHelper(requireContext(), null)

        view.findViewById<TextView>(R.id.EmpView_TaskGroView_TaskView_Title).text = "${db.getLogin(keeper.getUserID())} : ${db.getGroupName(keeper.getTaskGroupId())}"

    }

}