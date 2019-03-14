package com.pinecone.events.ui.events

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.model.Event
import com.pinecone.events.service.EventService

class EventsPresenter(val view: EventsView) : Contract.Presenter {
    override fun showEvent(event: Event) {
        view.showEvent(event)
    }

    override fun getEvents() {
        view.onEventsLoaded(EventService.getEvents())
    }

    override fun logOut() {
        FirebaseAuth.getInstance().signOut()
        view.onUserLoggedOut()
    }
}