package com.cc221005.meetapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cc221005.meetapp.ui.theme.MeetappTheme
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.uistates.NavigationModel
import com.cc221005.meetapp.ui.views.Navigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    private val navigationModel = NavigationModel()
    private val userModel = UserModel()

    private val isLoggedIn = userModel.userState.value.isLoggedIn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly
        userModel.updateFirebaseUser(auth.currentUser)
        setContent {
            MeetappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navigationModel = navigationModel, userModel = userModel, auth = auth, db = db)
                }
            }
        }
    }
}