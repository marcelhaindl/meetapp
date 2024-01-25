package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * # User Model
 * The User Model is used to interact with states relating to users.
 *
 * @param db (FirebaseFirestore) Firebase database variable to interact with the database
 */
class UserModel(private val db: FirebaseFirestore) : ViewModel() {
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    /**
     * # Update Email and Password
     * Updates the email and the password variable when the user is interacting with the sign up or login
     * on the onboarding screens.
     *
     * @param email (String) Email-Address
     * @param password (String) Password
     */
    fun updateEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _userState.update { it.copy(email = email) }
            _userState.update { it.copy(password = password) }
        }
    }

    /**
     * # Update Username and Name
     * Updates the username and the name when the user is interacting with the onboarding flow screens.
     *
     * @param username (String) Username
     * @param name (String) Full Name
     */
    fun updateUsernameAndName(username: String, name: String) {
        viewModelScope.launch {
            _userState.update { it.copy(onboardingUsername = username) }
            _userState.update { it.copy(onboardingName = name) }
        }
    }

    /**
     * # Add Interest Item
     * Adds an interest item whenever the user clicks on a not-selected chip at the onboarding flow
     *
     * @param interest (String) Interest string of the not-selected chip
     */
    fun addInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.onboardingInterestList.toMutableList().apply {
                add(interest.lowercase())
            }
            _userState.value = _userState.value.copy(onboardingInterestList = updatedInterests)
        }
    }

    /**
     * # Remove Interest Item
     * Removes an interest item whenever the user clicks on a selected chip at the onboarding flow
     *
     * @param interest (String) Interest String of the selected chip
     */
    fun removeInterestItem(interest: String) {
        viewModelScope.launch {
            val updatedInterests = _userState.value.onboardingInterestList.toMutableList().apply {
                remove(interest.lowercase())
            }
            Log.e("Interest", updatedInterests.toString())

            _userState.value = _userState.value.copy(onboardingInterestList = updatedInterests)
        }
    }

    /**
     * # Get User by ID
     * Gets the user with the [uid] from the database and uses the [callback] method to interact with the user.
     *
     * @param uid (String) UID of the user
     * @param callback ((User?) -> Unit) Callback method to interact with the user.
     */
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

    /**
     * # Get First 5 Users
     * Gets the first 5 users from the database. If the task is successful, the users are saved into the local variable
     * in order to interact with it on the screens. Also, the currently logged in user is removed from the array to avoid
     * that the user sees itself in the recommended list.
     */
    fun getFirst5Users() {
        db.collection("users")
            .limit(5)
            .get()
            .addOnSuccessListener { snapshot ->
                val users: MutableList<User> = snapshot.documents.map { document ->
                    document.toObject(User::class.java)!!.copy(uid = document.id)
                } as MutableList<User>

                users.removeIf { it.uid == _userState.value.localUser?.uid }

                viewModelScope.launch {
                    _userState.update { it.copy(first5Users = users) }
                }
            }
    }


    /**
     * # Update Specific User
     * Updates the specific user to [user].
     *
     * @param user (User) User to update to
     */
    fun updateSpecificUser(user: User) {
        viewModelScope.launch {
            _userState.update { it.copy(specificUser = user) }
        }
    }

    /**
     * # Set Local User
     * Sets the local user to [user]. Only the parameters that are set inside the [user] variable are actually saved to localUser.
     * If the parameters are null or empty, the previously saved value is remained.
     *
     * @param user (User) User to update the local user to
     */
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

    /**
     * # Get Recommended Events
     * Gets a number of recommended events depending on the interests of the user and the tags of the event.
     */
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
    }

    /**
     * # Get Top Events Next Week
     * Gets Events which date is within the range of one week.
     */
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
    }

    /**
     * # Get Events for each interest
     * For each interest a user has, this function searches for events that contain this interest as tags in order
     * to display very specific events like "technology events" or "cars events"
     */
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

    /**
     * # Get people with same interests
     * Gets users which have similar interests as the currently logged in user.
     */
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
    }

    /**
     * # Get Events from user id
     * Gets all the events where the user with the [uid] is named as a 1. host, 2. attendee or 3. visitor.
     *
     * @param uid (String) UID of the user
     */
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
    }

    /**
     * # Save current user
     * Gets the user with the [uid] from the database, and saves it as local user.
     *
     * @param uid (String) UID of the user
     */
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