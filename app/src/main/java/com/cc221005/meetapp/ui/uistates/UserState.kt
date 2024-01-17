package com.cc221005.meetapp.ui.uistates

import com.google.firebase.auth.FirebaseUser

data class UserState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val name: String = "",
    val localUser: FirebaseUser? = null,
    val interests: MutableList<String> = mutableListOf()
)