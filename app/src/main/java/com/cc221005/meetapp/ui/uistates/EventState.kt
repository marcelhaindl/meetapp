package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser
import java.time.LocalDateTime

data class EventState(
    val title: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val description: String = "",
    val cost: Number = 0,
    val attendees: Number = 0

)