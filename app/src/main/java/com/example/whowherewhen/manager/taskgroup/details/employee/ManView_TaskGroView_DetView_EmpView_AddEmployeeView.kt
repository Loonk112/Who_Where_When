package com.example.whowherewhen.manager.taskgroup.details.employee

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


class ManView_TaskGroView_DetView_EmpView_AddEmployeeView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_man_view_task_gro_view_det_view_emp_view_add_employee_view,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.ManView_TaskGroView_DetView_EmpView_AddEmpView_Spinner)

        val db = DBHelper(requireContext(), null)
        val assist = Keeper()
        val data = db.getUnconnectedEmployees(assist.getTaskGroupId())

        val adapter = ManView_TaskGroView_DetView_EmpView_AddEmpView_SpinnerAdapter(requireContext(), data)

        spinner.adapter = adapter
        val cancelButton = view.findViewById<Button>(R.id.ManView_TaskGroView_DetView_EmpView_AddEmpView_Cancel)
        cancelButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetView_EmpView_AddEmployeeView_to_ManView_TaskGroView_DetailsView)
        }
        val confirmButton = view.findViewById<Button>(R.id.ManView_TaskGroView_DetView_EmpView_AddEmpView_Confirm)
        confirmButton.setOnClickListener {
            val keeper = Keeper()
            db.addGroupWorker(data[spinner.selectedItemId.toInt()].id, keeper.getTaskGroupId())
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetView_EmpView_AddEmployeeView_to_ManView_TaskGroView_DetailsView)
        }

        if (data.size < 1){
            confirmButton.isEnabled = false;
            confirmButton.isClickable = false;
        }
    }
}