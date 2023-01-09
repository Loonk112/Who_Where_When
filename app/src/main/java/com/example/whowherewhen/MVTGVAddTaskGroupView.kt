package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        //TODO

    }
}