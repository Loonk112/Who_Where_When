package com.example.whowherewhen.manager.taskgroup.details.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.whowherewhen.R
import com.example.whowherewhen.data.EmployeeData

class ManView_TaskGroView_DetView_EmpView_AddEmpView_SpinnerAdapter(ctx: Context, employeeList : ArrayList<EmployeeData>) : ArrayAdapter<EmployeeData>(ctx, 0, employeeList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.manager_spinner_employee_adapter_template, parent, false)

        list?.let {
            val idTV = view.findViewById<TextView>(R.id.ManSpi_Emp_Id)
            val nameTV = view.findViewById<TextView>(R.id.ManSpi_Emp_Name)
            val surnameTV = view.findViewById<TextView>(R.id.ManSpi_Emp_Surname)

            idTV.text = list.id.toString()
            nameTV.text = list.name
            surnameTV.text = list.surname
        }

        return view
    }
}