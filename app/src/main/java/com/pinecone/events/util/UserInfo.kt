package com.pinecone.events.util

import android.content.Context
import android.content.SharedPreferences

const val PREF_KEY = "com.gfb.watchlist"
const val PREF_USER_ID = "ID"
class UserInfo(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_KEY, 0)


    var userId: String
        get() = prefs.getString(PREF_USER_ID, "5c6c570b8d94982b50e319d6")
        set(value) = prefs.edit().putString(PREF_USER_ID, value).apply()

}