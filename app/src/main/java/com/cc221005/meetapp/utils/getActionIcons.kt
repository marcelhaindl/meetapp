package com.cc221005.meetapp.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.views.Screen

@Composable
fun getActionIcons(screen: Screen, navController: NavController) {
    when (screen) {
            Screen.Home -> {
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Search -> {
                // TODO: Implement search bar
            }
            Screen.Create -> {
                IconButton(onClick = { /* TODO: Save Functionality */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Chat -> {
                IconButton(onClick = { /* TODO: Search Functionality */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(R.string.search),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { /* TODO: Add Chat Functionality */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(R.string.add_chat),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Profile -> {
                IconButton(onClick = { /* TODO: Wishlist */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = stringResource(R.string.wishlist),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.SpecificUser -> {
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            else -> { }
        }
}