package com.cc221005.meetapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.cc221005.meetapp.ui.uistates.EventModel
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
    private val userModel = UserModel(db = db)

    private val searchModel = SearchModel(db = db, userModel = userModel)

    private val eventModel = EventModel(db = db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in and if data is already saved
        if(auth.currentUser != null) {

            db.collection("users").document(auth.currentUser!!.uid).get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot.exists()) {
                        // Snapshot (User data) exists
                        val user = User(
                            uid = auth.currentUser?.uid,
                            email = auth.currentUser?.email,
                            username = snapshot.get("username").toString(),
                            name = snapshot.get("name").toString(),
                            interests = snapshot.get("interests") as? MutableList<String>
                                ?: mutableListOf(),
                            followers = snapshot.get("followers") as? MutableList<String>
                                ?: mutableListOf(),
                            following = snapshot.get("following") as? MutableList<String>
                                ?: mutableListOf(),
                            biography = if(snapshot.get("biography") != null && snapshot.get("biography") != "") snapshot.get("biography").toString()
                                        else ""
                        )

                        userModel.setLocalUserTo(user)
                    } else {
                        userModel.setLocalUserTo(null)
                        Log.e("Error", "Error while initially saving user")
                    }

                }
        } else {
            userModel.setLocalUserTo(null)
        }
        setContent {
            val themeState by navigationModel.navigationState.collectAsState()

            MeetappTheme (
                useDarkTheme =
                when (themeState.theme) {
                    0 -> true
                    1 -> false
                    else -> isSystemInDarkTheme()
                }
            ) {
                // A surface container using the 'background' color from the theme
                window.statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
                window.navigationBarColor = MaterialTheme.colorScheme.surface.toArgb()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navigationModel = navigationModel, userModel = userModel, auth = auth, db = db, searchModel = searchModel, eventModel = eventModel)
                }
            }
        }
    }
}