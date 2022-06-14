package com.example.navigationappassignment

import java.text.SimpleDateFormat
import java.util.*
object LongToString {

    private val sdf = SimpleDateFormat("dd/MM/yyyy")

    fun longToDate(long: Long): Date{
            return Date(long)
        }

    fun dateToString(date: Date): String{
        return sdf.format(date)
    }
}