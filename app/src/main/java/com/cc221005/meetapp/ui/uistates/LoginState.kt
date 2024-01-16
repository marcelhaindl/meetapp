package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoggedIn: Boolean = false,
    val username: String = "",
    val name: String = "",
    val currentUser: FirebaseUser? = null,
)