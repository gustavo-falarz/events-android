package com.pinecone.events.model

import java.io.Serializable

class Address(var street: String,
              var number: String,
              var complement: String,
              var neighborhood: String,
              var state: String,
              var city: String) : Serializable