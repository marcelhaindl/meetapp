package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserModel : ViewModel() {
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
                            interests = it.interests?.toMutableList()
                        ) ?: it
                    )
                } ?: currentState.copy(localUser = null)
            }
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

    fun dataIsInDatabase(isDataInDatabase: Boolean) {
        viewModelScope.launch {
            _userState.update { it.copy(isDataInDatabase = isDataInDatabase) }
        }
    }
}