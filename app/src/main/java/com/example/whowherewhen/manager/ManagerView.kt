package com.example.whowherewhen.manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.manager.employee.ManView_EmployeesView
import com.example.whowherewhen.manager.taskgroup.ManView_TaskGroupsView
import com.example.whowherewhen.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class ManagerView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<ImageButton>(R.id.ManView_Return)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_managerView_to_loginView)
        }

        val switchButton = view.findViewById<ImageButton>(R.id.ManView_Switch)
        switchButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_managerView_to_employeeView)
        }
        val keeper = Keeper()

        val bNav = view.findViewById<BottomNavigationView>(R.id.ManView_BottomNav)
        bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ManView_Menu_Employees -> {
                    keeper.setManMain(true)
                    loadFragment(ManView_EmployeesView())
                }
                R.id.ManView_Menu_TaskGroups -> {
                    keeper.setManMain(false)
                    loadFragment(ManView_TaskGroupsView())
                }
            }
            true
        }

        if (keeper.getManMain()) {
            loadFragment(ManView_EmployeesView())
        } else
        {
            loadFragment(ManView_TaskGroupsView())
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.ManView_Container,fragment)
        transaction.commit()
    }

}