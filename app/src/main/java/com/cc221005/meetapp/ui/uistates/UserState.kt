package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser

data class UserState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val name: String = "",
    val interests: MutableList<String> = mutableListOf(),
    val localUser: User? = null,
    val isDataInDatabase: Boolean = false,
)