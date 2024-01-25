package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.HeadlineWithSubtextAndArrow
import com.cc221005.meetapp.ui.views.widgets.LargeEventItem
import com.cc221005.meetapp.ui.views.widgets.MeetUserItem
import java.util.Locale

/**
 * # Home Screen
 * The Home Screen contains recommended events, top events next week, people with similar interests, and other specific events based on
 * the users interests.
 *
 * @param navController (NavController) Navigation controller to navigate to other screens
 * @param userModel (UserModel) User Model to interact with the user states
 * @param eventModel (EventModel) Event Model to interact with the event states
 */
@Composable
fun Home(navController: NavController, userModel: UserModel, eventModel: EventModel) {
    val userState = userModel.userState.collectAsState()
    val eventState = eventModel.eventState.collectAsState()

    // Get recommended events for the user
    userModel.getRecommendedEvents()
    // Get people with same interests
    userModel.getPeopleWithSameInterests()
    // Get top events next week
    userModel.getTopEventsNextWeek()
    // Get events for each interest
    userModel.getEventsForEachInterest()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Recommended Title
        // Either show recommended events, or show first 5 events if there are no recommended events
        HeadlineWithSubtextAndArrow(
            headline = "Recommended",
            subtext = "for you",
            onclick = { }
        )
        if(!userState.value.recommendedEvents.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.recommendedEvents!!.take(5)) { event ->
                    LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } else {
            eventModel.getFirst5Events()
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(eventState.value.first5Events.take(5)) { event ->
                    LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        // Top Events next week
        if(!userState.value.topEventsNextWeek.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(36.dp))
            HeadlineWithSubtextAndArrow(
                headline = "Top Events",
                subtext = "next week",
                onclick = { }
            )
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.topEventsNextWeek!!.take(5)) { event ->
                    LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Meet people with similar interests
        // Either show people with similar interests or show first 5 users when there are no people with similar interests
        HeadlineWithSubtextAndArrow(
            headline = "Meet people",
            subtext = "with similar interests",
            onclick = { }
        )
        if(!userState.value.peopleWithSameInterests.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.peopleWithSameInterests!!.take(5)) { user ->
                    MeetUserItem(navController = navController, user = user, userModel = userModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } else {
            userModel.getFirst5Users()
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.first5Users.take(5)) { user ->
                    MeetUserItem(navController = navController, user = user, userModel = userModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        // Show top events for each interest
        userState.value.eventsForEachInterest.map {
            val newInterestString: String = it.key.replaceFirstChar { firstChar ->
                if (firstChar.isLowerCase()) firstChar.titlecase(Locale.ROOT) else firstChar.toString()
            }

            if(it.value!!.isNotEmpty()) {
                Spacer(modifier = Modifier.height(36.dp))

                HeadlineWithSubtextAndArrow(
                    headline = "$newInterestString Events",
                    subtext = "Find events about ${newInterestString.lowercase()}",
                    onclick = { }
                )

                LazyRow(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    items(it.value!!.take(5)) { event ->
                        LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))
    }
}