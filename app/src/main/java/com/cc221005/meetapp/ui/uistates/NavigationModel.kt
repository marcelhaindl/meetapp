package com.cc221005.meetapp.ui.uistates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.ui.views.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * # Navigation Model
 * The Navigation Model is used to save states corresponding to any kind of navigation like selected screens or dialog states.
 */
class NavigationModel : ViewModel() {
    private val _navigationState = MutableStateFlow(NavigationState())
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()

    /**
     * # Select Screen
     * Sets the screen to [screen] in order to make queries or else with the variable.
     *
     * @param screen (Screen) Screen to update to
     */
    fun selectScreen(screen: Screen) {
        _navigationState.update { it.copy(selectedScreen = screen) }
    }

    /**
     * # Show Back-Without-Saving Dialog
     * Shows the dialog depending on the variable [show]. Needs to be displayed (true) whenever the user
     * wants to return without saving (e.g. returning from edit event screen).
     *
     * @param show (Boolean) true -> to show the dialog; false -> to not show the dialog
     */
    fun showBackWithoutSavingDialog(show: Boolean) {
        viewModelScope.launch {
            _navigationState.update { it.copy(isBackWithoutSavingDialogOpen = show) }
        }
    }

    /**
     * # Show Delete-Event Dialog
     * Shows the dialog depending on the variable [show]. Needs to be displayed (true) whenever the user
     * wants to delete an event.
     *
     * @param show (Boolean) true -> to show the dialog; false -> to not show the dialog
     */
    fun showDeleteEventDialog(show: Boolean) {
        viewModelScope.launch {
            _navigationState.update { it.copy(isDeleteEventDialogOpen = show) }
        }
    }

    /**
     * # Update Theme
     * Updates the theme in order to enable users to individually set dark, light or system default themes.
     *
     * - 0 -> Dark Theme
     * - 1 -> Light Theme
     * - else -> System Default (Default)
     *
     * @param theme (Number)
     */
    fun updateTheme(theme: Number) {
        viewModelScope.launch {
            _navigationState.update { it.copy(theme = theme) }
        }
    }
}