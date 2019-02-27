package com.pinecone.events.ui.eventDetails

import com.pinecone.events.model.Attendee
import com.pinecone.events.model.Event
import com.pinecone.events.model.User
import io.reactivex.Observable

interface Contract {

    interface View {
        fun onLoadEvent(event: Event)

        fun onLoadAttendees(attendees: List<Attendee>)

        fun onRegisterUser(observable: Observable<Event>)

        fun onRemoveUser(observable: Observable<Event>)

        fun onUserAttending()

        fun onUserNotAttending()

        fun registerToAttend()

        fun leaveEvent()

        fun onUserRegistered(event: Event)

        fun onUserLeft(event: Event)
    }

    interface Presenter {
        fun loadEvent(event: Event)

        fun registerToAttend(eventId: String)

        fun leaveEvent(eventId: String)
    }

}