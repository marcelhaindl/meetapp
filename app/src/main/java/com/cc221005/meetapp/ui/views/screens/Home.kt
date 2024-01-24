package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.HeadlineWithSubtextAndArrow
import com.cc221005.meetapp.ui.views.widgets.LargeEventItem
import com.cc221005.meetapp.ui.views.widgets.MeetUserItem
import java.util.Locale

@Composable
fun Home(navController: NavController, userModel: UserModel, eventModel: EventModel) {
    val userState = userModel.userState.collectAsState()

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
        HeadlineWithSubtextAndArrow(
            headline = "Recommended",
            subtext = "for you",
            onclick = { }
        )
        if(!userState.value.recommendedEvents.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.recommendedEvents!!) { event ->
                    LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } else {
            // TODO: Display 5 first events
        }

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
                items(userState.value.topEventsNextWeek!!) { event ->
                    LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        HeadlineWithSubtextAndArrow(
            headline = "Meet people",
            subtext = "with similar interests",
            onclick = { }
        )
        if(!userState.value.peopleWithSameInterests.isNullOrEmpty()) {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(userState.value.peopleWithSameInterests!!) { user ->
                    MeetUserItem(navController = navController, user = user, userModel = userModel)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } else {
            // TODO: Display 5 first users
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
                    items(it.value!!) { event ->
                        LargeEventItem(navController = navController, event = event, eventModel = eventModel)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))
    }
}