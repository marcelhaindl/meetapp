package com.cc221005.meetapp.ui.views.widgets

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.cc221005.meetapp.Event
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.views.Screen
import com.cc221005.meetapp.utils.convertLocalDateTimeToTimestamp
import com.cc221005.meetapp.utils.convertTimestampToFormattedDate
import com.cc221005.meetapp.utils.convertTimestampToLocalDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

/**
 * # MyDatePicker
 * Date picker for picking a date when editing or adding an event
 * @param label (String) Label of the Text Field
 * @param eventModel (EventModel) Event Model to interact with event states
 * @param navigationModel (NavigationModel) Navigation Model to interact with navigation states
 * @param keyboardActions (KeyboardActions) Keyboard actions (Set to default)
 * @param keyboardOptions (KeyboardOptions) Keyboard options (Set to default)
 * @param modifier (Modifier) Modifier of the date picker
 */
@Composable
fun MyDatePicker(
    label: String,
    eventModel: EventModel,
    navigationModel: NavigationModel,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier,
) {
    // Set the event state date depending on whether the selected screen is the AddEvent or the
    // EditEvent Screen
    val eventState = eventModel.eventState.collectAsState()
    val navState = navigationModel.navigationState.collectAsState()


    val eventTimestamp =
        if(navState.value.selectedScreen == Screen.EditEvent) eventState.value.editEvent.timestamp
        else eventState.value.addEvent.timestamp
    val eventDateString = if(navState.value.selectedScreen == Screen.EditEvent) convertTimestampToFormattedDate(eventState.value.editEvent.timestamp)
        else convertTimestampToFormattedDate(eventState.value.addEvent.timestamp)

    // Date Picker Dialog
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            val localDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0, 0)
            if(navState.value.selectedScreen == Screen.EditEvent) {
                eventModel.updateEditEventDate(convertLocalDateTimeToTimestamp(localDateTime))
            } else {
                eventModel.updateAddEventDate(convertLocalDateTimeToTimestamp(localDateTime))
            }
        },
        convertTimestampToLocalDateTime(eventTimestamp).year,
        convertTimestampToLocalDateTime(eventTimestamp).monthValue - 1,
        convertTimestampToLocalDateTime(eventTimestamp).dayOfMonth,
    )

    // Disable past dates
    dialog.datePicker.minDate = System.currentTimeMillis() - 1000

    TextField(
        // Value set to the date
        value = eventDateString,
        onValueChange = {},
        // Leading Icon
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.calendar),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null
            )
        },
        // Label of the Text Field
        label = { Text(text = label) },
        // Not enabled to just click on the text field
        enabled = false,
        // Make the TextField clickable to show the dialog on click
        modifier = modifier
            .clickable { dialog.show() },
        // Keyboard Options
        keyboardOptions = keyboardOptions,
        // Keyboard Actions
        keyboardActions = keyboardActions,
        // Colors of the text field
        colors = TextFieldDefaults.textFieldColors (
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}
