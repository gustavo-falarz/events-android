package com.pinecone.events.ui.eventDetails

import com.google.firebase.auth.FirebaseAuth
import com.pinecone.events.model.Event
import com.pinecone.events.service.EventService
import com.pinecone.events.ui.eventDetails.Contract.Presenter

class EventDetailsPresenter(private val view: EventDetailsView) : Presenter {

    override fun registerToAttend(eventId: String) {
        view.onRegisterUser(EventService.registerTotAttend(FirebaseAuth.getInstance().uid!!, eventId))
    }

    override fun leaveEvent(eventId: String) {
        view.onRegisterUser(EventService.leaveEvent(FirebaseAuth.getInstance().uid!!, eventId))
    }

    override fun loadEvent(event: Event) {
        view.onLoadEvent(event)
        view.onLoadAttendees(event.attendees)

        val attendee = event.attendees.find { it.id == FirebaseAuth.getInstance().uid!! }
        when (attendee) {
            null -> view.onUserNotAttending()
            else -> view.onUserAttending()
        }
    }

}