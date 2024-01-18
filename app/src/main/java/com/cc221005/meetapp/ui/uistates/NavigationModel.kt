package com.cc221005.meetapp.ui.uistates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.ui.views.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigationModel : ViewModel() {
    private val _navigationState = MutableStateFlow(NavigationState())
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()

    fun selectScreen(screen: Screen) {
        _navigationState.update { it.copy(selectedScreen = screen) }
    }

    fun updateTheme(theme: Number) {
        /**
         * # updateTheme
         * 0 -> Dark Theme
         * 1 -> Light Theme
         * else -> System Default
         */
        viewModelScope.launch {
            _navigationState.update { it.copy(theme = theme) }
        }
    }
}