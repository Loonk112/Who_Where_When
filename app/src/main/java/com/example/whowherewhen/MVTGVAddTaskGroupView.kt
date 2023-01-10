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


class MVTGVAddTaskGroupView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_v_t_g_v_add_task_group_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancel = view.findViewById<Button>(R.id.MVTGVATGVCancel)
        cancel.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVAddTaskGroupView_to_managerView)
        }

        val nameInput = view.findViewById<EditText>(R.id.MVTGVATGVTaskGroupNameInput)

        val confirmButton = view.findViewById<Button>(R.id.MVTGVATGVConfirm)
        confirmButton.setOnClickListener {
            if (nameInput.text.isEmpty()) {
                Toast.makeText(context, "Name must be filled to add Task Group", Toast.LENGTH_SHORT).show()
            } else {
                val db = DBHelper(requireContext(), null)
                db.addTaskGroup(nameInput.text.toString())
                Navigation.findNavController(view).navigate(R.id.action_MVTGVAddTaskGroupView_to_managerView)
            }
        }

    }
}