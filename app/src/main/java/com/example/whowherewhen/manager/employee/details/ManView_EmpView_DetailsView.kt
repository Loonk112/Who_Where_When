package com.example.whowherewhen.manager.employee.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.example.whowherewhen.Keeper
import com.example.whowherewhen.R
import com.example.whowherewhen.employee.EmpView_CredentialsUpdateView
import com.example.whowherewhen.employee.taskgroup.EmpView_TaskGroupView
import com.example.whowherewhen.manager.employee.details.taskgroup.ManView_EmpView_DetView_TaskGroupView
import com.google.android.material.bottomnavigation.BottomNavigationView


class ManView_EmpView_DetailsView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_man_view_emp_view_details_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<ImageButton>(R.id.ManView_EmpView_DetView_Return)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_ManView_EmpView_DetailsView_to_managerView)
        }

        val keeper = Keeper()

        val bNav = view.findViewById<BottomNavigationView>(R.id.ManView_EmpView_DetView_BottomNav)
        bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ManView_EmpView_DetView_Menu_Credentials -> {
                    keeper.setManEmp(true)
                    loadFragment(ManView_EmpView_DetView_CredentialsChange())
                }
                R.id.ManView_EmpView_DetView_Menu_TaskGroups -> {
                    keeper.setManEmp(false)
                    loadFragment(ManView_EmpView_DetView_TaskGroupView())
                }
            }
            true
        }

        if (keeper.getManEmp()) {
            loadFragment(ManView_EmpView_DetView_CredentialsChange())
        } else
        {
            loadFragment(ManView_EmpView_DetView_TaskGroupView())
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.ManView_EmpView_DetView_Container,fragment)
        transaction.commit()
    }
}