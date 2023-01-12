package com.example.whowherewhen

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


class MVEVAddEmployeeView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_v_e_v_add_employee_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameIn = view.findViewById<EditText>(R.id.MVEVAEVNameInput)
        val surnameIn = view.findViewById<EditText>(R.id.MVEVAEVSurnameInput)
        val loginIn = view.findViewById<EditText>(R.id.MVEVAEVLoginInput)
        val passOneIn = view.findViewById<EditText>(R.id.MVEVAEVPasswordOneInput)
        val passTwoIn = view.findViewById<EditText>(R.id.MVEVAEVPasswordTwoInput)

        val radioEmployee = view.findViewById<RadioButton>(R.id.MVEVAEVRadioEmployee)
        val radioManager = view.findViewById<RadioButton>(R.id.MVEVAEVRadioManager)

        val cancel = view.findViewById<Button>(R.id.MVEVAEVCancel)
        cancel.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVEVAddEmployeeView_to_managerView)
        }

        val confirm = view.findViewById<Button>(R.id.MVEVAEVConfirm)
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
                    Navigation.findNavController(view).navigate(R.id.action_MVEVAddEmployeeView_to_managerView)
                }
            }
        }

        //TODO
    }
}