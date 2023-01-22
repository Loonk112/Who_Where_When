package com.example.whowherewhen.manager.employee.details

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


class ManView_EmpView_DetView_CredentialsChange : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view__emp_view__det_view__credentials_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val employeeLogin = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_CreCha_EmployeeLogin)
        val oldPassIn = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_CreCha_OldPassword)
        val newPassIn = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_CreCha_NewPassword)
        val newPassIn2 = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_CreCha_NewPassword2)
        val confirmPass = view.findViewById<TextView>(R.id.ManView_EmpView_DetView_CreCha_Confirm)

        val keeper = Keeper();
        val db = DBHelper(requireContext(), null)

        employeeLogin.text = db.getLogin(keeper.getEmployeeId())



        confirmPass.setOnClickListener {
            if (db.verifyPassword(keeper.getEmployeeId(), oldPassIn.text.toString())) {
                if (newPassIn.text.isNotEmpty() and newPassIn2.text.isNotEmpty() and (newPassIn.text.toString() == newPassIn2.text.toString())) {
                    db.changePassword(keeper.getEmployeeId(), oldPassIn.text.toString(), newPassIn.text.toString())
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

        if ((keeper.getUserID() != 0) and
            (
                (db.getEmployee(keeper.getEmployeeId()).perms.toString() == "Manager") and
                (keeper.getEmployeeId() != keeper.getUserID())
            )) {
            confirmPass.isClickable = false
            confirmPass.isEnabled = false
            oldPassIn.isClickable = false
            oldPassIn.isEnabled = false
            newPassIn.isClickable = false
            newPassIn.isEnabled = false
            newPassIn2.isClickable = false
            newPassIn2.isEnabled = false
        }

    }
}