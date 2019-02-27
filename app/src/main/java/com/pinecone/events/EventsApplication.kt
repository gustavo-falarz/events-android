package com.pinecone.events

import android.app.Application
import com.pinecone.events.util.UserInfo

val prefs: UserInfo by lazy {
    EventsApplication.prefs
}

class EventsApplication : Application() {

    companion object {
        lateinit var prefs: UserInfo
    }

}