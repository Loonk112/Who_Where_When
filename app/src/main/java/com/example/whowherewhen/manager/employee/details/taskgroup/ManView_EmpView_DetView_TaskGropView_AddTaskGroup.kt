package com.example.whowherewhen.manager.employee.details.taskgroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.Navigation
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.manager.taskgroup.details.employee.ManView_TaskGroView_DetView_EmpView_AddEmpView_SpinnerAdapter


class ManView_EmpView_DetView_TaskGropView_AddTaskGroup : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view__emp_view__det_view__task_grop_view__add_task_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.ManView_EmpView_DetView_TaskGroView_AddTaskGro_Spinner)

        val db = DBHelper(requireContext(), null)
        val assist = Keeper()
        val data = db.getUnconnectedTaskGroups(assist.getEmployeeId())

        val adapter = ManView_EmpView_DetView_TaskGroupView_SpinnerAdapter(requireContext(), data)

        spinner.adapter = adapter
        val cancelButton = view.findViewById<Button>(R.id.ManView_EmpView_DetView_TaskGroView_AddTaskGro_Cancel)
        cancelButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_manView_EmpView_DetView_TaskGropView_AddTaskGroup_to_ManView_EmpView_DetailsView)
        }
        val confirmButton = view.findViewById<Button>(R.id.ManView_EmpView_DetView_TaskGroView_AddTaskGro_Confirm)
        confirmButton.setOnClickListener {
            val keeper = Keeper()
            db.addGroupWorker(keeper.getEmployeeId(), data[spinner.selectedItemId.toInt()].id)
            Navigation.findNavController(view).navigate(R.id.action_manView_EmpView_DetView_TaskGropView_AddTaskGroup_to_ManView_EmpView_DetailsView)
        }

        if (data.size < 1){
            confirmButton.isEnabled = false;
            confirmButton.isClickable = false;
        }
    }
}