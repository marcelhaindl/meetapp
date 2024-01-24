package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.User
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.UserItem
import com.google.firebase.firestore.FirebaseFirestore
@Composable
fun Search(searchModel: SearchModel, navController: NavController, userModel: UserModel) {
    val searchState = searchModel.searchState.collectAsState()
   LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =
        if(searchState.value.loadedUsers.isEmpty() || searchState.value.isLoading)
            Arrangement.Center
        else Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
       if(searchState.value.isLoading) {
           item {
               CircularProgressIndicator()
           }
       } else {
           if (searchState.value.loadedUsers.isNotEmpty()) {
               item {
                   Spacer(modifier = Modifier.height(16.dp))
               }
               items(searchState.value.loadedUsers) { user ->
                   UserItem(user = user, navController = navController, userModel = userModel)
                   Spacer(modifier = Modifier.height(8.dp))
               }
           } else {
               item {
                   Text(
                       text = stringResource(R.string.no_users_found),
                       color = MaterialTheme.colorScheme.onSurfaceVariant,
                       style = MaterialTheme.typography.bodyMedium
                   )
               }
           }
       }
    }
}

