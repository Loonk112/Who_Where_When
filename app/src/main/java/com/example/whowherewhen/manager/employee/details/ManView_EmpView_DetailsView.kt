package com.example.whowherewhen.manager.employee.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.example.whowherewhen.R


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

        //TODO
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.ManView_Container,fragment)
        transaction.commit()
    }
}