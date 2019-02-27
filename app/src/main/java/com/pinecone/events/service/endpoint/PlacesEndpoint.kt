package com.pinecone.events.service.endpoint

import com.pinecone.events.model.Place
import io.reactivex.Observable
import retrofit2.http.GET

interface PlacesEndpoint {

    @GET("place")
    fun getPlaces(): Observable<List<Place>>

}