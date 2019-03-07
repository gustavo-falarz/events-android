package com.pinecone.events.service.endpoint

import com.pinecone.events.model.Attendee
import io.reactivex.Completable
import retrofit2.http.PUT

interface UserEndpoint {

    @PUT("attendee")
    fun addAttendee(attendee: Attendee): Completable
}