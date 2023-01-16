package com.example.whowherewhen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class ManView_EmpView_RecyclerViewAdapter (private val employeeList: ArrayList<EmployeeData>) : RecyclerView.Adapter<ManView_EmpView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.ManRecView_EmpTemp_DeleteEmployee)!!
        val idView = view.findViewById<TextView>(R.id.ManRecView_EmpTemp_EmployeeId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecView_EmpTemp_EmployeeName)!!
        val surnameView = view.findViewById<TextView>(R.id.ManRecView_EmpTemp_EmployeeSurname)!!
        val permBtn = view.findViewById<Button>(R.id.ManRecView_EmpTemp_EmployeePerm)!!
        val detailsBtn = view.findViewById<ImageButton>(R.id.ManRecView_EmpTemp_EmployeeDetails)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_employee_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = DBHelper(holder.view.context, null)
        val keeper = Keeper()

        holder.idView.text = employeeList[position].id.toString()
        holder.nameView.text = employeeList[position].name
        holder.surnameView.text = employeeList[position].surname
        holder.permBtn.text = employeeList[position].perms

        holder.deleteBtn.setOnClickListener {
            db.deleteEmployee(employeeList[position].id)
            employeeList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.detailsBtn.setOnClickListener {
            keeper.setEmployeeId(employeeList[position].id)
            Navigation.findNavController(holder.view).navigate(R.id.action_managerView_to_ManView_EmpView_DetailsView)
        }

        holder.permBtn.setOnClickListener {
            if (employeeList[position].perms == "Manager") {
                employeeList[position].perms = "Employee"
                holder.permBtn.text = "Employee"
            } else {
                employeeList[position].perms = "Manager"
                holder.permBtn.text = "Manager"
            }
            db.changeEmployeePerms(employeeList[position].id, employeeList[position].perms)
            notifyDataSetChanged()
        }

        if ((keeper.getUserID() == employeeList[position].id) or (employeeList[position].id == 0)) {
            holder.deleteBtn.isClickable = false
            holder.deleteBtn.isEnabled = false

            holder.permBtn.isClickable = false
            holder.permBtn.isEnabled = false
        }

    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}