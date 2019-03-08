package com.pinecone.events.service.endpoint

import com.pinecone.events.model.Event
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import java.util.*

interface EventEndpoint {

    @PUT("event")
    fun addEvent(@Query("userId") userId: String,
                 @Query("placeId") placeId: String,
                 @Query("dateTime") dateTime: Long): Completable

    @GET("event")
    fun getEvents(): Observable<List<Event>>

    @GET("event/register")
    fun registerToAttend(@Query("attendeeId") userId: String,
                         @Query("eventId") eventId: String): Observable<Event>

    @GET("event/leave-event")
    fun leaveEvent(@Query("attendeeId") userId: String,
                   @Query("eventId") eventId: String): Observable<Event>
}