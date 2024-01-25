package com.cc221005.meetapp.ui.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.views.screens.Chat
import com.cc221005.meetapp.ui.views.screens.Create
import com.cc221005.meetapp.ui.views.screens.EditEvent
import com.cc221005.meetapp.ui.views.screens.Home
import com.cc221005.meetapp.ui.views.screens.Profile
import com.cc221005.meetapp.ui.views.screens.Search
import com.cc221005.meetapp.ui.views.screens.SelectTheme
import com.cc221005.meetapp.ui.views.screens.Settings
import com.cc221005.meetapp.ui.views.screens.SpecificEvent
import com.cc221005.meetapp.ui.views.screens.SpecificUser
import com.cc221005.meetapp.utils.getActionIcons
import com.cc221005.meetapp.utils.getBottomBar
import com.cc221005.meetapp.utils.getNavigationIcon
import com.cc221005.meetapp.utils.getTitle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * # Navigation
 * The Navigation contains all the main navigation parts like Scaffold with TopAppBar, BottomNavigationBar, Title, ...
 * Depending on whether the user is logged in or not, the navigation decides on which screen to navigate.
 * If the user is logged in, it gets navigated to the home screen, whereas if the user is logged out, it gets navigated to the
 * onboarding screens.
 *
 * @param navigationModel (NavigationModel) Navigation Model to interact with navigation states
 * @param userModel (UserModel) User Model to interact with user states
 * @param auth (FirebaseAuth) Firebase authentication to interact with the authenticated user
 * @param db (FirebaseFirestore) Firebase Firestore database to interact with the database
 * @param searchModel (SearchModel) Search Model to interact with the search states
 * @param eventModel (EventModel) Event Model to interact with the event states
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navigationModel: NavigationModel,
    userModel: UserModel,
    auth: FirebaseAuth,
    db: FirebaseFirestore,
    searchModel: SearchModel,
    eventModel: EventModel
) {
    val navState = navigationModel.navigationState.collectAsState()
    val userState = userModel.userState.collectAsState()
    val eventState = eventModel.eventState.collectAsState()
    val selectedScreen = navState.value.selectedScreen
    val navController = rememberNavController()

    val context: Context = LocalContext.current

    Scaffold(
        topBar = {
            if (userState.value.localUser != null && !userState.value.localUser?.interests.isNullOrEmpty()) TopAppBar(
                title = {
                    getTitle(
                        screen = selectedScreen,
                        searchModel = searchModel,
                        userModel = userModel,
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    getActionIcons(
                        screen = selectedScreen,
                        navController = navController,
                        db = db,
                        eventModel = eventModel,
                        context = LocalContext.current,
                        userModel = userModel,
                        navigationModel = navigationModel
                    )
                },
                navigationIcon = {
                    getNavigationIcon(
                        navigationModel = navigationModel,
                        screen = selectedScreen,
                        navController = navController
                    )
                }
            )
        },
        bottomBar = {
            getBottomBar(
                selectedScreen = selectedScreen,
                navController = navController,
                userModel = userModel,
                auth = auth,
                context = LocalContext.current,
                db = db
            )
        }
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination =
            if (userState.value.localUser?.interests.isNullOrEmpty() && userState.value.localUser != null) Screen.OnboardingFlow5.route
            else if (userState.value.localUser != null) Screen.Home.route
            else Screen.OnboardingFlow1.route,
        ) {
            // Onboarding Screens
            composable(Screen.OnboardingFlow1.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow1)
                OnboardingFlow1()
            }
            composable(Screen.OnboardingFlow2.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow2)
                OnboardingFlow2()
            }
            composable(Screen.OnboardingFlow3.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow3)
                OnboardingFlow3(navController = navController, userModel = userModel)
            }
            composable(Screen.OnboardingFlow3Login.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow3Login)
                OnboardingFlow3Login(navController = navController, userModel = userModel)
            }
            composable(Screen.OnboardingFlow4.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow4)
                OnboardingFlow4(userModel = userModel)
            }
            composable(Screen.OnboardingFlow5.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow5)
                OnboardingFlow5(userModel = userModel)
            }
            composable(Screen.OnboardingFlow6.route) {
                navigationModel.selectScreen(Screen.OnboardingFlow6)
                OnboardingFlow6()
            }

            // Main Screens
            composable(Screen.Home.route) {
                navigationModel.selectScreen(Screen.Home)
                Home(navController = navController, userModel = userModel, eventModel = eventModel)
            }
            composable(Screen.Search.route) {
                navigationModel.selectScreen(Screen.Search)
                Search(
                    searchModel = searchModel,
                    navController = navController,
                    userModel = userModel
                )
            }
            composable(Screen.Create.route) {
                navigationModel.selectScreen(Screen.Create)
                Create(
                    navigationModel = navigationModel,
                    eventModel = eventModel,
                    userModel = userModel
                )
            }
            composable(Screen.Chat.route) {
                navigationModel.selectScreen(Screen.Chat)
                Chat()
            }
            composable(Screen.Profile.route) {
                navigationModel.selectScreen(Screen.Profile)
                Profile(
                    userModel = userModel,
                    navController = navController,
                    eventModel = eventModel
                )
            }

            // Detailed Screens
            composable(Screen.Settings.route) {
                navigationModel.selectScreen(Screen.Settings)
                Settings(auth = auth, userModel = userModel, navController = navController)
            }
            composable(Screen.Theme.route) {
                navigationModel.selectScreen(Screen.Theme)
                SelectTheme(navigationModel = navigationModel)
            }
            composable(Screen.SpecificUser.route) {
                navigationModel.selectScreen(Screen.SpecificUser)
                SpecificUser(navController = navController, userModel = userModel)
            }
            composable(Screen.SpecificEvent.route) {
                navigationModel.selectScreen(Screen.SpecificEvent)
                SpecificEvent(
                    navController = navController,
                    eventModel = eventModel,
                    userModel = userModel
                )
            }
            composable(Screen.EditEvent.route) {
                navigationModel.selectScreen(Screen.EditEvent)
                EditEvent(eventModel = eventModel, navigationModel = navigationModel)
            }
        }
        if (navState.value.isBackWithoutSavingDialogOpen) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { navigationModel.showBackWithoutSavingDialog(false) },
                title = { Text(text = stringResource(R.string.leave_without_saving)) },
                text = { Text(text = stringResource(R.string.back_without_saving_dialog_text)) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            navigationModel.showBackWithoutSavingDialog(false)
                            navController.popBackStack()
                        }) {
                        Text(text = stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            navigationModel.showBackWithoutSavingDialog(false)
                        }) {
                        Text(text = stringResource(id = R.string.no))
                    }
                }
            )
        }
        if (navState.value.isDeleteEventDialogOpen) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { navigationModel.showDeleteEventDialog(false) },
                title = { Text(text = stringResource(R.string.delete_event)) },
                text = { Text(text = stringResource(R.string.delete_event_dialog_text)) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            navigationModel.showDeleteEventDialog(false)
                            db.collection("events").document(eventState.value.editEvent.id)
                                .delete()
                                .addOnSuccessListener {
                                    navController.navigate(Screen.Home.route)
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.successfully_deleted_event),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.failed_deleting_event),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            navigationModel.showDeleteEventDialog(false)
                        }) {
                        Text(text = stringResource(id = R.string.no))
                    }
                }
            )
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, selectedScreen: Screen) {
    val navigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onSurface,
        selectedTextColor = MaterialTheme.colorScheme.onSurface,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(80.dp)
    ) {
        NavigationBarItem(
            selected = (selectedScreen == Screen.Home),
            onClick = { navController.navigate(Screen.Home.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = null) },
            label = { Text(text = "Home") },
            colors = navigationBarItemColors
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Search),
            onClick = { navController.navigate(Screen.Search.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.search), contentDescription = null) },
            label = { Text(text = "Search") },
            colors = navigationBarItemColors
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Create),
            onClick = { navController.navigate(Screen.Create.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.plus), contentDescription = null) },
            label = { Text(text = "Create") },
            colors = navigationBarItemColors
        )
        /*NavigationBarItem(
            selected = (selectedScreen == Screen.Chat),
            onClick = { navController.navigate(Screen.Chat.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.chat), contentDescription = null) },
            label = { Text(text = "Chat") },
            colors = navigationBarItemColors
        )*/
        NavigationBarItem(
            selected = (selectedScreen == Screen.Profile),
            onClick = { navController.navigate(Screen.Profile.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = null) },
            label = { Text(text = "Profile") },
            colors = navigationBarItemColors
        )
    }
}