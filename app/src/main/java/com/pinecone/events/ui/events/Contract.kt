package com.pinecone.events.ui.events

import com.pinecone.events.model.Event
import io.reactivex.Observable

interface Contract {

    interface View {

        fun getEvents()

        fun onEventsLoaded(observable: Observable<List<Event>>)

        fun onClickEvent(event: Event)

        fun showEvent(event: Event)

        fun createAdapter(events: List<Event>)
    }

    interface Presenter {
        fun getEvents()

        fun showEvent(event: Event)
    }
}