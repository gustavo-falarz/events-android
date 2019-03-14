package com.pinecone.events.ui.newEvent

import com.pinecone.events.model.Place
import com.pinecone.events.model.Setup
import io.reactivex.Completable
import io.reactivex.Observable

interface Contract {

    interface View {

        fun onPlacesLoaded(observable: Observable<List<Place>>)

        fun getPlaces()

        fun addEvent()

        fun onAddEvent(completable: Completable)

        fun onEventAdded()

        fun onMissingInfo(message: String)

        fun showPlaces(places: List<Place>)
    }

    interface Presenter {

        var date: String?
        var time: String?
        var placeId: String?

        fun getPlaces()

        fun checkErrors(placeId: String?, date: String?, time: String?): Boolean

        fun addEvent()

        fun onEventAdded()

    }
}