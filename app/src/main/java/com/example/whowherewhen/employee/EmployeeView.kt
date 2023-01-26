package com.example.whowherewhen.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.whowherewhen.DBHelper
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.employee.taskgroup.EmpView_TaskGroupView
import com.example.whowherewhen.manager.employee.ManView_EmployeesView
import com.example.whowherewhen.manager.taskgroup.ManView_TaskGroupsView
import com.google.android.material.bottomnavigation.BottomNavigationView


class EmployeeView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<ImageButton>(R.id.EmpView_LogoutBtn)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_employeeView_to_loginView)
        }

        val keeper = Keeper()
        val db = DBHelper(requireContext(), null)

        val switchButton = view.findViewById<ImageButton>(R.id.EmpView_SwitchBtn)
        if (db.getEmployee(keeper.getUserID()).perms == "Manager") {
            switchButton.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_employeeView_to_managerView)
            }
        } else {
            switchButton.isVisible = false
        }

        view.findViewById<TextView>(R.id.EmpView_Title).text = db.getLogin(keeper.getUserID())

        val bNav = view.findViewById<BottomNavigationView>(R.id.EmpView_BNav)
        bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ManView_EmpView_DetView_Menu_Credentials -> {
                    loadFragment(EmpView_CredentialsUpdateView())
                    keeper.setEmpMain(true)
                }
                R.id.ManView_EmpView_DetView_Menu_TaskGroups -> {
                    loadFragment(EmpView_TaskGroupView())
                    keeper.setEmpMain(false)
                }
            }
            true
        }

        if (keeper.getEmpMain()) {
            loadFragment(EmpView_CredentialsUpdateView())
        } else
        {
            loadFragment(EmpView_TaskGroupView())
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.EmpView_Container,fragment)
        transaction.commit()
    }
}