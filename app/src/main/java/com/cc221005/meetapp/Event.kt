package com.cc221005.meetapp

import com.google.firebase.firestore.Exclude
import java.sql.Timestamp
import java.time.LocalDateTime

data class Event (
    var title: String = "",
    var timestamp: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now(),
    var description: String? = "",
    var cost: Double? = 0.0,
    var maxAttendees: Int? = 0,
    var hostedBy: String? = "",
    var attendees: MutableList<String>? = mutableListOf(),
    var visitedBy: MutableList<String>? = mutableListOf(),
    var tags: MutableList<String>? = mutableListOf()
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "timestamp" to timestamp,
            "description" to description,
            "cost" to cost,
            "maxAttendees" to maxAttendees,
            "hostedBy" to hostedBy,
            "attendees" to attendees,
            "visitedBy" to visitedBy,
            "tags" to tags
            )
    }
    override fun toString(): String {
        return "Event(title=$title, timestamp=$timestamp, description=$description, cost=$cost, maxAttendees=$attendees, hostedBy=$hostedBy, attendees=$attendees, visitedBy=$visitedBy, tags?$tags}"
    }
}