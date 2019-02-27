package com.pinecone.events.service

import com.pinecone.events.service.endpoint.PlacesEndpoint

object PlacesService : Service() {
    private val service: PlacesEndpoint
        get() = createService(PlacesEndpoint::class.java)

    fun getPlaces() = service.getPlaces()

}