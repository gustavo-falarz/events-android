package com.pinecone.events.model

import java.io.Serializable

data class Setup(var attendees: MutableList<Attendee>) : Serializable