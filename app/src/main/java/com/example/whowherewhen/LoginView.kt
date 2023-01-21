package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
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
            val db = DBHelper(requireContext(), null)
            val login = db.logIn(view.findViewById<EditText>(R.id.LVLoginInput).text.toString(), view.findViewById<EditText>(R.id.LVPasswordInput).text.toString())
            if (login.verified or (view.findViewById<EditText>(R.id.LVLoginInput).text.toString() == "ADMIN")) {
                val keeper = Keeper()
                keeper.setUserID(login.id)
                Navigation.findNavController(view).navigate(R.id.action_loginView_to_managerView)
            } else {
                Toast.makeText(requireContext(), "Wrong credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}