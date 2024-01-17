package com.cc221005.meetapp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.views.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun getTitle(screen: Screen, searchModel: SearchModel) {
    var searchString by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(
        TextFieldValue("")
    ) }

    searchModel.updateSearchString(searchString = searchString.text)

    val keyboardController = LocalSoftwareKeyboardController.current

    when (screen) {
        Screen.Home -> Text(text = "Home")
        Screen.Create -> Text(text = "Create")
        Screen.Chat -> Text(text = "Chat")
        Screen.Profile -> Text(text = "Profile")
        Screen.Settings -> Text(text = "Settings")

        Screen.Search ->
            TextField(
                value = searchString,
                onValueChange = { newValue: TextFieldValue -> searchString = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp).clip(shape = RoundedCornerShape(99.dp)),
                placeholder = { Text(text = stringResource(id = R.string.search) + "...") },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.search), contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
                trailingIcon = {
                    if(searchString.text.isNotEmpty()) IconButton(onClick = { searchString = TextFieldValue("") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(id = R.string.close), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent, // Remove bottom line when focused
                    unfocusedIndicatorColor = Color.Transparent // Remove bottom line when not focused
                ),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Search
                ),
                // When search button is pressed and search bar is empty, then hide the search bar
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                    }
                ),
            )
        else -> Text(text = "")
    }
}