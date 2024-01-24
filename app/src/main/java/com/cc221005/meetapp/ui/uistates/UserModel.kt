package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture

class UserModel(private val db: FirebaseFirestore) : ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    fun updateEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _userState.update { it.copy(email = email) }
            _userState.update { it.copy(password = password) }
        }
    }

    fun getUserById(uid: String, callback: (User?) -> Unit) {
        db.collection("users").document(uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val snapshot = task.result
                    val user = snapshot?.toObject(User::class.java)
                    callback(user)
                } else {
                    callback(null)
                }
            }
    }


    fun updateSpecificUser(user: User) {
        viewModelScope.launch {
            _userState.update { it.copy(specificUser = user) }
        }
    }

    fun setLocalUserTo(user: User?) {
        viewModelScope.launch {
            _userState.update { currentState ->
                user?.let {
                    currentState.copy(
                        localUser = currentState.localUser?.copy(
                            // Conditionally update properties based on the input user
                            uid = it.uid.takeUnless { it.isNullOrEmpty() } ?: currentState.localUser.uid,
                            email = it.email.takeUnless { it.isNullOrEmpty() } ?: currentState.localUser.email,
                            username = it.username.takeUnless { it.isNullOrEmpty() } ?: currentState.localUser.username,
                            name = it.name.takeUnless { it.isNullOrEmpty() } ?: currentState.localUser.name,
                            interests = it.interests?.takeUnless { it.isNullOrEmpty() }?.toMutableList()
                                ?: currentState.localUser.interests,
                            followers = it.followers?.takeUnless { it.isNullOrEmpty() }?.toMutableList()
                                ?: currentState.localUser.followers,
                            following = it.following?.takeUnless { it.isNullOrEmpty() }?.toMutableList()
                                ?: currentState.localUser.following,
                            biography = it.biography.takeUnless { it.isNullOrEmpty() } ?: currentState.localUser.biography,
                        ) ?: it
                    )
                } ?: currentState.copy(localUser = null)
            }
        }
    }



    fun getRecommendedEvents() {
        val userInterests = _userState.value.localUser?.interests ?: mutableListOf()

        val lowercaseUserInterests = userInterests.map { it.lowercase() }

        db.collection("events")
            .whereArrayContainsAny("tags", lowercaseUserInterests)
            .get()
            .addOnSuccessListener { snapshot ->
                val recommendedEvents: MutableList<Event> = snapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!.copy(id = document.id)
                } as MutableList<Event>

                viewModelScope.launch {
                    _userState.update { it.copy(recommendedEvents = recommendedEvents) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading recommended events $exception")
            }
    }

    fun getTopEventsNextWeek() {
        val currentTime = Timestamp.now()
        val oneWeekInMillis = 7 * 24 * 60 * 60 * 1000 // 1 week in milliseconds

        val startDate = currentTime
        val endDate = Timestamp(currentTime.seconds + (oneWeekInMillis / 1000), currentTime.nanoseconds)

        db.collection("events")
            .whereGreaterThanOrEqualTo("timestamp", startDate)
            .whereLessThanOrEqualTo("timestamp", endDate)
            .get()
            .addOnSuccessListener { snapshot ->
                val topEventsNextWeek: MutableList<Event> = snapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!.copy(id = document.id)
                } as MutableList<Event>

                viewModelScope.launch {
                    _userState.update { it.copy(topEventsNextWeek = topEventsNextWeek) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading top events next week $exception")
            }
    }

    fun getEventsForEachInterest() {
        _userState.value.localUser?.interests?.forEach { interest ->
            db.collection("events")
                .whereArrayContains("tags", interest.lowercase())
                .get()
                .addOnSuccessListener { snapshot ->
                    val interestEvents: MutableList<Event> = snapshot.documents.map { document ->
                        document.toObject(Event::class.java)!!.copy(id = document.id)
                    } as MutableList<Event>

                    viewModelScope.launch {
                        _userState.update {
                            it.copy(
                                eventsForEachInterest = it.eventsForEachInterest.toMutableMap().apply {
                                    this[interest] = interestEvents
                                }
                            )
                        }
                    }

                }
        }
    }

    fun getPeopleWithSameInterests() {
        val userInterests = _userState.value.localUser?.interests ?: mutableListOf()

        val lowercaseUserInterests = userInterests.map { it.lowercase() }

        db.collection("users")
            .whereArrayContainsAny("interests", lowercaseUserInterests)
            .get()
            .addOnSuccessListener { snapshot ->
                val peopleWithSameInterests: MutableList<User> = snapshot.documents.map { document ->
                    document.toObject(User::class.java)?.copy ( uid = document.id )
                } as MutableList<User>

                peopleWithSameInterests.removeIf { it.uid == _userState.value.localUser?.uid }

                viewModelScope.launch {
                    _userState.update { it.copy(peopleWithSameInterests = peopleWithSameInterests) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Failed loading people with same interests $exception")
            }
    }

    fun getEventsFromUserId(uid: String) {
        db.collection("events")
            .whereEqualTo("hostedBy", uid)
            .get()
            .addOnSuccessListener { hostedEventSnapshot ->
                val hostedEvents: MutableList<Event> = hostedEventSnapshot.documents.map { document ->
                    document.toObject(Event::class.java)!!.copy(id = document.id)
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
                    document.toObject(Event::class.java)!!.copy(id = document.id)
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
                    document.toObject(Event::class.java)!!.copy(id = document.id)
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
            _userState.update { it.copy(onboardingUsername = username) }
            _userState.update { it.copy(onboardingName = name) }
        }
    }

    fun addInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.onboardingInterestList.toMutableList().apply {
                add(interest.lowercase())
            }
            Log.e("Interest", updatedInterests.toString())
            _userState.value = _userState.value.copy(onboardingInterestList = updatedInterests)
        }
    }

    fun removeInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.onboardingInterestList.toMutableList().apply {
                remove(interest.lowercase())
            }
            Log.e("Interest", updatedInterests.toString())

            _userState.value = _userState.value.copy(onboardingInterestList = updatedInterests)
        }
    }

    fun saveCurrentUser(uid: String) {
        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.toObject(User::class.java)?.copy(uid = uid)
                setLocalUserTo(user)
            }
    }
}