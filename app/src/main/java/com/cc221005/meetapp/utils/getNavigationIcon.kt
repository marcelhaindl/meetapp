package com.cc221005.meetapp.utils

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.views.Screen

@Composable
fun getNavigationIcon(navigationModel: NavigationModel, screen: Screen, navController: NavController) {
    if (screen != Screen.Home &&
        screen != Screen.Search &&
        screen != Screen.Create &&
        screen != Screen.Chat &&
        screen != Screen.Profile) {
        IconButton(onClick = {
            if (screen == Screen.EditEvent) navigationModel.showBackWithoutSavingDialog(true)
            else navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}