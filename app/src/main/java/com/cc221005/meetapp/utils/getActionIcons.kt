package com.cc221005.meetapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.UUID

@Composable
fun getActionIcons(screen: Screen, navController: NavController, db: FirebaseFirestore, eventModel: EventModel, context: Context) {
    val eventState = eventModel.eventState.collectAsState()
    when (screen) {
            Screen.Home -> {
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Create -> {
                if(eventState.value.addEvent.title.isNotEmpty() &&
                        eventState.value.addEvent.timestamp.toString().isNotEmpty()) {
                    IconButton(
                        onClick = {
                            val eventId: String = UUID.randomUUID().toString()
                            db.collection("events").document(eventId)
                                .set(eventState.value.addEvent.toMap(), SetOptions.merge())
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.successfully_added_event),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                    navController.navigate(Screen.SpecificEvent.route)
                                    eventModel.setSpecificEventTo(eventState.value.addEvent)
                                    eventModel.updateAddEvent(
                                        Event(
                                            id = eventId,
                                            title = "",
                                            description = "",
                                            timestamp = Timestamp.now(),
                                            cost = 0.0,
                                            maxAttendees = 0,
                                            hostedBy = "",
                                            attendees = mutableListOf(),
                                            visitedBy = mutableListOf(),
                                            tags = mutableListOf(),
                                        )
                                    )
                                    eventModel.setDeleteAddEventFlag(true)
                                }
                                .addOnFailureListener() {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.failed_adding_data),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = stringResource(R.string.settings),
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Chat -> {
                IconButton(onClick = { /* TODO: Search Functionality */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(R.string.search),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { /* TODO: Add Chat Functionality */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(R.string.add_chat),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Profile -> {
                IconButton(onClick = { /* TODO: Wishlist */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = stringResource(R.string.wishlist),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.SpecificUser -> {
                IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = stringResource(R.string.settings),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            else -> { }
        }
}
