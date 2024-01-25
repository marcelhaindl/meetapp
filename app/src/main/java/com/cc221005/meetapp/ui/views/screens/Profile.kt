package com.cc221005.meetapp.ui.views.screens

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.SmallEventItem

/**
 * # Profile Screen
 * The Profile Screen contains the profile image (colored box) as well as the name, the followers and following users, the biography, the interests and all
 * the hosted, upcoming and visited events of the user
 *
 * @param userModel (UserModel) User Model to interact with the user states
 * @param navController (NavController) Navigation Controller to navigate to other screens
 * @param eventModel (EventModel) Event Model to interact with the event states
 */
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Profile(userModel: UserModel, navController: NavController, eventModel: EventModel) {
    val userState = userModel.userState.collectAsState()
    val localUser = userState.value.localUser

    // Create a remember variable (initially set to 0)
    var tabIndex by remember { mutableStateOf(0) }

    // Create the tab strings
    val tabs = listOf(stringResource(R.string.my_events),
            stringResource(R.string.upcoming), stringResource(R.string.visited))

    // Load hosted, upcoming and visited events into User Model
    userModel.getEventsFromUserId(localUser?.uid.toString())

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
                        text = localUser?.username?.first().toString().uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = localUser?.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                /*Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {  }
                    ) {
                        Text(
                            text = localUser?.followers?.size.toString(),
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
                        modifier = Modifier.clickable {  }
                    ) {
                        Text(
                            text = localUser?.following?.size.toString(),
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
                Spacer(modifier = Modifier.height(32.dp))*/
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (localUser?.biography.isNullOrEmpty()) "Hi, I am ${localUser?.name} and this is my MeetApp Account. Let's discover new events together."
                    else localUser?.biography.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildString {
                        localUser?.interests?.forEach { interest ->
                            append("#${interest.lowercase()} ")
                        }
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
                /*Spacer(modifier = Modifier.height(32.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = {  }
                ) {
                    Text(text = stringResource(R.string.edit_profile))
                }*/
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
                            enabled = index == 0,
                            onClick = { tabIndex = index },
                            selectedContentColor = if(index != 0) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.primary,
                            unselectedContentColor = if(index != 0) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.primary,
                            icon = {
                                when (index) {
                                    0 -> Text(
                                        text = userState.value.hostedEvents?.size.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    1 -> Text(
                                        text = userState.value.upcomingEvents?.size.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    2 -> Text(
                                        text = userState.value.visitedEvents?.size.toString(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            if(userState.value.hostedEvents.isNullOrEmpty()) {
                when (tabIndex) {
                    0 -> items (1) {
                        Text(
                            text = stringResource(R.string.no_events_created),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    1 -> items(1) {
                        Text(
                            text = stringResource(R.string.no_upcoming_events),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    2 -> items(1) {
                        Text(
                            text = stringResource(R.string.no_visited_events),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }else {
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
        }
    }
}
