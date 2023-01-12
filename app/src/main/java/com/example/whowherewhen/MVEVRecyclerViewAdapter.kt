package com.example.whowherewhen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class MVEVRecyclerViewAdapter (private val employeeList: ArrayList<EmployeeData>) : RecyclerView.Adapter<MVEVRecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.MRVETDeleteEmployee)!!
        val idView = view.findViewById<TextView>(R.id.MRVETEmployeeId)!!
        val nameView = view.findViewById<TextView>(R.id.MRVETEmployeeName)!!
        val surnameView = view.findViewById<TextView>(R.id.MRVETEmployeeSurname)!!
        val permBtn = view.findViewById<Button>(R.id.MRVETEmployeePerm)!!
        val detailsBtn = view.findViewById<ImageButton>(R.id.MRVETEmployeeDetails)!!
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
            Navigation.findNavController(holder.view).navigate(R.id.action_managerView_to_MVEVDetailsView)
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