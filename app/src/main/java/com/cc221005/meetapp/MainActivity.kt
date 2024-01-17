package com.cc221005.meetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.uistates.SearchModel
import com.cc221005.meetapp.ui.views.Navigation
import com.example.compose.MeetappTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    private val navigationModel = NavigationModel()
    private val userModel = UserModel()

    private val searchModel = SearchModel(db = db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly
        userModel.setLocalUserTo(auth.currentUser)
        setContent {
            MeetappTheme {
                // A surface container using the 'background' color from the theme
                window.statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
                window.navigationBarColor = MaterialTheme.colorScheme.surface.toArgb()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navigationModel = navigationModel, userModel = userModel, auth = auth, db = db, searchModel = searchModel)
                }
            }
        }
    }
}