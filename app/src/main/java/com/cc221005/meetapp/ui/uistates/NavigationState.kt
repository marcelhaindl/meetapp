package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.ui.views.Screen

data class NavigationState(
    val selectedScreen: Screen = Screen.Home,
    val isSearchBarActive: Boolean = false,
)