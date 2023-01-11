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

class MVTGVDVTasksView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_m_v_t_g_v_d_v_tasks_view, container, false)

        val keeper = Keeper()

        val db = DBHelper(requireContext(), null)
        val data = db.getTaskGroupTasks(keeper.getTaskGroupId())

        val recycler = view.findViewById<RecyclerView>(R.id.MVTGVDVTVRecyclerView)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = MVTGVDVTVRecyclerViewAdapter(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newTask = view.findViewById<FloatingActionButton>(R.id.MVTGVDVTVFloatingActionButton)
        newTask.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_MVTGVDetailsView_to_MVTGVDVTVAddTaskView)
        }
    }
}