package com.cc221005.meetapp.ui.views.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.cc221005.meetapp.ui.uistates.SearchModel

@Composable
fun SpecificUser(searchModel: SearchModel) {
    val searchState = searchModel.searchState.collectAsState()
    Text(text = searchState.value.specificUser.name.toString())
}