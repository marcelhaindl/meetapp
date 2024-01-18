package com.cc221005.meetapp.ui.views

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Search: Screen("search")
    object Create: Screen("create")
    object Chat: Screen("chat")
    object Profile: Screen("profile")
    object Settings: Screen("settings")
    object Theme: Screen("theme")
    object SpecificUser: Screen("specificuser")
    object OnboardingFlow1: Screen("onboardingflow1")
    object OnboardingFlow2: Screen("onboardingflow2")
    object OnboardingFlow3: Screen("onboardingflow3")
    object OnboardingFlow3Login: Screen("onboardingflow3login")
    object OnboardingFlow4: Screen("onboardingflow4")
    object OnboardingFlow5: Screen("onboardingflow5")
    object OnboardingFlow6: Screen("onboardingflow6")
}