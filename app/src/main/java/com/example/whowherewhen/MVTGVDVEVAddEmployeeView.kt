package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class MVTGVDVEVAddEmployeeView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_m_v_t_g_v_d_v_e_v_add_employee_view,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelButton = view.findViewById<Button>(R.id.MVTGVDVEVAEVCancel)
        cancelButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDVEVAddEmployeeView_to_MVTGVDetailsView)
        }
    }
}