package com.pinecone.events.model

class Attendee(id: String, name: String, email: String) : User(id, name, email) {

    var role: Role = Role.COMMON

    enum class Role {
        ORGANIZER,
        COMMON
    }
}