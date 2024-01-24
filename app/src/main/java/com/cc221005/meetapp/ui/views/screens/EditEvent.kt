package com.cc221005.meetapp.ui.views.screens

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.uistates.UserState
import com.cc221005.meetapp.ui.views.widgets.MyDatePicker
import com.google.firebase.Timestamp
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEvent(eventModel: EventModel, navigationModel: NavigationModel) {
    val eventState = eventModel.eventState.collectAsState()

    var title by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(eventState.value.editEvent.title ?: "")) }
    var description by remember { mutableStateOf(TextFieldValue(eventState.value.editEvent.description ?: "")) }
    var cost by remember { mutableStateOf(TextFieldValue( eventState.value.editEvent.cost.toString() ?: "")) }
    var maxAttendees by remember { mutableStateOf(TextFieldValue(eventState.value.editEvent.maxAttendees.toString() ?: "")) }
    var tags by remember { mutableStateOf(TextFieldValue(eventState.value.editEvent.tags!!.joinToString(", ") ?: "")) }


    eventModel.updateEditEvent(
        Event(
            title = title.text,
            description = description.text,
            cost = cost.text.takeIf { it.isNotBlank() }?.toDoubleOrNull() ?: 0.0,
            maxAttendees = maxAttendees.text.takeIf { it.isNotBlank() }?.toIntOrNull() ?: 0,
            tags = tags.text.lowercase().split(", ").toMutableList(),
        )
    )


    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
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

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            singleLine = true,
            onValueChange = { newText ->
                title = newText
            },
            label = { Text(text = stringResource(R.string.title)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.title),
                    contentDescription = null,
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        MyDatePicker(
            label = stringResource(id = R.string.date),
            eventModel = eventModel,
            modifier = Modifier.fillMaxWidth(),
            navigationModel = navigationModel,
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,

            onValueChange = { newText ->
                description = newText
            },

            label = { Text(text = stringResource(R.string.description)) },
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.list),
                    contentDescription = null,
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = cost,
                singleLine = true,

                onValueChange = { newValue ->
                    cost = newValue
                },
                label = { Text(text = stringResource(R.string.cost)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.wallet_02),
                        contentDescription = null,
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.width(16.dp))

            TextField(
                modifier = Modifier.weight(1f),
                value = maxAttendees,
                singleLine = true,

                onValueChange = { newValue ->
                    maxAttendees = newValue
                },

                label = { Text(text = stringResource(R.string.max_attend)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.users_02),
                        contentDescription = null,
                    )
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = tags,

            onValueChange = { newText ->
                tags = newText
            },

            placeholder = { Text(text = "tag1, tag2, tag3, ...") },

            label = { Text(text = stringResource(R.string.tags)) },
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.hash),
                    contentDescription = null,
                )
            }
        )
    }
}