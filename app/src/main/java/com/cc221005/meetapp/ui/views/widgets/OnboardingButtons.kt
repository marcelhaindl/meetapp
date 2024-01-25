package com.cc221005.meetapp.ui.views.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.R

/**
 * # Onboarding Buttons
 * The Onboarding Buttons Widget is shown at the very bottom of the onboarding screens in order to go back and forth.
 *
 * @param showLeadingButton (Boolean) To show leading button or not
 * @param showTrailingButton (Boolean) To show trailing button or not
 * @param leadingButtonText (String) Leading button text
 * @param trailingButtonText (String) Trailing button text
 * @param navController (NavController) Navigation controller to navigate to other screens
 * @param onTrailingButtonClicked (() -> Unit) on click method when trailing button is clicked
 */
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
        TextButton(onClick = { navController.popBackStack() }, enabled = showLeadingButton) {
            Text(text = leadingButtonText)
        }
        TextButton(onClick = onTrailingButtonClicked, enabled = showTrailingButton) {
            Text(text = trailingButtonText)
        }
    }
}