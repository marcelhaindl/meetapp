package com.cc221005.meetapp.ui.views.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.cc221005.meetapp.User
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen

@Composable
fun SpecificUser(navController: NavController, userModel: UserModel) {
    val userState = userModel.userState.collectAsState()
    if(userState.value.specificUser.uid == userState.value.localUser?.uid) {
        navController.navigate(Screen.Profile.route)
    }
    Text(text = userState.value.specificUser.name.toString())
}