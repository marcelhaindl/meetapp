package com.cc221005.meetapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.util.UUID

/**
 * # Get Action Icons
 * The getActionIcons composable is used to get the action icons for the TopAppBar depending on the current screen.
 *
 * @param screen (Screen) Current Screen
 * @param navController (NavController) Navigation Controller to navigate to other screens
 * @param db (FirebaseFirestore) Firebase Firestore database variable to interact with database
 * @param eventModel (EventModel) Event Model to interact with event states
 * @param context (Context)
 * @param userModel (UserModel) User Model to interact with user states
 * @param navigationModel (NavigationModel) Navigation Model to interact with navigation states
 */
@Composable
fun getActionIcons(
    screen: Screen,
    navController: NavController,
    db: FirebaseFirestore,
    eventModel: EventModel,
    context: Context,
    userModel: UserModel,
    navigationModel: NavigationModel
) {
    val eventState = eventModel.eventState.collectAsState()
    val userState = userModel.userState.collectAsState()
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
                                    eventModel.setSpecificEventTo(eventState.value.addEvent.copy(id = eventId))
                                    eventModel.updateAddEvent(
                                        Event(
                                            id = "",
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
                                .addOnFailureListener {
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
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(R.string.search),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(R.string.add_chat),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Screen.Profile -> {
                IconButton(onClick = {  }) {
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
            Screen.SpecificEvent -> {
                if(eventState.value.hostedEventUser.uid == userState.value.localUser?.uid) {
                    IconButton(onClick = {
                        navController.navigate(Screen.EditEvent.route)
                        eventModel.setEditEventTo(eventState.value.specificEvent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = stringResource(R.string.edit),
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Screen.EditEvent -> {
                if(eventState.value.editEvent.title.isNotEmpty() &&
                    eventState.value.editEvent.timestamp.toString().isNotEmpty()) {
                    IconButton(onClick = {
                        eventModel.saveEditedEvent(navController = navController, context = context)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = stringResource(R.string.save),
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = {
                        navigationModel.showDeleteEventDialog(true)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = stringResource(R.string.delete),
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            else -> { }
        }
}
