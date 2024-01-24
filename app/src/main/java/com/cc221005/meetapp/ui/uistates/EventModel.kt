package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class EventModel() : ViewModel() {
    private val _eventState = MutableStateFlow(EventState())
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    fun updateAddEvent(event: Event) {
        viewModelScope.launch {
            _eventState.update { currentState ->
                val updatedAddEvent = currentState.addEvent.copy(
                    title = event.title,
                    description = event.description,
                    cost = event.cost,
                    maxAttendees = event.maxAttendees,
                    hostedBy = event.hostedBy,
                    attendees = event.attendees,
                    visitedBy = event.visitedBy,
                    tags = event.tags
                )
                Log.e("updatedEvent", updatedAddEvent.toString())

                currentState.copy(addEvent = updatedAddEvent)
            }
        }
    }

    fun updateAddEventDate(timestamp: Timestamp) {
        viewModelScope.launch {
            _eventState.update { currentState ->
                val updatedAddEvent = currentState.addEvent.copy(
                    timestamp = timestamp
                )
                currentState.copy(addEvent = updatedAddEvent)
            }
        }
    }

}