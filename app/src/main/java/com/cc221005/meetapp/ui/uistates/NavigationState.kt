package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.ui.views.Screen

data class NavigationState(
    val selectedScreen: Screen = Screen.Home,
    val theme: Number = 3, // 0 -> Dark; 1 -> Light; else -> System Default
    val isBackWithoutSavingDialogOpen: Boolean = false,
    val isDeleteEventDialogOpen: Boolean = false
)