package com.pinecone.events.ui.newEvent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinecone.events.R
import com.pinecone.events.model.Place
import kotlinx.android.synthetic.main.adapter_menu_places.view.*

class PlaceAdapter(private val items: List<Place>, private val listener: (Place) -> Unit) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_menu_places,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(place: Place, listener: (Place) -> Unit) = with(itemView) {
            tvPlaceName.text = place.name
            setOnClickListener { listener(place) }
        }
    }

}