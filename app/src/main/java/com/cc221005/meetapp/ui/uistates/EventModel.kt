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
import java.time.LocalDateTime

class EventModel : ViewModel() {
    private val _eventState = MutableStateFlow(EventState())
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()


    fun updateDate(date: LocalDateTime) {
        viewModelScope.launch {
            _eventState.update { it.copy(date = date) }
        }
    }
}