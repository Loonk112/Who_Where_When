package com.example.whowherewhen.employee.taskgroup.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EmpView_TaskGroView_TaskView_Time : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_emp_view__task_gro_view__task_view__time,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnBtn = view.findViewById<ImageButton>(R.id.EmpView_TaskGroView_TaskView_Time_Return)
        returnBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_empView_TaskGroView_TaskView_Time_to_empView_TaskGroView_TasksView)
        }

        val keeper = Keeper()
        val db = DBHelper(requireContext(), null)
        val FAB = view.findViewById<FloatingActionButton>(R.id.EmpView_TaskGroView_TaskView_Time_FAB)
        if (db.checkIfInProgress(keeper.getUserID(), keeper.getTaskId())) {
            FAB.setImageResource(R.drawable.ic_baseline_pause_24)
        }

        FAB.setOnClickListener {
            if (db.checkIfInProgress(keeper.getUserID(), keeper.getTaskId())) {
                db.updateTime(keeper.getUserID(), keeper.getTaskId())
                FAB.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            } else {
                db.addTime(keeper.getUserID(), keeper.getTaskId())
                FAB.setImageResource(R.drawable.ic_baseline_pause_24)
            }
        }

        view.findViewById<TextView>(R.id.EmpView_TaskGroView_TaskView_Time_Title).text = "${db.getLogin(keeper.getUserID())} : ${db.getGroupName(keeper.getTaskGroupId())} : ${db.getTaskName(keeper.getTaskId())}"
    }
}