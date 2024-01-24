package com.cc221005.meetapp.ui.views.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.User
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.utils.convertTimestampToFormattedDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecificEvent(navController: NavController, eventModel: EventModel, userModel: UserModel) {
    val eventState = eventModel.eventState.collectAsState()
    val userState = userModel.userState.collectAsState()

    userModel.getUserById(eventState.value.specificEvent.hostedBy.toString()) { user ->
        if (user != null) {
            eventModel.setHostedEventUser(user = user, uid = eventState.value.specificEvent.hostedBy.toString())
        }
    }


    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),

    ) {
        Image(
            painter = painterResource(id = R.drawable.default_event_image),
            contentDescription = stringResource(R.string.default_event_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            AssistChip(
                onClick = { },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.wallet_02), contentDescription = null) },
                label = { Text(text = "${eventState.value.specificEvent.cost} € / ticket") }
            )
            Spacer(modifier = Modifier.width(16.dp))
            AssistChip(
                onClick = { },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.users_02), contentDescription = null) },
                label = { Text(text = "${eventState.value.specificEvent.attendees?.size} / ${eventState.value.specificEvent.maxAttendees} attendees") }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = eventState.value.specificEvent.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "on ${convertTimestampToFormattedDate(eventState.value.specificEvent.timestamp)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = "hosted by ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = userState.value.specificUser.username.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    userModel.updateSpecificUser(user = eventState.value.hostedEventUser)
                    navController.navigate(Screen.SpecificUser.route)
                }
            )

        }


        Spacer(modifier = Modifier.height(24.dp))

        Text(text = eventState.value.specificEvent.description.toString())
    }
}