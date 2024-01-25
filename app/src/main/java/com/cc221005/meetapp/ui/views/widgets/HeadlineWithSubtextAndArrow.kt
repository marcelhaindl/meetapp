package com.cc221005.meetapp.ui.views.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cc221005.meetapp.R

/**
 * # Headline with subtext and arrow
 * Widget to display the separate titles on the home screen like "Recommended -> for you".
 *
 * @param headline (String) "Recommended"
 * @param subtext (String) "for you"
 * @param onclick (() -> Unit) on click method
 */
@Composable
fun HeadlineWithSubtextAndArrow(
    headline: String,
    subtext: String,
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onclick }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = headline,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtext,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.arrowright),
            contentDescription = null
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}