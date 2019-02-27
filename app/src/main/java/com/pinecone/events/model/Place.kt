package com.pinecone.events.model

import java.io.Serializable


class Place(var name: String,
            var address: Address,
            var point: Point): Serializable {
    var id: String? = null
}