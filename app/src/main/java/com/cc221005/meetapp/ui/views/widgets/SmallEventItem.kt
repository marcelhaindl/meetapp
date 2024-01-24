package com.cc221005.meetapp.ui.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.utils.convertTimestampToFormattedDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun SmallEventItem(navController: NavController, event: Event, eventModel: EventModel) {
    val date = convertTimestampToFormattedDate(event.timestamp)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                eventModel.setSpecificEventTo(event)
                navController.navigate(Screen.SpecificEvent.route)
            }
            .padding(horizontal = 16.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        // Image Box
        Box(
            modifier = Modifier
                .width(84.dp)
                .height(84.dp)
                .clip(shape = RoundedCornerShape(42.dp))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .wrapContentSize(align = Alignment.Center)
        ) {
            androidx.compose.material.Text(
                text = event.title.first().toString().uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column (
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = event.title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "on $date",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.description.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}