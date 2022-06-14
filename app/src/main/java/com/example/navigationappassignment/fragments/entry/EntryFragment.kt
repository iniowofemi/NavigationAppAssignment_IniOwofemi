package com.example.navigationappassignment.fragments.entry

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigationappassignment.R
import com.example.navigationappassignment.model.Event
import com.example.navigationappassignment.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_entry.*
import kotlinx.android.synthetic.main.fragment_entry.view.*
import java.util.*

class EntryFragment : Fragment() {

    private lateinit var mEventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        var chosenDate = 0L
        view.editEventDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val c: Calendar = Calendar.getInstance()
            c.set(year, month, dayOfMonth)
            chosenDate = c.timeInMillis
        }

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        view.saveEntryButton.setOnClickListener{
            insertDataToDatabase(chosenDate)
        }

        return view
    }

    private fun insertDataToDatabase(date: Long) {
        val eventName = editEventName.text.toString()
        val eventCategory = editEventCategory.text.toString()
        val eventDate = date

        if(inputCheck(eventName, eventCategory)){
            // Create User Object
            val event = Event(0, eventName, eventCategory, eventDate)
            // Add Data to Database
            mEventViewModel.addEvent(event)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back to Events page
            findNavController().navigate(R.id.action_entryFragment_to_eventsFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(eventName: String, eventCategory: String): Boolean{
        return !(TextUtils.isEmpty(eventName) && TextUtils.isEmpty(eventCategory))
    }
}