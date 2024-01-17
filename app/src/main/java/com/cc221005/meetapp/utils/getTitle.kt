package com.cc221005.meetapp.utils

import com.cc221005.meetapp.ui.views.Screen

fun getTitle(screen: Screen): String {
    return when(screen) {
        Screen.Home -> "Home"
        Screen.Create -> "Create"
        Screen.Chat -> "Chat"
        Screen.Profile -> "Profile"
        Screen.Settings -> "Settings"
        else -> ""
    }
}