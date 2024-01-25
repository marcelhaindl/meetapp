package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.ui.views.widgets.SettingsItem
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.auth.FirebaseAuth

/**
 * # Settings Screen
 * The Settings Screen contains a number of buttons like privacy, security, theme, about, etc.
 * By now, only the theme button is implemented.
 * Also on the very bottom of the screen, there is the log out button to log out the user.
 *
 * @param auth (FirebaseAuth) to log out the user
 * @param userModel (UserModel) User Model to interact with user states
 * @param navController (NavController) Navigation controller to navigate to other screens
 */
@Composable
fun Settings(auth: FirebaseAuth, userModel: UserModel, navController: NavController) {
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Column {
            /*SettingsItem (
                icon = R.drawable.person,
                title = stringResource(R.string.account_settings),
                onClick = { }
            )
            SettingsItem (
                icon = Icons.Outlined.Notifications,
                title = stringResource(R.string.notifications),
                onClick = { }
            )
            SettingsItem (
                icon = R.drawable.lock,
                title = stringResource(R.string.privacy),
                onClick = { }
            )
            SettingsItem (
                icon = R.drawable.security,
                title = stringResource(R.string.security),
                onClick = { }
            )
            SettingsItem (
                icon = R.drawable.language,
                title = stringResource(R.string.language),
                onClick = { }
            )*/
            SettingsItem (
                icon = R.drawable.theme,
                title = stringResource(R.string.theme),
                onClick = {
                    navController.navigate(Screen.Theme.route)
                }
            )/*
            SettingsItem (
                icon = R.drawable.legal,
                title = stringResource(R.string.legal_and_compliance),
                onClick = { }
            )
            SettingsItem (
                icon = Icons.Outlined.Info,
                title = stringResource(R.string.about_meetapp),
                onClick = { }
            )
            SettingsItem (
                icon = R.drawable.help,
                title = stringResource(R.string.feedback_and_support),
                onClick = { }
            )*/
        }
        TextButton(
            onClick = {
                auth.signOut()
                userModel.setLocalUserTo(null) },
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(
                text = stringResource(R.string.logout),
                textAlign = TextAlign.End,
            )
        }
    }
}