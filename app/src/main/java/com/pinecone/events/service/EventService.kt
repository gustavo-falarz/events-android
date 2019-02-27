package com.pinecone.events.service

import com.pinecone.events.service.endpoint.EventEndpoint
import java.util.*

object EventService : Service() {

    private val service: EventEndpoint
        get() = createService(EventEndpoint::class.java)

    fun addEvent(userId: String,
                 placeId: String,
                 dateTime: Date) = service.addEvent(userId, placeId, dateTime)

    fun getEvents() = service.getEvents()

    fun registerTotAttend(userId: String, eventId: String) = service.registerToAttend(userId, eventId)

    fun leaveEvent(userId: String, eventId: String) = service.leaveEvent(userId, eventId)

}