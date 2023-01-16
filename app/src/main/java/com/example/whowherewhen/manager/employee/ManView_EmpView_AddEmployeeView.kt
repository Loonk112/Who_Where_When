package com.example.whowherewhen.manager.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.R


class ManView_EmpView_AddEmployeeView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view_emp_view_add_employee_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameIn = view.findViewById<EditText>(R.id.ManView_EmpView_AddEmpView_NameInput)
        val surnameIn = view.findViewById<EditText>(R.id.ManView_EmpView_AddEmpView_SurnameInput)
        val loginIn = view.findViewById<EditText>(R.id.ManView_EmpView_AddEmpView_LoginInput)
        val passOneIn = view.findViewById<EditText>(R.id.ManView_EmpView_AddEmpView_PasswordOneInput)
        val passTwoIn = view.findViewById<EditText>(R.id.ManView_EmpView_AddEmpView_PasswordTwoInput)

        val radioEmployee = view.findViewById<RadioButton>(R.id.ManView_EmpView_AddEmpView_RadioEmployee)
        val radioManager = view.findViewById<RadioButton>(R.id.ManView_EmpView_AddEmpView_RadioManager)

        val cancel = view.findViewById<Button>(R.id.ManView_EmpView_AddEmpView_Cancel)
        cancel.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_EmpView_AddEmployeeView_to_managerView)
        }

        val confirm = view.findViewById<Button>(R.id.ManView_EmpView_AddEmpView_Confirm)
        confirm.setOnClickListener {
            if (nameIn.text.isEmpty() or surnameIn.text.isEmpty() or !(radioEmployee.isChecked or radioManager.isChecked) or loginIn.text.isEmpty() or passOneIn.text.isEmpty() or passTwoIn.text.isEmpty()) {
                Toast.makeText(view.context, "All fields must be filled and permission selected to add new employee", Toast.LENGTH_LONG).show()
            } else {
                if (passOneIn.text.toString() != passTwoIn.text.toString()) {
                    Toast.makeText(view.context, "Passwords must match to add employee", Toast.LENGTH_SHORT).show()
                } else {
                    val db = DBHelper(view.context, null)
                    if (radioEmployee.isChecked) {
                        db.addEmployee(nameIn.text.toString(), surnameIn.text.toString(), "Employee")
                    } else {
                        db.addEmployee(nameIn.text.toString(), surnameIn.text.toString(), "Manager")
                    }
                    db.addPassword(db.getLastAddedEmployeeID(), loginIn.text.toString(), passOneIn.text.toString())
                    Navigation.findNavController(view).navigate(R.id.action_ManView_EmpView_AddEmployeeView_to_managerView)
                }
            }
        }

        //TODO
    }
}