package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User

data class EventState(
    val addEvent: Event = Event(),
    val deleteAddEventFlag: Boolean = false,
    val specificEvent: Event = Event(),
    val hostedEventUser: User = User(),
    val editEvent: Event = Event(),
    val first5Events: MutableList<Event> = mutableListOf()
)