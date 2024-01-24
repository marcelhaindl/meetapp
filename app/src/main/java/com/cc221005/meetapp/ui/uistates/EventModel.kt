package com.cc221005.meetapp.ui.uistates

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventModel(private val db: FirebaseFirestore) : ViewModel() {
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
                currentState.copy(addEvent = updatedAddEvent)
            }
        }
    }

    fun updateEditEvent(event: Event) {
        viewModelScope.launch {
            _eventState.update { currentState ->
                val updatedEditEvent = currentState.editEvent.copy(
                    title = event.title,
                    description = event.description,
                    cost = event.cost,
                    maxAttendees = event.maxAttendees,
                    attendees = event.attendees,
                    visitedBy = event.visitedBy,
                    tags = event.tags
                )
                currentState.copy(editEvent = updatedEditEvent)
            }
        }
        Log.e("testOutput123", _eventState.value.editEvent.toString())
    }

    fun saveEditedEvent(navController: NavController, context: Context) {
        val editEvent: Event = _eventState.value.editEvent
        Log.e("testOutput123", editEvent.toString())
        db.collection("events").document(editEvent.id)
            .set(editEvent.toMap())
            .addOnSuccessListener {
                setSpecificEventTo(_eventState.value.editEvent)
                navController.popBackStack()
                Toast.makeText(
                    context,
                    context.getString(R.string.successfully_updated_event),
                    Toast.LENGTH_SHORT,
                ).show()
            }
            .addOnFailureListener{
                Toast.makeText(
                    context,
                    context.getString(R.string.failed_updating_event),
                    Toast.LENGTH_SHORT,
                ).show()
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

    fun setEditEventTo(event: Event) {
        viewModelScope.launch {
            _eventState.update { it.copy(editEvent = event) }
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

    fun updateEditEventDate(timestamp: Timestamp) {
        viewModelScope.launch {
            _eventState.update { currentState ->
                val updatedEditEvent = currentState.editEvent.copy(
                    timestamp = timestamp
                )
                currentState.copy(editEvent = updatedEditEvent)
            }
        }
    }

    fun updateTagsOfAddEvent(tags: MutableList<String>) {
        viewModelScope.launch {
            _eventState.update { currentState ->
                val updatedEvent = currentState.addEvent.copy(tags = tags)
                currentState.copy(addEvent = updatedEvent)
            }
        }
    }
}