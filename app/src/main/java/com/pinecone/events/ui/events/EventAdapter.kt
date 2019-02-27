package com.pinecone.events.ui.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinecone.events.R
import com.pinecone.events.model.Event
import com.pinecone.events.util.DateUtil.Pattern.EVENT_DATE
import com.pinecone.events.util.DateUtil.Pattern.EVENT_TIME
import com.pinecone.events.util.DateUtil.formatToString
import kotlinx.android.synthetic.main.adapter_events.view.*

class EventAdapter(private val items: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_events,
                parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], listener)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(event: Event, listener: (Event) -> Unit) = with(itemView) {
            tvPlace.text = event.place.name
            tvDate.text = event.start.formatToString(EVENT_DATE)
            tvTime.text = event.start.formatToString(EVENT_TIME)
            setOnClickListener { listener(event) }
        }
    }
}