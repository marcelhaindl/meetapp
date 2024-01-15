package com.cc221005.meetapp.ui.uistates

import androidx.lifecycle.ViewModel
import com.cc221005.meetapp.ui.views.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationModel : ViewModel() {
    private val _navigationState = MutableStateFlow(NavigationState())
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()

    fun selectScreen(screen: Screen) {
        _navigationState.update { it.copy(selectedScreen = screen) }
    }

    fun setSearchBarActive(active: Boolean) {
        _navigationState.update { it.copy(isSearchBarActive = active) }
    }
}