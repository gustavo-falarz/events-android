package com.pinecone.events.service.endpoint

import com.pinecone.events.model.Attendee
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.PUT

interface UserEndpoint {

    @PUT("attendee")
    fun addAttendee(@Body attendee: Attendee): Completable
}