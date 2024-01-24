package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventModel() : ViewModel() {
    private val _eventState = MutableStateFlow(EventState())
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    fun updateAddEvent(event: Event) {
        Log.e("event", event.toString())
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
                currentState.copy(addEvent = updatedAddEvent)
            }
        }
    }

    fun setDeleteAddEventFlag(flag: Boolean) {
        viewModelScope.launch {
            _eventState.update { it.copy(deleteAddEventFlag = flag) }
        }
    }

    fun setSpecificEventTo(event: Event) {
        viewModelScope.launch {
            _eventState.update { it.copy(specificEvent = event) }
        }
    }

    fun setHostedEventUser(user: User, uid: String) {
        viewModelScope.launch {
            _eventState.update { it.copy(hostedEventUser = user.copy(uid = uid)) }
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