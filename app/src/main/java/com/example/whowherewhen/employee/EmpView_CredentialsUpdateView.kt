package com.example.whowherewhen.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R


class EmpView_CredentialsUpdateView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_emp_view__credentials_update_view,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val oldPassIn = view.findViewById<TextView>(R.id.EmpView_CreUpdView_OldPassIn)
        val newPassIn = view.findViewById<TextView>(R.id.EmpView_CreUpdView_NewPassIn)
        val newPassIn2 = view.findViewById<TextView>(R.id.EmpView_CreUpdView_NewPassIn2)
        val confirmPass = view.findViewById<TextView>(R.id.EmpView_CreUpdView_ConfirmBtn)

        val keeper = Keeper();
        val db = DBHelper(requireContext(), null)



        confirmPass.setOnClickListener {
            if (db.verifyPassword(keeper.getUserID(), oldPassIn.text.toString())) {
                if (newPassIn.text.isNotEmpty() and newPassIn2.text.isNotEmpty() and (newPassIn.text.toString() == newPassIn2.text.toString())) {
                    db.changePassword(keeper.getUserID(), oldPassIn.text.toString(), newPassIn.text.toString())
                    oldPassIn.setText("");
                    newPassIn.setText("");
                    newPassIn2.setText("");
                    Toast.makeText(view.context, "Password changed" , Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "New passwords must match" , Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(view.context, "Wrong password" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}