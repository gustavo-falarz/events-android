package com.pinecone.events.service

import com.pinecone.events.model.Attendee
import com.pinecone.events.service.endpoint.UserEndpoint

object UserService : Service() {
    val service: UserEndpoint
        get() = createService(UserEndpoint::class.java)

    fun addAttendee(attendee: Attendee) = service.addAttendee(attendee)
}