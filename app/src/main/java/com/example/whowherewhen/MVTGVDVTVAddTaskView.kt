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


class MVTGVDVTVAddTaskView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_v_t_g_v_d_v_t_v_add_task_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelButton = view.findViewById<Button>(R.id.MVTGVDVTVATVCancel)
        cancelButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDVTVAddTaskView_to_MVTGVDetailsView)
        }

        val input = view.findViewById<EditText>(R.id.MVTGVDVTVATVNameInput)
        val confirmButton = view.findViewById<Button>(R.id.MVTGVDVTVATVConfirm)
        confirmButton.setOnClickListener {
            if (input.text.isEmpty()) {
                Toast.makeText(context, "Name must be filled to add Task", Toast.LENGTH_SHORT).show()
            } else {
                val db = DBHelper(requireContext(), null)
                val keeper = Keeper()
                db.addTaskToGroup(keeper.getTaskGroupId(), input.text.toString())
            }
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDVTVAddTaskView_to_MVTGVDetailsView)
        }
    }
}