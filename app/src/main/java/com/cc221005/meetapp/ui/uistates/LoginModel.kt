package com.cc221005.meetapp.ui.uistates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun updateEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _loginState.update { it.copy(email = email) }
            _loginState.update { it.copy(password = password) }
        }
    }

    fun updateFirebaseUser(user: FirebaseUser?) {
        viewModelScope.launch {
            _loginState.update { it.copy(currentUser = user) }
        }
    }

    fun updateUsernameAndName(username: String, name: String) {
        viewModelScope.launch {
            _loginState.update { it.copy(username = username) }
            _loginState.update { it.copy(name = name) }
        }
    }

    fun userIsLoggedIn(loggedIn: Boolean) {
        viewModelScope.launch {
            _loginState.update { it.copy(isLoggedIn = loggedIn) }
        }
    }

}