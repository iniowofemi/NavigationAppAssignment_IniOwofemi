package com.example.navigationappassignment.fragments.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationappassignment.R
import com.example.navigationappassignment.viewmodel.EventViewModel
import com.example.roomapp.fragments.list.EventsAdapter
import kotlinx.android.synthetic.main.fragment_events.view.*

class EventsFragment : Fragment() {

    private lateinit var mEventsViewModel: EventViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        // Recyclerview
        val adapter = EventsAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //EventViewModel
        mEventsViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventsViewModel.readAllData.observe(viewLifecycleOwner, Observer{event ->
            adapter.setData(event)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_eventsFragment_to_entryFragment)
        }

        return view
    }
}