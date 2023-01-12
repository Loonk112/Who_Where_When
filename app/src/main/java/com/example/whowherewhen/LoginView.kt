package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation


class LoginView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val confirmLogin = view.findViewById<ImageButton>(R.id.LVConfirm)
        confirmLogin.setOnClickListener {
            //TODO

            Navigation.findNavController(view).navigate(R.id.action_loginView_to_managerView)
        }
    }
}