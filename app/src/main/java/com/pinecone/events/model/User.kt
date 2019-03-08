package com.pinecone.events.model

import java.io.Serializable


open class User(var id: String, var name: String, var email: String) : Serializable