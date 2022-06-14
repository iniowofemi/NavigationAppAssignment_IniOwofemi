package com.example.navigationappassignment.fragments.details

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.navigationappassignment.LongToString
import com.example.navigationappassignment.R
import com.example.navigationappassignment.model.Event
import com.example.navigationappassignment.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.fragment_entry.view.*
import java.util.*

class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var mEventsViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        var updatedDate = 0L
        view.updateEventDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val c: Calendar = Calendar.getInstance()
            c.set(year, month, dayOfMonth)
            updatedDate = c.timeInMillis
        }

        mEventsViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        view.updateEventName.setText(args.currentEventName.eventName)
        view.updateEventCategory.setText(args.currentEventName.eventCategory)
        view.updateEventDate.setDate(args.currentEventName.date)

        view.updateEntryButton.setOnClickListener{
            updateItem(updatedDate)
        }

        return view
    }

    private fun updateItem(date: Long) {
        val eventName = updateEventName.text.toString()
        val eventCategory = updateEventCategory.text.toString()
        val date = date

        if(inputCheck(eventName,eventCategory)){
            //Create User Object
            val updatedEventName = Event(args.currentEventName.id, eventName, eventCategory, date)
            //Update Event
            mEventsViewModel.updateEventName(updatedEventName)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_detailsFragment_to_eventsFragment)
        } else {
            Toast.makeText(requireContext(),"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(eventName: String, eventCategory: String): Boolean{
        return !(TextUtils.isEmpty(eventName) && TextUtils.isEmpty(eventCategory))
    }

}