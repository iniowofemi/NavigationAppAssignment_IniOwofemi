package com.example.roomapp.fragments.list

import com.example.navigationappassignment.model.Event
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationappassignment.R
import kotlinx.android.synthetic.main.custom_row.view.*
import com.example.navigationappassignment.LongToString.dateToString
import com.example.navigationappassignment.LongToString.longToDate
import com.example.navigationappassignment.fragments.events.EventsFragmentDirections

class EventsAdapter: RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {

    private var eventsList : List<Event> = emptyList()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.eventName_txt.text = currentItem.eventName
        holder.itemView.eventCategory_txt.text = currentItem.eventCategory
        holder.itemView.eventDate_txt.text = dateToString(longToDate(currentItem.date))

        holder.itemView.rowLayout.setOnClickListener {
            val action = EventsFragmentDirections.actionEventsFragmentToDetailsFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(event: List<Event>){
        this.eventsList = event
        notifyDataSetChanged()
    }
}