package com.example.whowherewhen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ManView_TaskGroupsView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_man_view_task_groups_view, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.MVTGVRecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)

        val db = DBHelper(requireContext(), null)

        val data = db.getAllTaskGroups()

        recycler.adapter = ManView_TaskGroView_RecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newUser = view.findViewById<FloatingActionButton>(R.id.MVTGVFloatingActionButton)
        newUser.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_managerView_to_ManView_TaskGroView_AddTaskGroupView)
        }

        //TODO

    }
}