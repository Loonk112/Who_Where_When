package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView


class MVTGVDetailsView : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_v_t_g_v_details_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(MVTGVDVEmployeesView())

        val returnButton = view.findViewById<ImageButton>(R.id.MVTGVDVReturn)
        returnButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDetailsView_to_managerView)
        }

        val bottomNav = view.findViewById<BottomNavigationView>(R.id.MVTGVDVBottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.MVTGVDVMEmployees -> {
                    loadFragment(MVTGVDVEmployeesView())
                }
                R.id.MVTGVDVMTasks -> {
                    loadFragment(MVTGVDVTasksView())
                }
            }
            true
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.MVTGVDVContainer,fragment)
        transaction.commit()
    }
}