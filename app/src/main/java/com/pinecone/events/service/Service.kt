package com.pinecone.events.service


import com.google.gson.GsonBuilder
import com.pinecone.events.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Gustavo on 10/16/2017.
 */

open class Service {

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()!!

    private val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))


    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder
                .build()
        return retrofit.create(serviceClass)
    }

}
