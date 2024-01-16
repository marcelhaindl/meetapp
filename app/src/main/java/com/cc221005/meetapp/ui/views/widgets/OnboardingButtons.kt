package com.cc221005.meetapp.ui.views.widgets

import android.opengl.Visibility
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.views.Screen

@Composable
fun OnboardingButtons(
    showLeadingButton: Boolean = true,
    showTrailingButton: Boolean = true,
    leadingButtonText: String = stringResource(R.string.back),
    trailingButtonText: String = stringResource(R.string.next),
    navController: NavController,
    onTrailingButtonClicked: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(showLeadingButton) TextButton(onClick = { navController.popBackStack() }) {
            Text(text = leadingButtonText)
        } else Box(modifier = Modifier.width(1.dp))
        if(showTrailingButton) TextButton(onClick = onTrailingButtonClicked ) {
            Text(text = trailingButtonText)
        } else Box(modifier = Modifier.width(1.dp))
    }
}