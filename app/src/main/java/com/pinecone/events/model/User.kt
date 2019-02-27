package com.pinecone.events.model

import java.io.Serializable


open class User(var name: String, var email: String):Serializable {

    var id: String? = null

}