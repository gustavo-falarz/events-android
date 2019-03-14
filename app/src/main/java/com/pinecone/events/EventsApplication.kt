package com.pinecone.events

import android.app.Application
import com.pinecone.events.util.UserInfo

val userInfo: UserInfo by lazy {
    EventsApplication.userInfo
}

class EventsApplication : Application() {

    companion object {
        lateinit var userInfo: UserInfo
    }

    override fun onCreate() {
        super.onCreate()
        userInfo = UserInfo(applicationContext)
    }

}