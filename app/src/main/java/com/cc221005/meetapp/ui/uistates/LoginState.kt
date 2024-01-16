package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.ui.views.Screen

data class LoginState(
    val email: String = "test@gmail.com",
    val password: String = "1234",
    val isLoggedIn: Boolean = false,
    val username: String = "Test",
    val name: String = "Test"
)