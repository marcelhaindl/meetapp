package com.cc221005.meetapp.ui.views.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.SmallEventItem
import com.google.firebase.components.Lazy

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Profile(userModel: UserModel) {
    val userState = userModel.userState.collectAsState()
    val localUser = userState.value.localUser

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(stringResource(R.string.hosted),
        stringResource(R.string.upcoming), stringResource(R.string.visited))

    // Load hosted, upcoming and visited events into User Model
    userModel.getEventsFromUserId(localUser?.uid.toString())

    Log.e("asdf", userModel.userState.value.localUser.toString())

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
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { /* TODO: onFollowersClick */ }
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
                        modifier = Modifier.clickable { /* TODO: onFollowingClick */ }
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
                Spacer(modifier = Modifier.height(32.dp))
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
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = { /*TODO: Edit Profile*/ }
                ) {
                    Text(text = stringResource(R.string.edit_profile))
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
                            text = stringResource(R.string.no_hosted_events),
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
                    SmallEventItem(event = event)
                    Spacer(modifier = Modifier.height(8.dp))
                }
        }
    }
}
