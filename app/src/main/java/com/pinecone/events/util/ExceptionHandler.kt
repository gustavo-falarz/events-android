package com.pinecone.events.util

import com.google.gson.Gson
import retrofit2.HttpException

class ExceptionHandler {

    companion object {

        fun HttpException.parse(): HttpResponse {
            val json = this.response().errorBody()?.string()
            return Gson().fromJson(json, HttpResponse::class.java)
        }

        class HttpResponse(val timestamp: String, val status: Int, val error: String,
                           val message: String, val path: String)
    }
}