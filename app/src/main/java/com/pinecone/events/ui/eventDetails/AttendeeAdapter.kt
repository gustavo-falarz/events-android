package com.pinecone.events.ui.eventDetails

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinecone.events.R
import com.pinecone.events.model.Attendee
import kotlinx.android.synthetic.main.adapter_attendee.view.*

class AttendeeAdapter(private val attendees: List<Attendee>) : RecyclerView.Adapter<AttendeeAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(attendees[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_attendee,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = attendees.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(attendee: Attendee) = with(itemView) {
            tvAttendeeName.text = attendee.name
        }
    }
}