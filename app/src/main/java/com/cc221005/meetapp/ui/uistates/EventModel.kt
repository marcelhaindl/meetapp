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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * # EventModel
 * The Event Model is used to persist states of events inside the app, as well as to interact with the
 * Firebase database.
 *
 * @param db (FirebaseFirestore) - Database variable to interact with database
 */
class EventModel(private val db: FirebaseFirestore) : ViewModel() {
    private val _eventState = MutableStateFlow(EventState())
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    /**
     * # Get First 5 Events
     * Gets first 5 events of the "events" column of the firebase database and converts each document
     * to an event class.
     */
    fun getFirst5Events() {
        db.collection("events")
            .limit(5)
            .get()
            .addOnSuccessListener { snapshot ->
                val events: MutableList<Event> = snapshot.documents.map { document ->
                    document.toObject(Event::class.java)?.apply { id = document.id }
                } as MutableList<Event>

                viewModelScope.launch {
                    _eventState.update { it.copy(first5Events = events) }
                }
            }
            .addOnFailureListener {
                Log.e("Error", "Failed loading first 5 events + $it")
            }
    }

    /**
     * # Update addEvent
     * Updates the "addEvent" variable by only changing the values that are set inside the [event] variable.
     * All the other variables stay the same.
     *
     * @param event (Event) Event containing all the values to change
     */
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

    /**
     * # Update editEvent
     * Updates the "editEvent" variable by only changing the values that are set inside the [event] variable.
     * All the other variables stay the same.
     *
     * @param event (Event) Event containing all the values to change
     */
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
    }

    /**
     * # Save edited event
     * Saves the edited event to the firebase database and shows Toasts whether the task was successful or not.
     * - Successful
     *      - Set specific event to edited event to show the updated event on the specific event screen
     *      - Pop one screen to show the previous one
     *      - Show a toast to inform the user that the task was successful
     * - Failed
     *      - Show a toast to inform the user that the task failed
     *
     * @param navController (NavController) Used to pop one screen
     * @param context (Context) Used to show the Toasts
     */
    fun saveEditedEvent(navController: NavController, context: Context) {
        val editEvent: Event = _eventState.value.editEvent
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

    /**
     * # Set the delete-add-event-flag
     * Is set to true whenever the user wants to save the add event variable in order to reset the addEvent variable.
     * The [flag] is set to false when the addEvent variable is reset.
     *
     * @param flag (Boolean) Flag to set
     */
    fun setDeleteAddEventFlag(flag: Boolean) {
        viewModelScope.launch {
            _eventState.update { it.copy(deleteAddEventFlag = flag) }
        }
    }

    /**
     * # Set specificEvent
     * Set specific event to the parameter [event] in order to display the event on the specific event screen.
     *
     * @param event (Event) Event containing all the values to set
     */
    fun setSpecificEventTo(event: Event) {
        viewModelScope.launch {
            _eventState.update { it.copy(specificEvent = event) }
        }
    }

    /**
     * # Set editEvent
     * Set edit event to the parameter [event] in order to display the event on the edit event screen.
     *
     * @param event (Event) Event containing all the values to set
     */
    fun setEditEventTo(event: Event) {
        viewModelScope.launch {
            _eventState.update { it.copy(editEvent = event) }
        }
    }

    /**
     * # Set hostedEventUser
     * Set hosted event user to [user] with the uid of [uid] in order to display the name on the specific event screen.
     *
     * @param user (User) User to update the hostedEventUser to
     * @param uid (String) UID of the user to add to the user class
     */
    fun setHostedEventUser(user: User, uid: String) {
        viewModelScope.launch {
            _eventState.update { it.copy(hostedEventUser = user.copy(uid = uid)) }
        }
    }

    /**
     * # Update Add Event Date
     * Updates the timestamp of the addEvent variable in order to individually update the variable with the DatePicker.
     *
     * @param timestamp (Timestamp) Firebase timestamp of the date
     */
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

    /**
     * # Update Edit Event Date
     * Updates the timestamp of the editEvent variable in order to individually update the variable with the DatePicker.
     *
     * @param timestamp (Timestamp) Firebase timestamp of the date
     */
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
}