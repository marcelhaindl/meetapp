package com.cc221005.meetapp.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.BottomNavigationBar
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.ui.views.widgets.OnboardingButtons
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


/**
 * # Get Bottom Bar
 * The getBottomBar Composable is used to get the bottom bar depending on the current screen.
 *
 * @param selectedScreen (Screen) Selected Screen
 * @param navController (NavController) Navigation controller to navigate to other screens
 * @param userModel (UserModel) User Model to interact with user states
 * @param auth (FirebaseAuth) Firebase authentication
 * @param context (Context)
 * @param db (FirebaseFirestore) Firebase Firestore database to interact with the database
 */
@Composable
fun getBottomBar(
    selectedScreen: Screen,
    navController: NavController,
    userModel: UserModel,
    auth: FirebaseAuth,
    context: Context,
    db: FirebaseFirestore
) {
    when (selectedScreen) {
        Screen.OnboardingFlow1, Screen.OnboardingFlow2, Screen.OnboardingFlow3, Screen.OnboardingFlow3Login, Screen.OnboardingFlow4, Screen.OnboardingFlow5, Screen.OnboardingFlow6 -> {
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
        }
        Screen.Home, Screen.Search, Screen.Create, Screen.Chat, Screen.Profile -> {
            BottomNavigationBar(
                navController = navController,
                selectedScreen = selectedScreen
            )
        }
        else -> {
            // Else show nothing
        }
    }
}