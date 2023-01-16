package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
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

        loadFragment(ManView_EmployeesView())

        val bNav = view.findViewById<BottomNavigationView>(R.id.ManView_BottomNav)
        bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.MVMUsers -> {
                    loadFragment(ManView_EmployeesView())
                }
                R.id.MVMTaskGroups -> {
                    loadFragment(ManView_TaskGroupsView())
                }
            }
            true
        }



    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.ManView_Container,fragment)
        transaction.commit()
    }
}