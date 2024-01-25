package com.cc221005.meetapp.ui.views.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.utils.convertTimestampToFormattedDate
import com.cc221005.meetapp.ui.theme.md_theme_dark_onSurface
import com.cc221005.meetapp.ui.theme.md_theme_dark_onSurfaceVariant

/**
 * # Large Event Item
 * Widget to display the large event item with an image, and a black box overlay containing information like name, date, and description.
 *
 * @param navController (NavController) Navigation Controller to navigate to other screens
 * @param event (Event) Event to be displayed
 * @param eventModel (EventModel) Event Model to interact with event states
 */
@Composable
fun LargeEventItem(navController: NavController, event: Event, eventModel: EventModel) {
    val date = convertTimestampToFormattedDate(event.timestamp)

    Box(
        modifier = Modifier
            .width(330.dp)
            .height(240.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable {
                eventModel.setSpecificEventTo(event)
                navController.navigate(Screen.SpecificEvent.route)
            }
    ) {
        // Image background
        Image(
            painter = painterResource(id = R.drawable.default_event_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clip(shape = RoundedCornerShape(4.dp))
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.65f))
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .height(80.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = event.title,
                    color = md_theme_dark_onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "on $date",
                    color = md_theme_dark_onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.description.toString(),
                    color = md_theme_dark_onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
            }
        }
    }
}