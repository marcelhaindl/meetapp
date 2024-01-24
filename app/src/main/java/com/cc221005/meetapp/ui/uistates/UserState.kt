package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User

data class UserState(
    val email: String = "",
    val password: String = "",
    val onboardingUsername: String = "",
    val onboardingName: String = "",
    val onboardingInterestList: MutableList<String> = mutableListOf(),
    val localUser: User? = null,
    val hostedEvents: MutableList<Event>? = mutableListOf(),
    val upcomingEvents: MutableList<Event>? = mutableListOf(),
    val visitedEvents: MutableList<Event>? = mutableListOf(),
    val recommendedEvents: MutableList<Event>? = mutableListOf(),
    val peopleWithSameInterests: MutableList<User>? = mutableListOf(),
    val topEventsNextWeek: MutableList<Event>? = mutableListOf(),
    val eventsForEachInterest: MutableMap<String, MutableList<Event>?> = mutableMapOf(),
    val specificUser: User = User(),
    val first5Users: MutableList<User> = mutableListOf()
)