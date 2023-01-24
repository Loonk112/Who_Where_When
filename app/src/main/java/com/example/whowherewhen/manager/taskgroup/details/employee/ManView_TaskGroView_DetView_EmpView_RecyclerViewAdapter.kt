package com.example.whowherewhen.manager.taskgroup.details.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.data.ExtendedGroupEmployeeData

class ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter (private val employeeList: ArrayList<ExtendedGroupEmployeeData>) : RecyclerView.Adapter<ManView_TaskGroView_DetView_EmpView_RecyclerViewAdapter.ViewHolder>()  {
    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val deleteBtn = view.findViewById<ImageButton>(R.id.ManRecVie_EmpTemp2_DeleteEmployee)!!
        val idView = view.findViewById<TextView>(R.id.ManRecVie_EmpTemp2_EmployeeId)!!
        val nameView = view.findViewById<TextView>(R.id.ManRecVie_EmpTemp2_EmployeeName)!!
        val surnameView = view.findViewById<TextView>(R.id.ManRecVie_EmpTemp2_EmployeeSurname)!!
        val timeBtn = view.findViewById<ImageButton>(R.id.ManRecVie_EmpTemp2_EmployeeTime)!!
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.manager_recycler_view_employee_template_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idView.text = employeeList[position].emp_id.toString()
        holder.nameView.text = employeeList[position].name
        holder.surnameView.text = employeeList[position].surname

        holder.deleteBtn.setOnClickListener {
            val db = DBHelper(holder.view.context, null)
            db.deleteGroupWorker(employeeList[position].conn_id)
            employeeList.removeAt(position)
            notifyDataSetChanged()
        }

        holder.timeBtn.setOnClickListener {
            val keeper = Keeper()
            keeper.setEmployeeId(employeeList[position].emp_id)
            Navigation.findNavController(holder.view).navigate(R.id.action_ManView_TaskGroView_DetailsView_to_ManView_TaskGroView_DetView_EmpView_TimeView)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
}