package com.cc221005.meetapp.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.BottomNavigationBar
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.ui.views.widgets.OnboardingButtons
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


@Composable
fun getBottomBar(
    selectedScreen: Screen,
    navController: NavController,
    userModel: UserModel,
    auth: FirebaseAuth,
    context: Context,
    db: FirebaseFirestore
) {

    // While onboarding, show onboarding buttons
    if (selectedScreen == Screen.OnboardingFlow1 ||
        selectedScreen == Screen.OnboardingFlow2 ||
        selectedScreen == Screen.OnboardingFlow3 ||
        selectedScreen == Screen.OnboardingFlow3Login ||
        selectedScreen == Screen.OnboardingFlow4 ||
        selectedScreen == Screen.OnboardingFlow5 ||
        selectedScreen == Screen.OnboardingFlow6) {
        OnboardingButtons(
            showLeadingButton = selectedScreen != Screen.OnboardingFlow1 && selectedScreen != Screen.OnboardingFlow4,
            navController = navController,
            onTrailingButtonClicked = getTrailingButtonFunction(
                navController = navController,
                currentScreen = selectedScreen,
                userModel = userModel,
                auth = auth,
                context = context,
                db = db
            ),
            trailingButtonText = when (selectedScreen) {
                Screen.OnboardingFlow3 -> "Sign up"
                Screen.OnboardingFlow3Login -> "Login"
                Screen.OnboardingFlow6 -> "Finish"
                else -> "Next"
            }
        )
        // If user is on main screens, show the bottom nav bar
    } else if (selectedScreen == Screen.Home ||
                selectedScreen == Screen.Search ||
                selectedScreen == Screen.Create ||
                selectedScreen == Screen.Chat ||
                selectedScreen == Screen.Profile) {
        BottomNavigationBar(
            navController = navController,
            selectedScreen = selectedScreen
        )
    } else {
        // Else show nothing
    }
}