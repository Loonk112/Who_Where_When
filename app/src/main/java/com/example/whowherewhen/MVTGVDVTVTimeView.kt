package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.Navigation

class MVTGVDVTVTimeView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_v_t_g_v_d_v_t_v_time_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<ImageButton>(R.id.MVTGVDVTVTVReturn)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDVTVTimeView_to_MVTGVDetailsView)
        }
    }
}