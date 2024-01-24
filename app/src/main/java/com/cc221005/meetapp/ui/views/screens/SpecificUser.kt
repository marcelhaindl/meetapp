package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.User
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.ui.views.widgets.SmallEventItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpecificUser(navController: NavController, userModel: UserModel, eventModel: EventModel) {
    val userState = userModel.userState.collectAsState()
    if(userState.value.specificUser.uid == userState.value.localUser?.uid) {
        navController.navigate(Screen.Profile.route)
    }

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(stringResource(R.string.hosted),
        stringResource(R.string.upcoming), stringResource(R.string.visited))


    LazyColumn (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),

        ) {
        // Image Box
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clip(shape = RoundedCornerShape(60.dp))
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    androidx.compose.material.Text(
                        text = userState.value.specificUser.username?.first().toString().uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = userState.value.specificUser.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* TODO: onFollowersClick */ }
                    ) {
                        Text(
                            text = userState.value.specificUser.followers?.size.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(R.string.followers),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(48.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* TODO: onFollowingClick */ }
                    ) {
                        Text(
                            text = userState.value.specificUser.following?.size.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(R.string.following),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (userState.value.specificUser.biography.isNullOrEmpty()) "Hi, I am ${userState.value.specificUser.name} and this is my MeetApp Account. Let's discover new events together."
                    else userState.value.specificUser.biography.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildString {
                        userState.value.specificUser.interests?.forEach { interest ->
                            append("#${interest.lowercase()} ")
                        }
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = { /* TODO: Functionality to follow */ }
                ) {
                    Text(text = stringResource(R.string.follow))
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        stickyHeader {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        icon = {
                            when (index) {
                                0 -> Text(
                                    text = "0",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                1 -> Text(
                                    text = "0",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                2 -> Text(
                                    text = "0",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        /*if(userState.value.hostedEvents.isNullOrEmpty()) {
            when (tabIndex) {
                0 -> items (1) {
                    Text(
                        text = stringResource(R.string.hosted_events_not_available),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                1 -> items(1) {
                    Text(
                        text = stringResource(R.string.upcoming_events_not_available),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                2 -> items(1) {
                    Text(
                        text = stringResource(R.string.visited_events_not_available),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            val eventList: MutableList<Event> = when (tabIndex) {
                0 -> userState.value.hostedEvents!!
                1 -> userState.value.upcomingEvents!!
                2 -> userState.value.visitedEvents!!
                else -> mutableListOf()
            }
            items(eventList) { event ->
                SmallEventItem(navController = navController, event = event, eventModel = eventModel)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }*/
    }
}