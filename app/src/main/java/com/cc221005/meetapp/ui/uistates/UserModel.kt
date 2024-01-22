package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserModel(private val db: FirebaseFirestore) : ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    fun updateEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _userState.update { it.copy(email = email) }
            _userState.update { it.copy(password = password) }
        }
    }

    fun setLocalUserTo(user: User?) {
        viewModelScope.launch {
            _userState.update { currentState ->
                user?.let {
                    currentState.copy(
                        localUser = currentState.localUser?.copy(
                            uid = it.uid,
                            email = it.email,
                            username = it.username,
                            name = it.name,
                            interests = it.interests?.toMutableList(),
                            followers = it.followers?.toMutableList(),
                            following = it.following?.toMutableList(),
                            biography = it.biography,
                        ) ?: it
                    )
                } ?: currentState.copy(localUser = null)
            }
        }
    }

    fun getEventsFromUserId(uid: String) {
        db.collection("events")
            .whereEqualTo("hostedBy", uid)
            .get()
            .addOnSuccessListener { hostedEventSnapshot ->
                val hostedEvents: MutableList<Event> = hostedEventSnapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!
                } as MutableList<Event>

                viewModelScope.launch {
                    _userState.update { it.copy(hostedEvents = hostedEvents) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading hosted events $exception")
            }

        db.collection("events")
            .whereArrayContains("attendees", uid)
            .get()
            .addOnSuccessListener { upcomingEventSnapshot ->
                val upcomingEvents = upcomingEventSnapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!
                } as MutableList<Event>

                viewModelScope.launch {
                    _userState.update { it.copy(upcomingEvents = upcomingEvents) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading upcoming events $exception")
            }

        db.collection("events")
            .whereArrayContains("visitedBy", uid)
            .get()
            .addOnSuccessListener { visitedEventSnapshot ->
                val visitedEvents = visitedEventSnapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!
                } as MutableList<Event>

                viewModelScope.launch {
                    _userState.update { it.copy(visitedEvents = visitedEvents) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading visited events $exception")
            }
    }

    fun updateUsernameAndName(username: String, name: String) {
        viewModelScope.launch {
            _userState.update { it.copy(username = username) }
            _userState.update { it.copy(name = name) }
        }
    }

    fun addInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.interests.toMutableList().apply {
                add(interest)
            }
            _userState.value = _userState.value.copy(interests = updatedInterests)
        }
    }

    fun removeInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.interests.toMutableList().apply {
                remove(interest)
            }
            _userState.value = _userState.value.copy(interests = updatedInterests)
        }
    }
}