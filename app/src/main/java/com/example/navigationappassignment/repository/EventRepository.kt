package com.example.navigationappassignment.repository

import androidx.lifecycle.LiveData
import com.example.navigationappassignment.eventdata.EventDao
import com.example.navigationappassignment.model.Event

class EventRepository(private val eventDao: EventDao) {

    val readAllData: LiveData<List<Event>> = eventDao.readAllData()

    fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    fun updateEvent(event: Event) {
        eventDao.updateEvent(event)
    }
}