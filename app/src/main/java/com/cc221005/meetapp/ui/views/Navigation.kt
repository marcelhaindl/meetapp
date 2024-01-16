package com.cc221005.meetapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.LoginModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.views.screens.Chat
import com.cc221005.meetapp.ui.views.screens.Create
import com.cc221005.meetapp.ui.views.screens.Home
import com.cc221005.meetapp.ui.views.screens.Profile
import com.cc221005.meetapp.ui.views.screens.Search
import com.cc221005.meetapp.ui.views.widgets.OnboardingButtons
import kotlinx.coroutines.launch
import java.time.LocalTime



fun getTrailingButtonFunction(navController: NavController, currentScreen: Screen, loginModel: LoginModel): () -> Unit {
    when(currentScreen) {
        Screen.OnboardingFlow1 -> return {
            navController.navigate(Screen.OnboardingFlow2.route)
        }

        Screen.OnboardingFlow2 -> return {
            navController.navigate(Screen.OnboardingFlow3.route)
        }

        Screen.OnboardingFlow3 -> return {
            // TODO: Save Email and Password
            navController.navigate(Screen.OnboardingFlow4.route)
        }

        Screen.OnboardingFlow4 -> return {
            // TODO: Save Username and Name
            navController.navigate(Screen.OnboardingFlow5.route)
        }

        Screen.OnboardingFlow5 -> return {
            // TODO: Save Interests and Hobbies
            navController.navigate(Screen.OnboardingFlow6.route)
        }

        Screen.OnboardingFlow6 -> return {
            // TODO: Authenticate user with email and password
            // TODO: Set auth.currentUser
            // TODO: Switch to main screen
            loginModel.userIsLoggedIn(true)
        }

        else -> return {
            navController.navigate(Screen.OnboardingFlow1.route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navigationModel: NavigationModel, loginModel: LoginModel) {
    val navState = navigationModel.navigationState.collectAsState()
    val loginState = loginModel.loginState.collectAsState()
    val selectedScreen = navState.value.selectedScreen
    val navController = rememberNavController()

    Scaffold(
            topBar = {
                if(loginState.value.isLoggedIn) TopAppBar(
                    title = {
                        Text(text = "MeetApp")
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        scrolledContainerColor = MaterialTheme.colorScheme.background
                    ),
                    actions = {
                        // Actions
                    },
                    navigationIcon = {
                        // Navigation Icon
                    }
                )
            },

            bottomBar = {
                if(loginState.value.isLoggedIn) BottomNavigationBar(
                    navController = navController,
                    selectedScreen = selectedScreen
                ) else OnboardingButtons(
                    showLeadingButton = selectedScreen != Screen.OnboardingFlow1,
                    navController = navController,
                    onTrailingButtonClicked = getTrailingButtonFunction(
                        navController = navController,
                        currentScreen = navState.value.selectedScreen,
                        loginModel = loginModel
                    )
                )
            }
    ) {
                NavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                    startDestination = if(loginState.value.isLoggedIn) Screen.Home.route else Screen.OnboardingFlow1.route,
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
                        OnboardingFlow3()
                    }
                    composable(Screen.OnboardingFlow4.route) {
                        navigationModel.selectScreen(Screen.OnboardingFlow4)
                        OnboardingFlow4()
                    }
                    composable(Screen.OnboardingFlow5.route) {
                        navigationModel.selectScreen(Screen.OnboardingFlow5)
                        OnboardingFlow5()
                    }
                    composable(Screen.OnboardingFlow6.route) {
                        navigationModel.selectScreen(Screen.OnboardingFlow6)
                        OnboardingFlow6()
                    }

                    // Main Screens
                    composable(Screen.Home.route) {
                        navigationModel.selectScreen(Screen.Home)
                        Home()
                    }
                    composable(Screen.Search.route) {
                        navigationModel.selectScreen(Screen.Search)
                        Search()
                    }
                    composable(Screen.Create.route) {
                        navigationModel.selectScreen(Screen.Create)
                        Create()
                    }
                    composable(Screen.Chat.route) {
                        navigationModel.selectScreen(Screen.Chat)
                        Chat()
                    }
                    composable(Screen.Profile.route) {
                        navigationModel.selectScreen(Screen.Profile)
                        Profile()
                    }
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
        NavigationBarItem(
            selected = (selectedScreen == Screen.Chat),
            onClick = { navController.navigate(Screen.Chat.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.chat), contentDescription = null) },
            label = { Text(text = "Chat") },
            colors = navigationBarItemColors
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Profile),
            onClick = { navController.navigate(Screen.Profile.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = null) },
            label = { Text(text = "Profile") },
            colors = navigationBarItemColors
        )
    }
}