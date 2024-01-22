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
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.EventModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * # MyDatePicker
 * Date picker for picking a date when editing or adding an event
 * @param label Label of the Text Field
 * @param eventModel Instance of the EventModel
 * @param navigationModel Instance of the NavigationModel
 * @param keyboardActions Keyboard actions (Set to default)
 * @param keyboardOptions Keyboard options (Set to default)
 * @param pattern Pattern of how the date should be displayed
 * @param modifier Modifier of the date picker
 */
@Composable
fun MyDatePicker(
    label: String,
    eventModel: EventModel,
    navigationModel: NavigationModel,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    pattern: String = "dd/MM/yyyy",
    modifier: Modifier,
) {
    // Navigation State
    val navState = navigationModel.navigationState.collectAsState()

    // Set the event state date depending on whether the selected screen is the AddEvent or the
    // EditEvent Screen
    val eventDate = eventModel.eventState.collectAsState().value.date

    // Formatter to format the date to a specific pattern
    val formatter = DateTimeFormatter.ofPattern(pattern)

    // Date Picker Dialog
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            eventModel.updateDate(
                date = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0, 0)
            )
        },
        eventDate.year,
        eventDate.monthValue - 1,
        eventDate.dayOfMonth,
    )

    // Disable past dates
    dialog.datePicker.minDate = System.currentTimeMillis() - 1000

    // TextField
    TextField(
        // Value set to the date
        value = eventDate.format(formatter),
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
