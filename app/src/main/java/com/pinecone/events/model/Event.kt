package com.pinecone.events.model

import java.io.Serializable
import java.util.*


class Event(
        var organizer: User,
        var title: String,
        var start: Date,
        var place: Place,
        var attendees: MutableList<Attendee>) : Serializable {

    var id: String? = null
    var setup: Setup? = null
}