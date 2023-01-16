package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation


class ManView_TaskGroView_DetView_TaskView_AddTaskView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view_task_gro_view_det_view_task_view_add_task_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelButton = view.findViewById<Button>(R.id.ManView_TaskGroView_DetView_TaskView_AddTaskView_Cancel)
        cancelButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetView_TaskView_AddTaskView_to_ManView_TaskGroView_DetailsView)
        }

        val input = view.findViewById<EditText>(R.id.ManView_TaskGroView_DetView_TaskView_AddTaskView_NameInput)
        val confirmButton = view.findViewById<Button>(R.id.ManView_TaskGroView_DetView_TaskView_AddTaskView_Confirm)
        confirmButton.setOnClickListener {
            if (input.text.isEmpty()) {
                Toast.makeText(context, "Name must be filled to add Task", Toast.LENGTH_SHORT).show()
            } else {
                val db = DBHelper(requireContext(), null)
                val keeper = Keeper()
                db.addTaskToGroup(keeper.getTaskGroupId(), input.text.toString())
            }
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetView_TaskView_AddTaskView_to_ManView_TaskGroView_DetailsView)
        }
    }
}