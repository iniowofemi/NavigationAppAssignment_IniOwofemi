package com.example.navigationappassignment.eventdata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.navigationappassignment.model.Event

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Query("SELECT * FROM event_data ORDER BY date ASC")
    fun readAllData(): LiveData<List<Event>>
}