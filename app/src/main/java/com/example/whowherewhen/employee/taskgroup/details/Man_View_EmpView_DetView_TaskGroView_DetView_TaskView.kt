package com.example.whowherewhen.employee.taskgroup.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whowherewhen.R


class Man_View_EmpView_DetView_TaskGroView_DetView_TaskView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_man__view__emp_view__det_view__task_gro_view__det_view__task_view,
            container,
            false
        )
    }

}