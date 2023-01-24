package com.example.whowherewhen.manager.taskgroup.details.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedGroupEmployeeData
import com.example.whowherewhen.data.TaskTimeData


class ManView_TaskGroView_DetView_EmpView_TimeView : Fragment() {

    lateinit var data: ArrayList<TaskTimeData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_gro_view_det_view_emp_view_time_view, container, false)

        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        data = db.getEmployeeTaskGroupTimes(keeper.getEmployeeId(), keeper.getTaskGroupId())

        val recycler = view.findViewById<RecyclerView>(R.id.ManView_TaskGroView_DetView_EmpView_TimeView_RecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ManView_TaskGroView_DetView_EmpView_TimeView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<ImageButton>(R.id.ManView_TaskGroView_DetView_EmpView_TimeView_Return)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetView_EmpView_TimeView_to_ManView_TaskGroView_DetailsView)
        }

    }
}