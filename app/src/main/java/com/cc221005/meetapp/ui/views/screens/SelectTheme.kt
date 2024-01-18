package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.NavigationModel

@Composable
fun SelectTheme(navigationModel: NavigationModel) {
    val navState = navigationModel.navigationState.collectAsState()

    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Dark Theme
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { navigationModel.updateTheme(0) }
                .padding(horizontal = 16.dp)

        ) {
            RadioButton(
                selected = navState.value.theme == 0,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    unselectedColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(R.string.dark_theme), color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
        }
        // Light Theme
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { navigationModel.updateTheme(1) }
                .padding(horizontal = 16.dp)

        ) {
            RadioButton(
                selected = navState.value.theme == 1,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    unselectedColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(R.string.light_theme), color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
        }
        // System Default
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { navigationModel.updateTheme(3) }
                .padding(horizontal = 16.dp)

        ) {
            RadioButton(
                selected = navState.value.theme != 0 && navState.value.theme != 1,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    unselectedColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(R.string.system_default), color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface)
        }
    }
}