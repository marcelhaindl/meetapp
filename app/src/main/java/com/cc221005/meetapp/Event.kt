package com.cc221005.meetapp

import com.google.firebase.firestore.Exclude

/**
 * # Event Class
 * Event is a data class (blueprint) containing all variables and functions needed to interact with an event.
 *
 * @param id (String) The unique identifier for the event.
 * @param title (String) The title or name of the event.
 * @param timestamp (Timestamp) The Firebase timestamp. Default is the current timestamp.
 * @param description (String) A brief description or details about the event. Nullable.
 * @param cost (Double) The cost associated with attending the event. Nullable, default is 0.0.
 * @param maxAttendees (Int) The maximum number of attendees allowed for the event. Nullable, default is 0.
 * @param hostedBy (String) The organizer or entity hosting the event. Nullable.
 * @param attendees (MutableList<String>) List of user IDs who are attending the event. Initialized as an empty mutable list
 * @param visitedBy (MutableList<String>) List of user IDs who have visited or interacted with the event. Initialized as an empty mutable list
 * @param tags (MutableList<String>) List of tags associated with the event. Initialized as an empty mutable list
 */
data class Event (
    var id: String = "",
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
        return "Event(id=$id, title=$title, timestamp=$timestamp, description=$description, cost=$cost, maxAttendees=$attendees, hostedBy=$hostedBy, attendees=$attendees, visitedBy=$visitedBy, tags=$tags}"
    }
}