package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser

data class SearchState(
    val searchString: String = "",
    val loadedUsers: List<User> = listOf(),
    val isLoading: Boolean = false,
    val specificUser: User = User()
)