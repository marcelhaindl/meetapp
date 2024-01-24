package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser
import java.time.LocalDateTime

data class EventState(
    val addEvent: Event = Event()
)