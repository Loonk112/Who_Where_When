package com.example.whowherewhen.manager.taskgroup.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.example.whowherewhen.manager.taskgroup.details.employee.ManView_TaskGroView_DetView_EmployeesView
import com.example.whowherewhen.R
import com.example.whowherewhen.manager.taskgroup.details.task.ManView_TaskGroView_DetView_TasksView
import com.google.android.material.bottomnavigation.BottomNavigationView


class ManView_TaskGroView_DetailsView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view_task_gro_view_details_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(ManView_TaskGroView_DetView_EmployeesView())

        val returnButton = view.findViewById<ImageButton>(R.id.ManView_TaskGroView_DetView_Return)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_TaskGroView_DetailsView_to_managerView)
        }

        val bottomNav = view.findViewById<BottomNavigationView>(R.id.ManView_TaskGroView_DetView_BottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ManView_TaskGroView_DetView_Menu_Employees -> {
                    loadFragment(ManView_TaskGroView_DetView_EmployeesView())
                }
                R.id.ManView_TaskGroView_DetView_Menu_Tasks -> {
                    loadFragment(ManView_TaskGroView_DetView_TasksView())
                }
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.ManView_TaskGroView_DetView_Container,fragment)
        transaction.commit()
    }
}