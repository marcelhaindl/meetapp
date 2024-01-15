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
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.views.screens.Chat
import com.cc221005.meetapp.ui.views.screens.Create
import com.cc221005.meetapp.ui.views.screens.Home
import com.cc221005.meetapp.ui.views.screens.Profile
import com.cc221005.meetapp.ui.views.screens.Search
import kotlinx.coroutines.launch
import java.time.LocalTime

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Search: Screen("search")
    object Create: Screen("create")
    object Chat: Screen("chat")
    object Profile: Screen("profile")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navigationModel: NavigationModel) {
    val navState = navigationModel.navigationState.collectAsState()
    val selectedScreen = navState.value.selectedScreen
    val navController = rememberNavController()

    Scaffold(
            topBar = {
                TopAppBar(
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

            bottomBar = { BottomNavigationBar(navController = navController, selectedScreen = selectedScreen)}
    ) {
                NavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                    startDestination = Screen.Home.route,
                ) {
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
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.height(80.dp)
    ) {
        NavigationBarItem(
            selected = (selectedScreen == Screen.Home),
            onClick = { navController.navigate(Screen.Home.route) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text(text = "Home") }
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Search),
            onClick = { navController.navigate(Screen.Search.route) },
            icon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            label = { Text(text = "Search") }
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Create),
            onClick = { navController.navigate(Screen.Create.route) },
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
            label = { Text(text = "Create") }
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Chat),
            onClick = { navController.navigate(Screen.Chat.route) },
            icon = { Icon(imageVector = Icons.Default.Call, contentDescription = null) },
            label = { Text(text = "Chat") }
        )
        NavigationBarItem(
            selected = (selectedScreen == Screen.Profile),
            onClick = { navController.navigate(Screen.Profile.route) },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text(text = "Profile") }
        )
    }
}