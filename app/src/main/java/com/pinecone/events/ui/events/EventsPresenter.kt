package com.pinecone.events.ui.events

import com.pinecone.events.ui.events.EventsView
import com.pinecone.events.model.Event
import com.pinecone.events.service.EventService

class EventsPresenter(val view: EventsView): Contract.Presenter{
    override fun showEvent(event: Event) {
        view.showEvent(event)
    }

    override fun getEvents() {
        view.onEventsLoaded(EventService.getEvents())
    }
}