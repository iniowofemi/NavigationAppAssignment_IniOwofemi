package com.example.navigationappassignment.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize

@Entity(tableName = "event_data")
data class Event (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val eventName: String,
    val eventCategory: String,
    val date: Long,
): Parcelable